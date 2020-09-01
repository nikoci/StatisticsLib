package org.dreamndelight.playerstatistics.listeners;

import org.dreamndelight.playerstatistics.main.Main;
import org.dreamndelight.playerstatistics.enums.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;

public class ItemBreakListener implements Listener {

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent event) {
        Main.getInstance().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.ITEMS_BROKEN, event.getBrokenItem().getType(), 1);
    }

}
