package com.devflask.statisticslib.lib.data;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.enums.Substatistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class StatisticsManager {

    private final HashMap<UUID, PlayerStatistic> playerStatistics;

    private final PlayerStatistics plugin;

    public StatisticsManager(PlayerStatistics plugin) {
        this.plugin = plugin;
        playerStatistics = new HashMap<>();
    }


    /**
     * This method will return a cached {@link PlayerStatistic} instance
     * if existing, or fetch/create a new instance.
     *
     * @param player the player whose {@link PlayerStatistic} wants to be accessed/fetched
     * @return returns either a cached {@link PlayerStatistic} instance or a new one
     */
    public PlayerStatistic getOrFetch(Player player) {
        return getOrFetch(player.getUniqueId());
    }



    /**
     * This method will return a cached {@link PlayerStatistic} instance
     * if existing, or fetch/create a new instance.
     * @param uuid the uuid of the player whose {@link PlayerStatistic} wants to be accessed/fetched
     * @return returns either a cached {@link PlayerStatistic} instance or a new one through {@link StatisticsManager#fetchOrCreate(UUID)}
     */
    public PlayerStatistic getOrFetch(UUID uuid) {
        Optional<PlayerStatistic> statistic = getStatistic(uuid);
        return statistic.orElseGet(() -> fetchOrCreate(uuid));
    }



    /**
     * This method gets the {@link PlayerStatistic} for a specific player
     * @param player the player whose {@link PlayerStatistic} wants to be accessed
     * @return this returns an {@link Optional} containing a {@link PlayerStatistic}
     * object of the player or an {@link Optional#empty()} if no Statistics for the player
     * could be found (Due to the Statistic not being fetched or the player not having a statistic yet)
     */
    public Optional<PlayerStatistic> getStatistic(Player player) {
        return getStatistic(player.getUniqueId());
    }



    /**
     * This method gets the {@link PlayerStatistic} for a specific player
     * @param player the player whose {@link PlayerStatistic} wants to be accessed
     * @return this returns an {@link Optional} containing a {@link PlayerStatistic}
     * object of the player or an {@link Optional#empty()} if no Statistics for the player
     * could be found (Due to the Statistic not being fetched or the player not having a statistic yet)
     *
     * This method is similar to {@link StatisticsManager#getStatistic(Player)}
     * except it accepts an {@link OfflinePlayer} as a parameter
     */
    public Optional<PlayerStatistic> getStatistic(OfflinePlayer player) {
        return getStatistic(player.getUniqueId());
    }



    /**
     * This method gets the {@link PlayerStatistic} for a specific player
     * @param uuid the uuid of the player whose {@link PlayerStatistic} wants to be accessed
     * @return this returns an {@link Optional} containing a {@link PlayerStatistic}
     * object of the player or an {@link Optional#empty()} if no Statistics for the player
     * could be found (Due to the Statistic not being fetched or the player not having a statistic yet)
     */
    public Optional<PlayerStatistic> getStatistic(UUID uuid) {
        return Optional.ofNullable(playerStatistics.getOrDefault(uuid, null));
    }


    /**
     * This method fetches the {@link PlayerStatistic} of a player from the
     * MySQL-Database
     * @param uuid the uuid of the player whose {@link PlayerStatistic} wants to be fetched
     * @return this returns an {@link Optional} containing a {@link PlayerStatistic}
     * object of the player or an {@link Optional#empty()} if no Statistics for the player
     * could be found
     */
    private Optional<PlayerStatistic> fetchStatistics(UUID uuid) {
        Optional<String> userid = plugin.getUserData().getPlayerID(uuid);
        if(getStatistic(uuid).isPresent()) throw new RuntimeException("Statistics for this user are already fetched");
        if (!userid.isPresent()) throw new RuntimeException("No UserID found for given UUID.");
        try {

            PreparedStatement preparedStatement = SQL.prepareStatement("SELECT statistic, amount FROM plainstatistics WHERE userid = ?");
            preparedStatement.setString(1, userid.get());
            ResultSet resultSet = preparedStatement.executeQuery();

            HashMap<String, Integer> plainStatistics = new HashMap<>();
            while (resultSet.next()) {
                plainStatistics.put(resultSet.getString("statistic"), resultSet.getInt("amount"));
            }
            
            /*
            Retrieve material-statistics from different table
             */
            preparedStatement = SQL.prepareStatement("SELECT statistic, material, amount FROM materialstatistics WHERE userid = ?");
            preparedStatement.setString(1, userid.get());
            resultSet = preparedStatement.executeQuery();

            HashMap<String, HashMap<Material, Integer>> materialStatistics = new HashMap<>();
            HashMap<Material, Integer> innerMapMAT;
            while (resultSet.next()) {
                if(materialStatistics.containsKey(resultSet.getString("statistic"))) {
                    innerMapMAT = materialStatistics.get(resultSet.getString("statistic"));
                    innerMapMAT.put(Material.getMaterial(resultSet.getString("material")), resultSet.getInt("amount"));
                } else {
                    innerMapMAT = new HashMap<>();
                    innerMapMAT.put(Material.getMaterial(resultSet.getString("material")), resultSet.getInt("amount"));
                    materialStatistics.put(resultSet.getString("statistic"), innerMapMAT);
                }
            }

            preparedStatement = SQL.prepareStatement("SELECT statistic, entity, amount FROM entitystatistics WHERE userid = ?");
            preparedStatement.setString(1, userid.get());
            resultSet = preparedStatement.executeQuery();

            HashMap<String, HashMap<EntityType, Integer>> entityStatistics = new HashMap<>();
            HashMap<EntityType, Integer> innerMapENT;
            while (resultSet.next()) {
                if (entityStatistics.containsKey(resultSet.getString("statistic"))) {
                    innerMapENT = entityStatistics.get(resultSet.getString("statistic"));
                    innerMapENT.put(EntityType.valueOf(resultSet.getString("entity")), resultSet.getInt("amount"));
                } else {
                    innerMapENT = new HashMap<>();
                    innerMapENT.put(EntityType.valueOf(resultSet.getString("entity")), resultSet.getInt("amount"));
                    entityStatistics.put(resultSet.getString("statistic"), innerMapENT);
                }
            }

            PlayerStatistic statistic = new PlayerStatistic(uuid, userid.get(), plainStatistics, materialStatistics, entityStatistics, plugin);
            playerStatistics.put(uuid, statistic);
            return Optional.of(statistic);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }


    /**
     * This method allows for multiple PlayerStatistics to be fetched
     * by calling {@link StatisticsManager#fetchStatistics(UUID)} for
     * each player asynchronously
     * @param uuids the uuid of the player whose {@link PlayerStatistic} wants to be fetched
     */
    private void fetchStatistics(UUID... uuids) {
        Thread thread = new Thread(() -> {
            for (UUID uuid : uuids) {
                fetchStatistics(uuid);
            }
        });
        thread.start();
    }

    /**
     * This method returns the entire Map of fetched {@link PlayerStatistic}
     * @return this returns a {@link HashMap} containing all fetched {@link PlayerStatistic}
     */
    public HashMap<UUID, PlayerStatistic> getPlayerStatistics() {
        return playerStatistics;
    }



    /**
     * This method is meant to increase a {@link Statistic} with no (NONE) {@link Substatistic}
     * @param player the player whose statistic should be increased
     * @param statistic the statistic that wants to be increased
     * @param amount the value by which the statistic should be increased
     *
     * This method is similar to {@link PlayerStatistic#addStatistic(Statistic, int)}
     * but does not require the {@link PlayerStatistic} object of the player
     */
    public void addStatistic(Player player, Statistic statistic, int amount) {
        addStatistic(player, statistic, null, null, amount);
    }



    /**
     * This method is meant to increase a {@link Statistic} with a MATERIAL {@link Substatistic}
     * @param player the player whose statistic should be increased
     * @param statistic the statistic that wants to be increased
     * @param material define the {@link Material} of the statistic
     * @param amount the value by which the statistic should be increased
     *
     * This method is similar to {@link PlayerStatistic#addStatistic(Statistic, Material, int)}
     * but does not require the {@link PlayerStatistic} object of the player
     */
    public void addStatistic(Player player, Statistic statistic, Material material, int amount) {
        addStatistic(player, statistic, material, null, amount);
    }



    /**
     * This method is meant to increase a {@link Statistic} with an ENTITY {@link Substatistic}
     * @param player the player whose statistic should be increased
     * @param statistic the statistic that wants to be increased
     * @param entityType define the {@link EntityType} of a statistic
     * @param amount the value by which the statistic should be increased
     *
     * This method is similar to {@link PlayerStatistic#addStatistic(Statistic, EntityType, int)}
     * but does not require the {@link PlayerStatistic} object of the player
     */
    public void addStatistic(Player player, Statistic statistic, EntityType entityType, int amount) {
        addStatistic(player, statistic, null, entityType, amount);
    }


    /**
     * This method is meant to increase a {@link Statistic} with a MATERIAL {@link Substatistic}
     * @param player the player whose statistic should be increased
     * @param statistic the statistic that wants to be increased
     * @param material define the {@link Material} of the statistic
     * @param entityType define the {@link EntityType} of a statistic
     * @param amount the value by which the statistic should be increased
     *
     * This method is similar to {@link PlayerStatistic#addStatistic(Statistic, Material, int)}
     * but does not require the {@link PlayerStatistic} object of the player
     */
    private void addStatistic(Player player, Statistic statistic, Material material, EntityType entityType, int amount) {
        plugin.getStatisticsManager().getStatistic(player).orElseGet(() -> plugin.getStatisticsManager().fetchOrCreate(player)).addStatistic(statistic, material, entityType, amount);
        }


    /**
     * This method is meant to create a new {@link PlayerStatistic} object
     * @param player the player whose {@link PlayerStatistic} should be created
     * @return this returns a new {@link PlayerStatistic} object for the player
     */
    private PlayerStatistic createStatistic(Player player) {
        plugin.getUserData().registerPlayer(player);
        return createStatistic(player.getUniqueId());
    }


    /**
     * This method is meant to create a new {@link PlayerStatistic} object
     * @param uuid the uuid of the player whose {@link PlayerStatistic} should be created
     * @return this returns a new {@link PlayerStatistic} object for the player
     */
    private PlayerStatistic createStatistic(UUID uuid) {
        PlayerStatistic statistic = new PlayerStatistic(uuid, plugin.getUserData().getPlayerID(uuid).orElseThrow(() -> new RuntimeException("Player was not registered due to an unknown error")), plugin);
        playerStatistics.put(uuid, statistic);
        return statistic;
    }


    /**
     * This method is meant to either fetch {@link StatisticsManager#fetchStatistics(UUID)} an existing {@link PlayerStatistic}
     * from the MySQL-Database or {@link StatisticsManager#createStatistic(Player)}
     * if no Statistic could be found on the database
     * @param player the player whose {@link PlayerStatistic} should be fetched/created
     * @return this returns either a new {@link PlayerStatistic} for the player
     * or an existing one fetched from the MySQL-Database
     */
    public PlayerStatistic fetchOrCreate(Player player) {
        return fetchOrCreate(player.getUniqueId());
    }



    /**
     * This method is meant to either fetch {@link StatisticsManager#fetchStatistics(UUID)} an existing {@link PlayerStatistic}
     * from the MySQL-Database or {@link StatisticsManager#createStatistic(Player)}
     * if no Statistic could be found on the database
     * @param uuid the uuid of the player whose {@link PlayerStatistic} should be fetched/created
     * @return this returns either a new {@link PlayerStatistic} for the player
     * or an existing one fetched from the MySQL-Database
     */
    public PlayerStatistic fetchOrCreate(UUID uuid) {
        if (getStatistic(uuid).isPresent()) return getStatistic(uuid).get();
        Optional<PlayerStatistic> statistic = fetchStatistics(uuid);
        return statistic.orElseGet(() -> createStatistic(uuid));
    }

    /**
     * This method is meant to save all currently cached {@link PlayerStatistic}
     * from {@link StatisticsManager#getPlayerStatistics()} asynchronously
     */
    public void saveStatistics() {

        new Thread(() -> {
                for (Map.Entry<UUID, PlayerStatistic> entry : playerStatistics.entrySet()) {
                    UUID uuid = entry.getKey();
                    PlayerStatistic statistic = entry.getValue();
                    Optional<String> userID = plugin.getUserData().getPlayerID(uuid);
                    if(!userID.isPresent()) continue;
                    statistic.savePlainStatistics();
                    statistic.saveMaterialStatistics();
                    statistic.saveEntityStatistics();
                }
        }).start();

    }


    /**
     * This method is for internal saving only
     * @param clearCache decides whether the cache should be cleared after saving or not
     */
    void saveStatistics(boolean clearCache) {

        plugin.getLogger().log(Level.INFO, "All cached Statistics will now be saved...");
            for (Map.Entry<UUID, PlayerStatistic> entry : playerStatistics.entrySet()) {
                UUID uuid = entry.getKey();
                PlayerStatistic statistic = entry.getValue();
                Optional<String> userID = plugin.getUserData().getPlayerID(uuid);
                if(!userID.isPresent()) continue;
                statistic.savePlainStatistics();
                statistic.saveMaterialStatistics();
                statistic.saveEntityStatistics();
            }
        plugin.getLogger().log(Level.INFO, "All Statistics have successfully been saved");
            if(clearCache) {
                final int entries = playerStatistics.size();
                playerStatistics.clear();
                plugin.getLogger().log(Level.INFO, entries + " cached entries have been cleared.");
            }

    }
}
