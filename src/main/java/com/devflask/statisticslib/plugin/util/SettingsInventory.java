package com.devflask.statisticslib.plugin.util;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;

public class SettingsInventory {

    private final StatisticsPlugin plugin;
    private final Inventory settingsInventory;
    private final HashMap<Statistic, ItemStack> guiItemsEnabled;
    private final HashMap<Statistic, ItemStack> guiItemsDisabled;


    public SettingsInventory(StatisticsPlugin plugin) {
        this.plugin = plugin;
        this.settingsInventory = Bukkit.createInventory(null, 36, "§6§lStatistics Settings");
        this.guiItemsEnabled = new HashMap<>();
        this.guiItemsDisabled = new HashMap<>();

        loadGUIItems();

        plugin.getConfigManager().getEnabledStatistics().forEach((statistic, enabled) -> settingsInventory.addItem((enabled ? guiItemsEnabled : guiItemsDisabled).getOrDefault(statistic, new ItemStack(Material.STONE))));
    }

    private ItemStack generateItem(Statistic statistic, boolean enabled) {
        ItemCreator itemCreator = new ItemCreator(statistic.getIcon(), plugin);
        itemCreator.setDisplayName(ChatColor.GOLD + statistic.getDisplayName())
                .setGlow(enabled)
                .addLore("§7Status: " + (enabled ? "§aEnabled" : "§4Disabled"))
                .addLore(" ")
                .addLore("§7Click to " + (enabled ? "§4disable" : "§aenable"))
                .cancelClickAction(true)
                .addClickAction(event -> {
                    if (event.getClickedInventory() == null || !event.getClickedInventory().equals(settingsInventory))
                        return;
                    if (!event.getWhoClicked().hasPermission("psl.admin")) return;
                    plugin.getConfigManager().setStatisticEnabled(statistic, !enabled);
                    swapItem(event.getSlot(), statistic, !enabled);
                    event.getWhoClicked().sendMessage(plugin.getConfigManager().PREFIX + "§7The statistic \"§6" + statistic.getDisplayName() + "\"§7 has been " + (enabled ? "§4disabled" : "§aenabled") + ".");
                });
        return itemCreator.build();
    }

    private void swapItem(int slot, Statistic statistic, boolean enabled) {
        settingsInventory.setItem(slot, (enabled ? guiItemsEnabled : guiItemsDisabled).get(statistic));
    }

    private void loadGUIItems() {
        Arrays.stream(Statistic.values()).forEach(statistic -> {
            guiItemsEnabled.put(statistic, generateItem(statistic, true));
            guiItemsDisabled.put(statistic, generateItem(statistic, false));
        });
    }

    public Inventory get() {
        return settingsInventory;
    }


}
