package com.dehys.norbecore.data;


import com.sun.istack.internal.NotNull;
import org.bukkit.Material;
import org.bukkit.Statistic;

import java.util.HashMap;
import java.util.UUID;

public class PlayerStatistic {

    private final UUID uuid;
    private final String userid;
    private final HashMap<Material, Integer> blocksBroken;
    private int deaths, playerKills, mobKills, droppedItems;


    public PlayerStatistic(final UUID uuid, final String userid) {
        this.uuid = uuid;
        this.userid = userid;
        blocksBroken = new HashMap<>();
        deaths = 0;
    }

    public PlayerStatistic(final UUID uuid, final String userid, HashMap<Material, Integer> blocksBroken, int deaths) {
        this.uuid = uuid;
        this.userid = userid;
        this.blocksBroken = blocksBroken;
        this.deaths = deaths;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getUserid() {
        return userid;
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
                assert material != null;
                if (this.blocksBroken.containsKey(material)) {
                    this.blocksBroken.put(material, this.blocksBroken.get(material) + amount);
                } else {
                    this.blocksBroken.put(material, amount);
                }
                break;
            case DEATHS:
                this.deaths += amount;
                break;

            case PLAYER_KILLS:
                this.playerKills += amount;
                break;

            case MOB_KILLS:
                this.mobKills += amount;
                break;

            case DROP_COUNT:
            case DROP:
                this.droppedItems += amount;
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

            case PLAYER_KILLS:
                return this.playerKills;

            case MOB_KILLS:
                return this.mobKills;

            case DROP_COUNT:
            case DROP:
                return this.droppedItems;

            default:
                return 0;
        }
    }


}
