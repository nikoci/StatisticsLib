package org.dreamndelight.playerstatistics.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.dreamndelight.playerstatistics.enums.Statistic;
import org.dreamndelight.playerstatistics.main.PlayerStatistics;

public class ItemBreakListener implements Listener {

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent event) {
        PlayerStatistics.get().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_BROKEN, event.getBrokenItem().getType(), 1);
    }

}
