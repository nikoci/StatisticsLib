package org.dreamndelight.playerstatistics.data;


import org.jetbrains.annotations.Nullable;
import org.dreamndelight.playerstatistics.enums.Statistic;
import org.dreamndelight.playerstatistics.enums.Substatistic;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class PlayerStatistic {

    private final UUID uuid;
    private final String userid;
    private final HashMap<String, Integer> plainStatistics;
    private final HashMap<String, HashMap<Material, Integer>> materialStatistics;
    private final HashMap<String, HashMap<EntityType, Integer>> entityStatistics;

    /**
     * This is the main constructor used for creating a new and empty {@link PlayerStatistic} object
     *
     * @param uuid   the uuid of the player this object will be destined to
     * @param userid another, shorter id of the player, which is used internally to access data from the SQL-Database
     */
    public PlayerStatistic(final UUID uuid, final String userid) {
        this.uuid = uuid;
        this.userid = userid;

        plainStatistics = new HashMap<>();
        materialStatistics = new HashMap<>();
        entityStatistics = new HashMap<>();
    }


    /**
     * This is the constructor used for creating a new {@link PlayerStatistic} object
     * for a player who already has existing statistics
     *
     * @param uuid               the uuid of the player this object will be destined to
     * @param userid             another, shorter id of the player, which is used internally to access data from the SQL-Database
     * @param plainStatistics    a map of all statistics which have no (NONE) {@link Substatistic}
     * @param materialStatistics a map of all statistics which have a MATERIAL {@link Substatistic}
     * @param entityStatistics   a map of all statistics which have an ENTITY {@link Substatistic}
     */
    public PlayerStatistic(final UUID uuid, final String userid, HashMap<String, Integer> plainStatistics,
                           HashMap<String, HashMap<Material, Integer>> materialStatistics, HashMap<String, HashMap<EntityType, Integer>> entityStatistics) {
        this.uuid = uuid;
        this.userid = userid;
        this.plainStatistics = plainStatistics;
        this.materialStatistics = materialStatistics;
        this.entityStatistics = entityStatistics;

    }


    /**
     * Getter for players UUID
     *
     * @return returns the {@link UUID} of the {@link org.bukkit.entity.Player}
     */
    public UUID getUUID() {
        return uuid;
    }


    /**
     * Getter for players UserID
     *
     * @return returns the UserID of a player, which is an internal ID for accessing data from the SQL-Database
     */
    public String getUserID() {
        return userid;
    }


    /**
     * This method is meant to increase a {@link Statistic} with no (NONE) {@link Substatistic}
     *
     * @param statistic the statistic that wants to be increased
     * @param amount    the value by which the statistic should be increased
     */
    public void addStatistic(@Nullable Statistic statistic, int amount) {
        addStatistic(statistic, null, null, amount);
    }

    /**
     * This method is meant to increase a {@link Statistic} with a MATERIAL {@link Substatistic}
     *
     * @param statistic the statistic that wants to be increased
     * @param material  define the {@link Material} of the statistic
     * @param amount    the value by which the statistic should be increased
     */
    public void addStatistic(@Nullable Statistic statistic, Material material, int amount) {
        addStatistic(statistic, material, null, amount);
    }

    /**
     * This method is meant to increase a {@link Statistic} with a ENTITY {@link Substatistic}
     *
     * @param statistic  the statistic that wants to be increased
     * @param entityType define the {@link EntityType} of the statistic
     * @param amount     the value by which the statistic should be increased
     */
    public void addStatistic(@Nullable Statistic statistic, EntityType entityType, int amount) {
        addStatistic(statistic, null, entityType, amount);
    }

    void addStatistic(@Nullable Statistic statistic, Material material, EntityType entityType, int amount) {
        switch (statistic.getSubstatistic()) {
            case MATERIAL:
                assert material != null;
                addMaterialStatistic(statistic, material, amount);
                break;

            case ENTITY:
                assert entityType != null;


            case NONE:
                addPlainStatistic(statistic, amount);
        }
    }

    /**
     * This method is meant for {@link Statistic} with no (NONE) {@link Substatistic}
     *
     * @param statistic the statistic that wants to be retrieved
     * @return returns the statistic selected
     * If a Statistic with Substatistics has been selected,
     * this method will return the sum of all the Substatistics of that Statistic
     */
    public int getStatistic(@Nullable Statistic statistic) {
        return getStatistic(statistic, null, null);
    }

    /**
     * This method is meant for {@link Statistic} with a MATERIAL {@link Substatistic}
     *
     * @param statistic the statistic that wants to be retrieved
     * @param material  define the {@link Material} of the statistic
     * @return returns the statistic specified by the {@link Material}
     * Will return the sum of all the Substatistics of that Statistic if material = null
     */
    public int getStatistic(@Nullable Statistic statistic, Material material) {
        return getStatistic(statistic, material, null);
    }

    /**
     * This method is meant for {@link Statistic} with a ENTITY {@link Substatistic}
     *
     * @param statistic  the statistic that wants to be retrieved
     * @param entityType define the {@link EntityType} of the statistic
     * @return returns the statistic specified by the {@link EntityType}
     * Will return the sum of all the Substatistics of that Statistic if entityType = null
     */
    public int getStatistic(@Nullable Statistic statistic, EntityType entityType) {
        return getStatistic(statistic, null, entityType);
    }

    /**
     * This method will save all of the {@link Statistic}
     * that have an UNDEFINED {@link Substatistic} to the MySQL-Database
     * Running this method directly will cause it to run sync
     * If you want to run this method async, use {@link StatisticsManager#saveStatistics()}
     * or run the method in another {@link Thread}
     */
    public void savePlainStatistics() {
        try {
            PreparedStatement plainStatement = SQL.prepareStatement("INSERT INTO plainstatistics (userid, statistic, amount) VALUES (?, ?, ?)");

            plainStatement.setString(1, userid);
            for (Map.Entry<String, Integer> entry : plainStatistics.entrySet()) {
                plainStatement.setString(2, entry.getKey());
                plainStatement.setInt(3, entry.getValue());
                plainStatement.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * This method will save all of the {@link Statistic}
     * that have a MATERIAL {@link Substatistic} to the MySQL-Database
     * Running this method directly will cause it to run sync
     * If you want to run this method async, use {@link StatisticsManager#saveStatistics()}
     * or run the method in another {@link Thread}
     */
    public void saveMaterialStatistics() {
        try {
            /*
            PreparedStatement checkStatement = SQL.prepareStatement("SELECT amount FROM materialstatistics WHERE userid = ? AND statistic = ? AND material = ?");
            checkStatement.setString(1, userid);
            PreparedStatement materialStatementINSERT = SQL.prepareStatement("INSERT INTO materialstatistics (userid, statistic, material, amount) VALUES (?, ?, ?, ?)");
            materialStatementINSERT.setString(1, userid);
            PreparedStatement materialStatementUPDATE = SQL.prepareStatement("UPDATE materialstatistics SET amount = ? WHERE userid = ? AND statistic = ? AND material = ?");
            materialStatementUPDATE.setString(1, userid);
             */
            PreparedStatement materialStatement = SQL.prepareStatement("INSERT INTO materialstatistics (userid, statistic, material, amount) VALUES (?, ?, ?, ?)");
            materialStatement.setString(1, userid);
            for (String statistic : materialStatistics.keySet()) {
                HashMap<Material, Integer> innerMap = materialStatistics.get(statistic);
                for (Map.Entry<Material, Integer> entry : innerMap.entrySet()) {
                    materialStatement.setString(2, statistic);
                    materialStatement.setString(3, entry.getKey().name());
                    materialStatement.setInt(4, entry.getValue());
                    materialStatement.executeUpdate();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * This method will save all of the {@link Statistic}
     * that have an UNDEFINED {@link Substatistic} to the MySQL-Database
     * Running this method directly will cause it to run sync
     * If you want to run this method async, use {@link StatisticsManager#saveStatistics()}
     * or run the method in another {@link Thread}
     */
    public void saveEntityStatistics() {
        try {
            PreparedStatement entityStatement = SQL.prepareStatement("INSERT INTO plainstatistics (userid, statistic, entity, amount) VALUES (?, ?, ?, ?)");
            entityStatement.setString(1, userid);
            for (String statistic : entityStatistics.keySet()) {
                HashMap<EntityType, Integer> innerMap = entityStatistics.get(statistic);
                for (Map.Entry<EntityType, Integer> entry : innerMap.entrySet()) {
                    entityStatement.setString(2, statistic);
                    entityStatement.setString(3, entry.getKey().name());
                    entityStatement.setInt(4, entry.getValue());
                    entityStatement.executeUpdate();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method is used internally to get a statistic of any type
     *
     * @param statistic  the {@link Statistic} that wants to be retrieved
     * @param material   define the {@link Material} of the statistic
     * @param entityType define the {@link EntityType} of the statistic
     * @return returns the value of the {@link Statistic} or 0 if it cannot be found.
     */
    private int getStatistic(@Nullable Statistic statistic, Material material, EntityType entityType) {
        switch (statistic.getSubstatistic()) {

            case MATERIAL:
                return getMaterialStatistic(statistic).isPresent() ?
                        (material != null ? getMaterialStatistic(statistic).get().getOrDefault(material, 0)
                                : getMaterialStatistic(statistic).get().values().stream().mapToInt(Integer::intValue).sum()) : 0;
            case ENTITY:
                return getEntityStatistic(statistic).isPresent() ?
                        (entityType != null ? getEntityStatistic(statistic).get().getOrDefault(entityType, 0)
                                : getEntityStatistic(statistic).get().values().stream().mapToInt(Integer::intValue).sum()) : 0;
            case NONE:
                return getPlainStatistic(statistic);

            default:
                return 0;
        }
    }


    private Integer getPlainStatistic(@Nullable Statistic statistic) {
        return plainStatistics.getOrDefault(statistic.getKey(), 0);
    }

    private Optional<HashMap<Material, Integer>> getMaterialStatistic(Statistic statistic) {
        if (statistic.getSubstatistic() != Substatistic.MATERIAL) return Optional.empty();
        return Optional.ofNullable(materialStatistics.get(statistic.getKey()));
    }

    private Optional<HashMap<EntityType, Integer>> getEntityStatistic(Statistic statistic) {
        if (statistic.getSubstatistic() != Substatistic.ENTITY) return Optional.empty();
        return Optional.ofNullable(entityStatistics.get(statistic.getKey()));
    }

    private void addPlainStatistic(Statistic statistic, int value) {
        final String key = statistic.getKey();
        if (statistic.hasSubstatistic()) {
            throw new IllegalArgumentException("Given Statistic has substatistics");
        }
        if (!plainStatistics.containsKey(key)) {
            plainStatistics.put(key, value);
        } else {
            plainStatistics.put(key, plainStatistics.get(key) + value);
        }
    }

    private void addMaterialStatistic(Statistic statistic, Material material, int value) {
        final String key = statistic.getKey();
        if (statistic.getSubstatistic() != Substatistic.MATERIAL) {
            throw new IllegalArgumentException("Given Statistic has no material substatistics");
        }
        HashMap<Material, Integer> materialMap;
        if (!materialStatistics.containsKey(key)) {
            materialMap = new HashMap<>();
            materialMap.put(material, value);
            materialStatistics.put(key, materialMap);
        } else {
            materialMap = materialStatistics.get(key);
            materialMap.put(material, (materialMap.containsKey(material) ? materialMap.get(material) + value : value));
        }
    }

    private void addEntityStatistic(Statistic statistic, EntityType entityType, int value) {
        final String key = statistic.getKey();
        if (statistic.getSubstatistic() != Substatistic.ENTITY) {
            throw new IllegalArgumentException("Given Statistic has no entity substatistics");
        }
        HashMap<EntityType, Integer> entityMap;
        if (!entityStatistics.containsKey(key)) {
            entityMap = new HashMap<>();
            entityMap.put(entityType, value);
            entityStatistics.put(key, entityMap);
        } else {
            entityMap = entityStatistics.get(key);
            entityMap.put(entityType, (entityMap.containsKey(entityType) ? entityMap.get(entityType) + value : value));
        }
    }
}
