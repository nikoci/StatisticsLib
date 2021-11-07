package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public record EntityBreedListener(Plugin plugin) implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityBreed(EntityBreedEvent event) {
        if (!event.isCancelled()) {
            if (event.getBreeder() instanceof Player player) {
                plugin.getStatisticsManager().addStatistic(player, Statistic.ENTITYS_BRED, event.getEntityType(), 1);
            }
        }
    }

}
