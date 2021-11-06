package com.devflask.statisticslib.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

public class BlockPlaceListener implements Listener {

    private final PlayerStatistics plugin;

    public BlockPlaceListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (!event.isCancelled()) {
            plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.BLOCKS_PLACED, event.getBlockPlaced().getType(), 1);
        }
    }

}
