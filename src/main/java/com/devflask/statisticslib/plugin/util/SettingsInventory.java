package com.devflask.statisticslib.plugin.util;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        plugin.getConfigManager().getEnabledStatistics().forEach((statistic, enabled) -> settingsInventory.addItem((enabled ? guiItemsEnabled : guiItemsDisabled).get(statistic)));
    }

    private void loadGUIItems() {
        Arrays.stream(Statistic.values()).forEach(statistic -> {
            ItemCreator itemCreator = new ItemCreator(statistic.getIcon(), plugin);
            final boolean disabled = plugin.getConfigManager().isStatisticDisabled(statistic);
            itemCreator.setDisplayName(ChatColor.GOLD + statistic.getDisplayName())
                    .setGlow(!disabled)
                    .addLore("§7Status: " + (disabled ? "§4Disabled" : "§aEnabled"))
                    .addLore(" ")
                    .addLore("§7Click to " + (disabled ? "§aenable" : "§4disable"))
                    .cancelClickAction(true)
                    .addClickAction(event -> {
                        if (event.getClickedInventory() == null || !event.getClickedInventory().equals(settingsInventory))
                            return;
                        if (!event.getWhoClicked().hasPermission("psl.admin")) return;
                        plugin.getConfigManager().setStatisticEnabled(statistic, !disabled);

                        event.getWhoClicked().sendMessage(plugin.getConfigManager().PREFIX + "§7The statistic \"§6" + statistic.getDisplayName() + "\"§7 has been " + (disabled ? "§aenabled" : "§4disabled") + ".");
                    });
            final ItemStack item = itemCreator.build();

            if (disabled) guiItemsDisabled.put(statistic, item);
            else guiItemsEnabled.put(statistic, item);
        });
    }

    public Inventory get() {
        return settingsInventory;
    }


}
