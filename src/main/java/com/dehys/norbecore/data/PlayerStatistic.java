package com.dehys.norbecore.data;


import com.dehys.norbecore.enums.Statistic;
import com.dehys.norbecore.enums.Substatistic;
import com.sun.istack.internal.NotNull;
import org.bukkit.Material;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class PlayerStatistic {

    private final UUID uuid;
    private final String userid;
    private final HashMap<String, Integer> plainStatistics;
    private final HashMap<String, HashMap<Material, Integer>> materialStatistics;
    //private int deaths, playerKills, mobKills, droppedItems, brokenItems, damageDealt, damageTaken, itemsCrafted;


    public PlayerStatistic(final UUID uuid, final String userid) {
        this.uuid = uuid;
        this.userid = userid;

        plainStatistics = new HashMap<>();
        materialStatistics = new HashMap<>();
    }

    public PlayerStatistic(final UUID uuid, final String userid, HashMap<String, Integer> plainStatistics, HashMap<String, HashMap<Material, Integer>> materialStatistics) {
        this.uuid = uuid;
        this.userid = userid;
        this.plainStatistics = plainStatistics;
        this.materialStatistics = materialStatistics;

    }

    public UUID getUUID() {
        return uuid;
    }

    public String getUserid() {
        return userid;
    }



    public void addStatistic(@NotNull Statistic statistic, Material material, int amount) {
       switch (statistic.getSubstatistic()) {
           case MATERIAL:
               assert material!=null;
               addMaterialStatistic(statistic, material, amount);
               break;

           case ENTITY:
               //Add when needed

           case NONE:
               addPlainStatistic(statistic, amount);
       }
    }

    public int getStatistic(@NotNull Statistic statistic) {
        return getStatistic(statistic, null);
    }

    public int getStatistic(@NotNull Statistic statistic, Material material) {
        switch (statistic.getSubstatistic()) {
            case MATERIAL:
                assert material!=null;
                return getMaterialStatistic(statistic).isPresent() ? getMaterialStatistic(statistic).get().getOrDefault(material, 0) : 0;
            case ENTITY:
                return 0;
                //add when needed
            case NONE:
                return getPlainStatistic(statistic);

            default:
                return 0;
        }
    }

    private Integer getPlainStatistic(@NotNull Statistic statistic) {
        return plainStatistics.getOrDefault(statistic.getKey(), 0);
    }

    private Optional<HashMap<Material, Integer>> getMaterialStatistic(Statistic statistic) {
        if(!statistic.hasSubstatistic()) return Optional.empty();
        return Optional.ofNullable(materialStatistics.get(statistic.getKey()));
    }

    private void addPlainStatistic(Statistic statistic, int value) {
        final String key = statistic.getKey();
        if(statistic.hasSubstatistic()) {
            throw new IllegalArgumentException("Given Statistic has substatistics");
        }
        if(!plainStatistics.containsKey(key)) {
            plainStatistics.put(key, value);
        } else {
            plainStatistics.put(key, plainStatistics.get(key)+value);
        }
    }

    private void addMaterialStatistic(Statistic statistic, Material material, int value) {
        final String key = statistic.getKey();
        if(statistic.getSubstatistic() != Substatistic.MATERIAL) {
            throw new IllegalArgumentException("Given Statistic has no material substatistics");
        }
        HashMap<Material, Integer> materialMap;
        if(!materialStatistics.containsKey(key)) {
            materialMap = new HashMap<>();
            materialMap.put(material, value);
            materialStatistics.put(key, materialMap);
        } else {
            materialMap = materialStatistics.get(key);
            materialMap.put(material, materialMap.get(material)+value);
        }
    }

    public void addStatistic(@NotNull Statistic statistic, int amount) {
        addStatistic(statistic, null, amount);
    }


}
