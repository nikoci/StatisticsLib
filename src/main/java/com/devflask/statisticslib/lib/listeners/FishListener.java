package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.Plugin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public record FishListener(Plugin plugin) implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onFish(final PlayerFishEvent event) {
        if (!event.isCancelled()) {
            if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
                if (event.getCaught() != null && event.getCaught().getType() == EntityType.DROPPED_ITEM) {
                    plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.FISH_CAUGHT, ((Item) event.getCaught()).getItemStack().getType(), 1);
                }
            }
        }
    }


}
