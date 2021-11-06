package com.devflask.statisticslib.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

public class ConsumeItemListener implements Listener {

    private final PlayerStatistics plugin;

    public ConsumeItemListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onItemConsume(final PlayerItemConsumeEvent event) {
        if (!event.isCancelled()) {
            plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_CONSUMED, event.getItem().getType(), 1);
        }
    }

}
