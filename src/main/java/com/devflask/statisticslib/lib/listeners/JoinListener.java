package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final record JoinListener(StatisticsPlugin statisticsPlugin) implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        statisticsPlugin.getUserData().registerPlayer(event.getPlayer());
        statisticsPlugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.PLAYER_JOINS, 1);
    }

}
