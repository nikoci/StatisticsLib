package com.devflask.statisticslib.plugin.util;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface ClickAction {

    ClickAction CANCEL = (event) -> {
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
    };

    void runAction(final InventoryClickEvent event);

}
