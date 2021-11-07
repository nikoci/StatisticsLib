package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

public record EntityTameListener(StatisticsPlugin statisticsPlugin) implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityTame(final EntityTameEvent event) {
        if (!event.isCancelled()) {
            final Player player = Bukkit.getPlayer(event.getOwner().getUniqueId());
            if (player != null) {
                statisticsPlugin.getStatisticsManager().addStatistic(player, Statistic.ENTITYS_TAMED, event.getEntityType(), 1);
            }
        }
    }

}
