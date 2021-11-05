package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class FishListener implements Listener {

    private final PlayerStatistics plugin;

    public FishListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFish(final PlayerFishEvent event) {

        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if (event.getCaught() != null && event.getCaught().getType() == EntityType.DROPPED_ITEM) {
                plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.FISH_CAUGHT, ((Item) event.getCaught()).getItemStack().getType(), 1);
            }

        }

    }


}
