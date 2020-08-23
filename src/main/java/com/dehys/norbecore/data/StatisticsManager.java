package com.dehys.norbecore.data;

import com.dehys.norbecore.exceptions.StatisticAlreadyLoadedException;
import com.dehys.norbecore.main.Main;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import com.dehys.norbecore.enums.Statistic;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class StatisticsManager {

    private HashMap<UUID, PlayerStatistic> playerStatistics;

    public StatisticsManager() {
        playerStatistics = new HashMap<>();
    }

    public void loadStatistic(Player player) throws StatisticAlreadyLoadedException {
        loadStatistic(player.getUniqueId());
    }

    public void loadStatistic(OfflinePlayer offlinePlayer) throws StatisticAlreadyLoadedException {
        loadStatistic(offlinePlayer.getUniqueId());
    }

    public void loadStatistic(UUID uuid) throws StatisticAlreadyLoadedException {
        if (playerStatistics.containsKey(uuid)) {
            throw new StatisticAlreadyLoadedException("The statistics for that player are already loaded");
        }
        fetchStatistics(uuid);
    }

    public Optional<PlayerStatistic> getStatistic(Player player) {
        return getStatistic(player.getUniqueId());
    }

    public Optional<PlayerStatistic> getStatistic(OfflinePlayer player) {
        return getStatistic(player.getUniqueId());
    }

    public Optional<PlayerStatistic> getStatistic(UUID uuid) {
        return Optional.ofNullable(playerStatistics.get(uuid));
    }


    private Optional<PlayerStatistic> fetchStatistics(UUID uuid) {
        Optional<String> userid = Main.getInstance().getUserData().getPlayerID(uuid);
        if (!userid.isPresent()) throw new RuntimeException("No UserID found for given UUID.");
        try {

            PreparedStatement preparedStatement = SQL.prepareStatement("SELECT statistic, amount FROM plainstatistics WHERE userid = ?");
            preparedStatement.setString(1, userid.get());
            ResultSet resultSet = preparedStatement.executeQuery();

            HashMap<String, Integer> plainStatistics = new HashMap<>();
            while (resultSet.next()) {
                plainStatistics.put(resultSet.getString("statistic"), resultSet.getInt("amount"));
            }
            preparedStatement = SQL.prepareStatement("SELECT statistic, material, amount FROM materialstatistics WHERE userid = ?");
            preparedStatement.setString(1, userid.get());
            resultSet = preparedStatement.executeQuery();

            HashMap<String, HashMap<Material, Integer>> materialStatistics = new HashMap<>();
            HashMap<Material, Integer> innerMap;
            while (resultSet.next()) {
                if(materialStatistics.containsKey(resultSet.getString("statistic"))) {
                    innerMap = materialStatistics.get(resultSet.getString("statistic"));
                    innerMap.put(Material.getMaterial(resultSet.getString("material")), resultSet.getInt("amount"));
                } else {
                    innerMap = new HashMap<>();
                    innerMap.put(Material.getMaterial(resultSet.getString("material")), resultSet.getInt("amount"));
                    materialStatistics.put(resultSet.getString("statistic"), innerMap);
                }
            }
            return Optional.of(new PlayerStatistic(uuid, userid.get(), plainStatistics, materialStatistics));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }




    private Optional<PlayerStatistic> fetchStatistics(UUID... uuids) {
        Thread thread = new Thread(() -> {
            for (UUID uuid : uuids) {
                fetchStatistics(uuid);
            }
        });
        thread.start();
        return Optional.empty();
    }

    public HashMap<UUID, PlayerStatistic> getPlayerStatistics() {
        return playerStatistics;
    }

    public void addStatistic(Player player, Statistic statistic, int amount) {
        addStatistic(player, statistic, null, amount);
    }

    public void addStatistic(Player player, Statistic statistic, Material material, int amount) {
        Main.getInstance().getStatisticsManager().getStatistic(player).orElse(Main.getInstance().getStatisticsManager().fetchOrCreate(player)).addStatistic(statistic, material, amount);
    }

    public PlayerStatistic createStatistic(Player player) {
        Main.getInstance().getUserData().registerPlayer(player);
        return createStatistic(player.getUniqueId());
    }

    private PlayerStatistic createStatistic(UUID uuid) {
        PlayerStatistic statistic = new PlayerStatistic(uuid, Main.getInstance().getUserData().getPlayerID(uuid).orElseThrow(() -> new RuntimeException("Player was not registered due to an unknown error")));
        playerStatistics.put(uuid, statistic);
        return statistic;
    }

    public PlayerStatistic fetchOrCreate(Player player) {
        return fetchOrCreate(player.getUniqueId());
    }

    public PlayerStatistic fetchOrCreate(UUID uuid) {
        Optional<PlayerStatistic> statistic = fetchStatistics(uuid);
        return statistic.orElseGet(() -> createStatistic(uuid));
    }
}
