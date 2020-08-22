package com.dehys.norbecore.listeners;

import com.dehys.norbecore.main.Main;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class ItemCraftListener implements Listener {

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        assert event.getWhoClicked() instanceof Player;
        Main.getInstance().getStatisticsManager().addStatistic((Player) event.getWhoClicked(), Statistic.CRAFT_ITEM, 1);
    }

}
