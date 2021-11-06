package com.devflask.statisticslib.lib.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

public class EntityTameListener implements Listener {

    private final PlayerStatistics plugin;

    public EntityTameListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityTame(final EntityTameEvent event) {
        if (!event.isCancelled()) {
            final Player player = Bukkit.getPlayer(event.getOwner().getUniqueId());
            if (player != null) {
                plugin.getStatisticsManager().addStatistic(player, Statistic.ENTITYS_TAMED, event.getEntityType(), 1);
            }
        }
    }

}
