package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class QuitListener implements Listener {

    private final PlayerStatistics plugin;

    public QuitListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public final void onQuit(PlayerQuitEvent event) {
        plugin.getLib().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.PLAYER_QUITS, 1);
    }

}
