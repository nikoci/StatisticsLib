package com.dehys.norbecore.listeners;

import com.dehys.norbecore.enums.Statistic;
import com.dehys.norbecore.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if(event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            Main.getInstance().getStatisticsManager().addStatistic(player, Statistic.TARGETS_HIT, event.getEntityType(), 1);
        }
    }

}
