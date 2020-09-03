package org.dreamndelight.playerstatistics.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.dreamndelight.playerstatistics.enums.Statistic;
import org.dreamndelight.playerstatistics.main.PlayerStatistics;

public class BlockBreakListener implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        PlayerStatistics.get().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.BLOCKS_BROKEN, event.getBlock().getType(), 1);
    }

}
