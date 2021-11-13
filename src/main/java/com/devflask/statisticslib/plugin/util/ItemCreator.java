package com.devflask.statisticslib.plugin.util;

import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public class ItemCreator {

    private final ItemStack itemStack;
    private final List<String> lores = new ArrayList<>();
    private final ItemMeta itemMeta;
    private final StatisticsPlugin plugin;
    private final ArrayList<ClickAction> clickActions = new ArrayList<>();

    public ItemCreator(ItemStack item, final StatisticsPlugin plugin) {
        this.itemStack = item;
        this.itemMeta = item.getItemMeta();
        this.plugin = plugin;
    }

    public ItemCreator(Material material, int amount, final StatisticsPlugin plugin) {
        this.plugin = plugin;
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemCreator(Material material, final StatisticsPlugin plugin) {
        this.itemStack = new ItemStack(material, 1);
        this.plugin = plugin;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemCreator setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemCreator setType(Material material) {
        this.itemStack.setType(material);
        return this;
    }

    public ItemCreator setGlow(boolean bool) {
        if (bool) {
            this.itemMeta.addEnchant(Enchantment.OXYGEN, 1, true);
            this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            this.itemMeta.removeEnchant(Enchantment.OXYGEN);
            this.itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemCreator setUnbreakable(boolean bool) {
        this.itemMeta.setUnbreakable(bool);
        return this;
    }

    public ItemCreator setDisplayName(String name) {
        this.itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public ItemCreator addLore(String lore) {
        this.lores.add(ChatColor.translateAlternateColorCodes('&', lore));
        return this;
    }

    public ItemCreator addLoreList(String[] name) {
        this.lores.addAll(Arrays.asList(name));
        return this;
    }

    public ItemCreator setSkullOwner(String name) {
        ((SkullMeta) this.itemMeta).setOwningPlayer(Bukkit.getOfflinePlayer(plugin.getUserData().getUUID(name).orElse(UUID.randomUUID())));
        return this;
    }

    public ItemCreator setArmorColor(Color color) {
        ((LeatherArmorMeta) this.itemMeta).setColor(color);
        return this;
    }

    public ItemCreator addEnchantment(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemCreator addItemFlag(ItemFlag flag) {
        this.itemMeta.addItemFlags(flag);
        return this;
    }

    public ItemCreator removeItemFlag(ItemFlag flag) {
        this.itemMeta.removeItemFlags(flag);
        return this;
    }

    public ItemCreator cancelClickAction(boolean bool) {
        if (bool) {
            this.clickActions.remove(ClickAction.CANCEL);
        } else {
            this.clickActions.add(ClickAction.CANCEL);
        }
        return this;
    }

    public ItemCreator addClickAction(ClickAction clickAction) {
        this.clickActions.add(clickAction);
        return this;
    }

    public ItemStack build() {
        if (!lores.isEmpty()) {
            this.itemMeta.setLore(lores);
        }
        this.itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ArrayList<ClickAction> getClickActions() {
        return clickActions;
    }

}
