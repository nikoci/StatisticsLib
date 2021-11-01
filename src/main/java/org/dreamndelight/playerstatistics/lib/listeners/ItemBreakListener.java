package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class ItemBreakListener implements Listener {

    private final PlayerStatistics plugin;

    public ItemBreakListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemBreak(final PlayerItemBreakEvent event) {
        plugin.getLib().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_BROKEN, event.getBrokenItem().getType(), 1);
    }

}
