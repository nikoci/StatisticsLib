package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            PlayerStatistics.get().getStatisticsManager().addStatistic(player, Statistic.TARGETS_HIT, event.getEntityType(), 1);
        }
    }

}
