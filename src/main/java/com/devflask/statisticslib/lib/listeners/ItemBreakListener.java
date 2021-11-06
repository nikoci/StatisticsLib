package com.devflask.statisticslib.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

public class ItemBreakListener implements Listener {

    private final PlayerStatistics plugin;

    public ItemBreakListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemBreak(final PlayerItemBreakEvent event) {
        plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_BROKEN, event.getBrokenItem().getType(), 1);
    }

}
