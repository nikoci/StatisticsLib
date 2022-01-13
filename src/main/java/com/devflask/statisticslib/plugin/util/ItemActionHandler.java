package com.devflask.statisticslib.plugin.util;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemActionHandler {

    public static HashMap<ItemStack, ArrayList<ClickAction>> clickActions = new HashMap<>();

    public static void registerItem(ItemCreator itemCreator) {
        clickActions.put(itemCreator.getItemStack(), itemCreator.getClickActions());
    }

    public static void handleClick(final InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (clickActions.containsKey(event.getCurrentItem())) {
            for (ClickAction clickAction : clickActions.get(event.getCurrentItem())) {
                clickAction.runAction(event);
            }
        }
    }

}
