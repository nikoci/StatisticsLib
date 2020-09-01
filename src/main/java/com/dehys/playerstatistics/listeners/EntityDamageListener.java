package com.dehys.playerstatistics.listeners;

import com.dehys.playerstatistics.main.Main;
import com.dehys.playerstatistics.enums.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {

            Main.getInstance().getStatisticsManager().addStatistic((Player) event.getDamager(), Statistic.DAMAGE_DEALT, (int) Math.round(event.getFinalDamage()));
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Main.getInstance().getStatisticsManager().addStatistic((Player) event.getEntity(), Statistic.DAMAGE_TAKEN, (int) Math.round(event.getDamage()));
        }
    }

}
