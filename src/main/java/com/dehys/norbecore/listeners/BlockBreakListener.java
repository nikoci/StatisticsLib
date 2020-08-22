package com.dehys.norbecore.listeners;

import com.dehys.norbecore.main.Main;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Main.getInstance().getStatisticsManager().getStatistic(event.getPlayer()).orElse(Main.getInstance().getStatisticsManager().fetchOrCreate(event.getPlayer())).addStatistic(Statistic.MINE_BLOCK, 1);
    }

}
