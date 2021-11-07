package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public record ConsumeItemListener(Plugin plugin) implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onItemConsume(final PlayerItemConsumeEvent event) {
        if (!event.isCancelled()) {
            plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_CONSUMED, event.getItem().getType(), 1);
        }
    }

}
