package com.devflask.statisticslib.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

public class BlockBreakListener implements Listener {

    private final PlayerStatistics plugin;

    public BlockBreakListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(final BlockBreakEvent event) {
        if (!event.isCancelled()) {
            plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.BLOCKS_BROKEN, event.getBlock().getType(), 1);
        }
    }

}
