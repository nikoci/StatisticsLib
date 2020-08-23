package com.dehys.norbecore.listeners;

import com.dehys.norbecore.main.Main;
import com.dehys.norbecore.enums.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {


    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Main.getInstance().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_DROPPED, event.getItemDrop().getItemStack().getAmount());
    }


}
