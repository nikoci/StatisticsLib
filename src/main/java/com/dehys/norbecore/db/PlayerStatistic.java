package com.dehys.norbecore.db;

import java.util.UUID;

public class PlayerStatistic {

    private final UUID uuid;

    public PlayerStatistic(final UUID uuid) {
        this.uuid = uuid;
    }

    public static PlayerStatistic loadExisting(UUID uuid) {
        //TODO: Retrieve player statistics from SQL and put them accordingly
        return new PlayerStatistic(uuid);
    }

    public UUID getUUID() {
        return uuid;
    }
}
