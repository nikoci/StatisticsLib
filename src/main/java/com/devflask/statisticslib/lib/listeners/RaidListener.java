package com.devflask.statisticslib.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidFinishEvent;
import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;

public class RaidListener implements Listener {

    private final PlayerStatistics plugin;

    public RaidListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRaid(final RaidFinishEvent event) {
        event.getWinners().forEach(winner -> plugin.getStatisticsManager().addStatistic(winner, Statistic.RAIDS_WON, 1));
    }

}
