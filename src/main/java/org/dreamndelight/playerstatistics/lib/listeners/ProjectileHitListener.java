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

            if (event.getHitEntity() != null) {
                PlayerStatistics.get().getStatisticsManager().addStatistic(player, Statistic.PROJECTILES_HIT_ENTITY, event.getEntityType(), 1);
            } else if (event.getHitBlock() != null) {
                PlayerStatistics.get().getStatisticsManager().addStatistic(player, Statistic.PROJECTILES_HIT_BLOCK, event.getEntityType(), 1);
            }
        }
    }

}
