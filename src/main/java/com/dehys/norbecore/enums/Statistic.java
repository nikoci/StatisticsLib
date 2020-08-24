package com.dehys.norbecore.enums;

import java.util.Arrays;

public enum Statistic {


    BLOCKS_BROKEN("blocksbroken", Substatistic.MATERIAL),
    ITEMS_BROKEN("itemsbroken", Substatistic.MATERIAL),
    ITEMS_ENCHANTED("itemsenchanted", Substatistic.MATERIAL),
    MOB_KILLS("mobkills", Substatistic.ENTITY),
    TARGETS_HIT("targetshit", Substatistic.ENTITY),
    DEATHS("deaths", Substatistic.NONE),
    PLAYER_KILLS("playerkills", Substatistic.NONE),
    ITEMS_DROPPED("itemsdropped", Substatistic.NONE),
    DAMAGE_DEALT("damagedealt", Substatistic.NONE),
    DAMAGE_TAKEN("damagetaken", Substatistic.NONE),
    ITEMS_CRAFTED("itemscrafted", Substatistic.NONE);


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
