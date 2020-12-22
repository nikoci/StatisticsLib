package org.dreamndelight.playerstatistics.lib.enums;

import java.util.Arrays;

@SuppressWarnings({"unused", "RedundantSuppression"})
public enum Statistic {


    BLOCKS_BROKEN("blocksbroken", Substatistic.MATERIAL),
    ITEMS_BROKEN("itemsbroken", Substatistic.MATERIAL),
    ITEMS_ENCHANTED("itemsenchanted", Substatistic.MATERIAL),
    ITEMS_DROPPED("itemsdropped", Substatistic.MATERIAL),
    ITEMS_USED("itemsused", Substatistic.MATERIAL),
    ITEMS_CRAFTED("itemscrafted", Substatistic.MATERIAL),
    MOB_KILLS("mobkills", Substatistic.ENTITY),
    TARGETS_HIT("targetshit", Substatistic.ENTITY),
    DEATHS("deaths", Substatistic.NONE),
    PLAYER_KILLS("playerkills", Substatistic.NONE),
    DAMAGE_DEALT("damagedealt", Substatistic.NONE),
    DAMAGE_TAKEN("damagetaken", Substatistic.NONE),
    FISH_CAUGHT("fishcaught", Substatistic.NONE);


    private final String key;
    private final Substatistic substatistic;

    Statistic(String key, Substatistic substatistic) {
        this.key = key;
        this.substatistic = substatistic;
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

    public boolean hasSubstatistic(){
        return substatistic!=Substatistic.NONE;
    }
}
