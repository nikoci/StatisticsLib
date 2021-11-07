package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public record EnchantListener(StatisticsPlugin statisticsPlugin) implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEnchant(final EnchantItemEvent event) {
        if (!event.isCancelled()) {
            statisticsPlugin.getStatisticsManager().addStatistic(event.getEnchanter(), Statistic.ITEMS_ENCHANTED, event.getItem().getType(), 1);
        }
    }

}
