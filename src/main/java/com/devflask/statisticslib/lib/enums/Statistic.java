package com.devflask.statisticslib.lib.enums;

import org.bukkit.Material;

import java.util.Arrays;

public enum Statistic {

    BLOCKS_BROKEN("blocksbroken", Substatistic.MATERIAL, "Blocks broken", Material.DIAMOND_PICKAXE),
    BLOCKS_PLACED("blocksplaced", Substatistic.MATERIAL, "Blocks placed", Material.BRICK_WALL),
    ITEMS_BROKEN("itemsbroken", Substatistic.MATERIAL, "Items broken", Material.GOLDEN_SHOVEL),
    ITEMS_ENCHANTED("itemsenchanted", Substatistic.MATERIAL, "Items enchanted", Material.ENCHANTING_TABLE),
    ITEMS_DROPPED("itemsdropped", Substatistic.MATERIAL, "Items dropped", Material.DROPPER),
    ITEMS_USED("itemsused", Substatistic.MATERIAL, "Items used", Material.FLINT_AND_STEEL),
    ITEMS_CRAFTED("itemscrafted", Substatistic.MATERIAL, "Items crafted", Material.CRAFTING_TABLE),
    FISH_CAUGHT("fishcaught", Substatistic.MATERIAL, "Fish caught", Material.FISHING_ROD),
    BLOCKS_INTERACTED("blocksinteracted", Substatistic.MATERIAL, "Blocks interacted with", Material.DIAMOND_BLOCK),
    PROJECTILES_HIT_BLOCK("projectileshitblock", Substatistic.MATERIAL, "Projectiles hit a block", Material.TARGET),
    ITEMS_CONSUMED("itemsconsumed", Substatistic.MATERIAL, "Items consumed", Material.APPLE),
    MOB_KILLS("mobkills", Substatistic.ENTITY, "Mob-Kills", Material.CREEPER_HEAD),
    PROJECTILES_HIT_ENTITY("projectileshitentity", Substatistic.ENTITY, "Projectiles hit an entity", Material.ARROW),
    ENTITYS_BRED("entitysbred", Substatistic.ENTITY, "Entitys bred", Material.WHEAT),
    ENTITYS_TAMED("entitystamed", Substatistic.ENTITY, "Entitys tamed", Material.BONE),
    DEATHS("deaths", Substatistic.NONE, "Deaths", Material.WITHER_SKELETON_SKULL),
    PLAYER_KILLS("playerkills", Substatistic.NONE, "Player-Kills", Material.PLAYER_HEAD),
    DAMAGE_DEALT("damagedealt", Substatistic.NONE, "Damage dealt", Material.DIAMOND_SWORD),
    DAMAGE_TAKEN("damagetaken", Substatistic.NONE, "Damage taken", Material.DIAMOND_CHESTPLATE),
    PLAYER_JOINS("joins", Substatistic.NONE, "Joins", Material.EMERALD),
    PLAYER_QUITS("quits", Substatistic.NONE, "Quits", Material.REDSTONE),
    RAIDS_WON("raidswon", Substatistic.NONE, "Raids won", Material.CROSSBOW);


    private final String key, displayName;
    private final Substatistic substatistic;
    private final Material icon;

    Statistic(String key, Substatistic substatistic, String displayName, Material icon) {
        this.key = key;
        this.substatistic = substatistic;
        this.displayName = displayName;
        this.icon = icon;
    }

    public static Statistic getByKey(String key) {
        return Arrays.stream(Statistic.values()).filter(statistic -> statistic.getKey().equalsIgnoreCase(key)).findFirst().orElse(null);
    }


    public String getKey() {
        return key;
    }

    public Substatistic getSubstatistic() {
        return substatistic;
    }

    public boolean hasSubstatistic() {
        return substatistic != Substatistic.NONE;
    }

    public Material getIcon() {
        return icon;
    }

    public String getDisplayName() {
        return displayName;
    }
}
