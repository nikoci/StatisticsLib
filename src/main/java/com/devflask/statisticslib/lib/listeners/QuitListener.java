package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public record QuitListener(PlayerStatistics plugin) implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.PLAYER_QUITS, 1);
    }

}
