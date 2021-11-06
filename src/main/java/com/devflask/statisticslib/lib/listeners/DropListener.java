package com.devflask.statisticslib.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

public class DropListener implements Listener {

    private final PlayerStatistics plugin;

    public DropListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDrop(final PlayerDropItemEvent event) {
        if (!event.isCancelled()) {
            plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_DROPPED, event.getItemDrop().getItemStack().getType(), event.getItemDrop().getItemStack().getAmount());
        }
    }


}
