package com.devflask.statisticslib.plugin.listeners;

import com.devflask.statisticslib.plugin.util.ItemActionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(final InventoryClickEvent event) {
        ItemActionHandler.handleClick(event);
    }

}
