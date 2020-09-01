package com.dehys.playerstatistics.listeners;

import com.dehys.playerstatistics.enums.Statistic;
import com.dehys.playerstatistics.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantListener implements Listener {


    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        Main.getInstance().getStatisticsManager().addStatistic(event.getEnchanter(), Statistic.ITEMS_ENCHANTED ,event.getItem().getType(), 1);
    }

}
