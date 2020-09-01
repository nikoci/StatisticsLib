package com.dehys.playerstatistics.listeners;

import com.dehys.playerstatistics.enums.Statistic;
import com.dehys.playerstatistics.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishListener implements Listener {


    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            Main.getInstance().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.FISH_CAUGHT, 1);
        }
    }


}
