package org.dreamndelight.playerstatistics.listeners;

import org.dreamndelight.playerstatistics.main.Main;
import org.dreamndelight.playerstatistics.enums.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Main.getInstance().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.BLOCKS_BROKEN, event.getBlock().getType(), 1);
    }

}
