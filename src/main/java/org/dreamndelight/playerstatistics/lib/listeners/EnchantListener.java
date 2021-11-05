package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class EnchantListener implements Listener {

    private final PlayerStatistics plugin;

    public EnchantListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEnchant(final EnchantItemEvent event) {
        plugin.getStatisticsManager().addStatistic(event.getEnchanter(), Statistic.ITEMS_ENCHANTED, event.getItem().getType(), 1);
    }

}
