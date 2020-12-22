package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;


public class ItemCraftListener implements Listener {

    @EventHandler
    public void onCraftItem(final CraftItemEvent event) {
        assert event.getWhoClicked() instanceof Player;
        if (event.getCurrentItem() == null) return;
        PlayerStatistics.get().getStatisticsManager().addStatistic((Player) event.getWhoClicked(), Statistic.ITEMS_CRAFTED, event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
    }

}
