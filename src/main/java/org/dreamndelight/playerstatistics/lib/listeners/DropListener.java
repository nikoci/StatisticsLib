package org.dreamndelight.playerstatistics.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.dreamndelight.playerstatistics.enums.Statistic;
import org.dreamndelight.playerstatistics.main.PlayerStatistics;

public class DropListener implements Listener {


    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        PlayerStatistics.get().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_DROPPED, event.getItemDrop().getItemStack().getType(), event.getItemDrop().getItemStack().getAmount());
    }


}
