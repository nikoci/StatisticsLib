package com.dehys.norbecore.data;


import com.sun.istack.internal.NotNull;
import org.bukkit.Material;
import org.bukkit.Statistic;

import java.util.HashMap;
import java.util.UUID;

public class PlayerStatistic {

    private final UUID uuid;
    private final String playerID;
    private final HashMap<Material, Integer> blocksBroken;
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

    public void addStatistic(@NotNull Statistic statistic, int amount) {
        addStatistic(statistic, null, amount);
    }

    public void addStatistic(@NotNull Statistic statistic, Material material, int amount) {
        switch (statistic) {
            case MINE_BLOCK:
                if (this.blocksBroken.containsKey(material)) {
                    this.blocksBroken.put(material, this.blocksBroken.get(material) + amount);
                } else {
                    this.blocksBroken.put(material, amount);
                }
                break;
            case DEATHS:
                this.deaths++;
                break;
        }
    }

    public int getStatistic(@NotNull Statistic statistic) {
        return getStatistic(statistic, null);
    }

    public int getStatistic(@NotNull Statistic statistic, Material material) {
        switch (statistic) {
            case MINE_BLOCK:
                if (material != null)
                    return this.blocksBroken.getOrDefault(material, 0);
                else
                    return blocksBroken.values().stream().mapToInt(Integer::intValue).sum();
            case DEATHS:
                return this.deaths;

            default:
                return 0;
        }
    }


}
