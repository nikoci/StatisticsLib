package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public record ProjectileHitListener(PlayerStatistics plugin) implements Listener {

    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player player) {
            if (event.getHitEntity() != null) {
                plugin.getStatisticsManager().addStatistic(player, Statistic.PROJECTILES_HIT_ENTITY, event.getEntityType(), 1);
            } else if (event.getHitBlock() != null) {
                plugin.getStatisticsManager().addStatistic(player, Statistic.PROJECTILES_HIT_BLOCK, event.getHitBlock().getType(), 1);
            }

        }
    }

}
