package com.dehys.norbecore.listeners;

import com.dehys.norbecore.main.Main;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {


    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Main.getInstance().getStatisticsManager().getStatistic(event.getEntity()).orElse(Main.getInstance().getStatisticsManager().createStatistic(event.getEntity())).addStatistic(Statistic.DEATHS, 1);
    }

}
