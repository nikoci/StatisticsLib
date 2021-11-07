package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidFinishEvent;

public record RaidListener(Plugin plugin) implements Listener {

    @EventHandler
    public void onRaid(final RaidFinishEvent event) {
        event.getWinners().forEach(winner -> plugin.getStatisticsManager().addStatistic(winner, Statistic.RAIDS_WON, 1));
    }

}
