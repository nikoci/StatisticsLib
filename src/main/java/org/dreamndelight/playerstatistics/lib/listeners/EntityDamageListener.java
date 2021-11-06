package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class EntityDamageListener implements Listener {

    private final PlayerStatistics plugin;

    public EntityDamageListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (!event.isCancelled()) {
            if (event.getDamager() instanceof Player) {
                plugin.getStatisticsManager().addStatistic((Player) event.getDamager(), Statistic.DAMAGE_DEALT, (int) Math.round(event.getFinalDamage()));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamage(final EntityDamageEvent event) {
        if (!event.isCancelled()) {
            if (event.getEntity() instanceof Player) {
                plugin.getStatisticsManager().addStatistic((Player) event.getEntity(), Statistic.DAMAGE_TAKEN, (int) Math.round(event.getDamage()));
            }
        }
    }

}
