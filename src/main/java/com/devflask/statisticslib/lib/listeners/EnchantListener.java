package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public record EnchantListener(PlayerStatistics plugin) implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEnchant(final EnchantItemEvent event) {
        if (!event.isCancelled()) {
            plugin.getStatisticsManager().addStatistic(event.getEnchanter(), Statistic.ITEMS_ENCHANTED, event.getItem().getType(), 1);
        }
    }

}
