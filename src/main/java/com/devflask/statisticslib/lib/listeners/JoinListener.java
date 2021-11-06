package com.devflask.statisticslib.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

public class JoinListener implements Listener {

    private final PlayerStatistics plugin;

    public JoinListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        plugin.getUserData().registerPlayer(event.getPlayer());
        plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.PLAYER_JOINS, 1);
    }

}
