package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public record QuitListener(StatisticsPlugin statisticsPlugin) implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        statisticsPlugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.PLAYER_QUITS, 1);
    }

}
