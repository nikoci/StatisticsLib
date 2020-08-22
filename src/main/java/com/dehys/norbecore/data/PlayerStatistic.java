package com.dehys.norbecore.data;


import org.bukkit.Material;
import org.bukkit.Statistic;

import java.util.HashMap;
import java.util.UUID;

public class PlayerStatistic {

    private final UUID uuid;
    private final String playerID;
    private HashMap<Material, Integer> blocksBroken;
    private int deaths;

    public PlayerStatistic(final UUID uuid, final String playerID) {
        this.uuid = uuid;
        this.playerID = playerID;
        blocksBroken = new HashMap<>();
        deaths = 0;
    }

    public static PlayerStatistic loadExisting(UUID uuid, String playerID) {
        //TODO.md: Retrieve player statistics from SQL and put them accordingly
        return new PlayerStatistic(uuid, playerID);
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getPlayerID() {
        return playerID;
    }


    public HashMap<Material, Integer> getBlocksBroken() {
        return blocksBroken;
    }

    public void addStatistic(Statistic statistic, int amount) {
        addStatistic(statistic, null, amount);
    }

    public void addStatistic(Statistic statistic, Material material, int amount) {
        switch (statistic) {
            case MINE_BLOCK:
                if (this.blocksBroken.containsKey(material)) {
                    this.blocksBroken.put(material, this.blocksBroken.get(material) + amount);
                } else {
                    this.blocksBroken.put(material, amount);
                }
                break;
            case DEATHS:

                break;
        }
    }

    public int getBrokenBlocks(Material material) {
        return blocksBroken.getOrDefault(material, 0);
    }

    public int getTotalBrokenBlocks() {
        return blocksBroken.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getDeaths() {
        return deaths;
    }
}
