package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class InteractListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteract(final PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.useItemInHand() == Event.Result.ALLOW) {
                Player player = event.getPlayer();
                if (player.getCooldown(event.getMaterial()) <= 0) {
                    PlayerStatistics.get().getStatisticsManager().addStatistic(player, Statistic.ITEMS_USED, event.getMaterial(), 1);
                }
            }
        }
    }

}
