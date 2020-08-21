package com.dehys.norbecore.data;

import java.util.UUID;

public class PlayerStatistic {

    private final UUID uuid;
    private final String playerID;

    public PlayerStatistic(final UUID uuid, final String playerID) {
        this.uuid = uuid;
        this.playerID = playerID;
    }

    public static PlayerStatistic loadExisting(UUID uuid, String playerID) {
        //TODO: Retrieve player statistics from SQL and put them accordingly
        return new PlayerStatistic(uuid, playerID);
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getPlayerID() {
        return playerID;
    }
}
