package com.dehys.playerstatistics.listeners;

import com.dehys.playerstatistics.enums.Statistic;
import com.dehys.playerstatistics.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListener implements Listener {


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player)
            Main.getInstance().getStatisticsManager().addStatistic((Player) event.getEntity(), Statistic.DEATHS, 1);

        if (event.getEntity().getKiller() != null) {
            Main.getInstance().getStatisticsManager().addStatistic(event.getEntity().getKiller(), (event.getEntity() instanceof Player ? Statistic.PLAYER_KILLS : Statistic.MOB_KILLS), (event.getEntity() instanceof Player ? null : event.getEntityType()), 1);
        }


    }


}
