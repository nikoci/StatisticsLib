package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class DropListener implements Listener {

    private final PlayerStatistics plugin;

    public DropListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDrop(final PlayerDropItemEvent event) {
        plugin.getLib().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_DROPPED, event.getItemDrop().getItemStack().getType(), event.getItemDrop().getItemStack().getAmount());
    }


}
