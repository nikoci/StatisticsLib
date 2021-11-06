package com.devflask.statisticslib.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

public class EnchantListener implements Listener {

    private final PlayerStatistics plugin;

    public EnchantListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEnchant(final EnchantItemEvent event) {
        if (!event.isCancelled()) {
            plugin.getStatisticsManager().addStatistic(event.getEnchanter(), Statistic.ITEMS_ENCHANTED, event.getItem().getType(), 1);
        }
    }

}
