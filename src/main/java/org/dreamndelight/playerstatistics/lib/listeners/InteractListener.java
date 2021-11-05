package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class InteractListener implements Listener {

    private final PlayerStatistics plugin;

    public InteractListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() != null) {
            if (event.useItemInHand() == Event.Result.ALLOW) {
                if (player.getCooldown(event.getMaterial()) <= 0) {
                    plugin.getStatisticsManager().addStatistic(player, Statistic.ITEMS_USED, event.getMaterial(), 1);
                }
            }
        }
        if (event.getClickedBlock() != null) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                plugin.getStatisticsManager().addStatistic(player, Statistic.BLOCKS_INTERACTED, event.getClickedBlock().getType(), 1);
            }
        }
    }

}
