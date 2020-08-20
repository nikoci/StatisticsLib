package com.dehys.norbecore.data;

import com.dehys.norbecore.exceptions.StatisticAlreadyLoadedException;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
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
        fetchStatistic(uuid);
    }


    private void fetchStatistic(UUID uuid) {
        //TODO: Fetch statistics related to that uuid
    }

    private void fetchStatistics(UUID... uuids) {
        //TODO: Fetch statistics related to all of the uuids
    }

    public HashMap<UUID, PlayerStatistic> getPlayerStatistics() {
        return playerStatistics;
    }
}
