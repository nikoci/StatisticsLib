package org.dreamndelight.playerstatistics.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.dreamndelight.playerstatistics.enums.Statistic;
import org.dreamndelight.playerstatistics.main.PlayerStatistics;

public class FishListener implements Listener {


    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            PlayerStatistics.get().getStatisticsManager().addStatistic(event.getPlayer(), Statistic.FISH_CAUGHT, 1);
        }
    }


}
