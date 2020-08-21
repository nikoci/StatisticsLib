package com.dehys.norbecore.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class StatisticsManager {

    private HashMap<UUID, PlayerStatistic> playerStatistics;

    public StatisticsManager() {
        playerStatistics = new HashMap<>();
    }

    public void loadStatistics(Player player) {
        loadStatistics(player.getUniqueId());
    }

    public void loadStatistics(OfflinePlayer offlinePlayer) {
        loadStatistics(offlinePlayer.getUniqueId());
    }

    public void loadStatistics(UUID uuid) {

    }

    public HashMap<UUID, PlayerStatistic> getPlayerStatistics() {
        return playerStatistics;
    }
}
