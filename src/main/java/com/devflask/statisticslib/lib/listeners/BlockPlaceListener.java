package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public record BlockPlaceListener(StatisticsPlugin statisticsPlugin) implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (!event.isCancelled()) {
            statisticsPlugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.BLOCKS_PLACED, event.getBlockPlaced().getType(), 1);
        }
    }

}
