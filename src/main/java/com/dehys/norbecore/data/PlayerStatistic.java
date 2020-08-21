package com.dehys.norbecore.data;

import javafx.scene.paint.Material;

import java.util.HashMap;
import java.util.UUID;

public class PlayerStatistic {

    private final UUID uuid;
    private final String playerID;
    private HashMap<Material, Integer> blocksBroken;

    public PlayerStatistic(final UUID uuid, final String playerID) {
        this.uuid = uuid;
        this.playerID = playerID;
        blocksBroken = new HashMap<>();
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

    public Integer getBrokenBlocks(Material material) {
        return blocksBroken.getOrDefault(material, 0);
    }

    public Integer getTotalBrokenBlocks() {
        return blocksBroken.values().stream().mapToInt(Integer::intValue).sum();
    }
}
