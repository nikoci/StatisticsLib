package com.dehys.norbecore.enums;

public enum Statistic {


    BLOCKS_BROKEN("blocksbroken", Substatistic.MATERIAL),
    ITEMS_BROKEN("itemsbroken", Substatistic.MATERIAL),
    DEATHS("deaths", Substatistic.NONE),
    PLAYER_KILLS("playerkills", Substatistic.NONE),
    MOB_KILLS("mobkills", Substatistic.NONE),
    ITEMS_DROPPED("itemsdropped", Substatistic.NONE),
    DAMAGE_DEALT("damagedealt", Substatistic.NONE),
    DAMAGE_TAKEN("damagetaken", Substatistic.NONE),
    ITEMS_CRAFTED("itemscrafted", Substatistic.NONE);



    private String key;
    private Substatistic substatistic;
    private Statistic(String key, Substatistic substatistic) {
        this.key = key;
        this.substatistic = substatistic;
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
