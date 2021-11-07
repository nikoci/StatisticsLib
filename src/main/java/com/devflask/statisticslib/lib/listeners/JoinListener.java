package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.plugin.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final record JoinListener(Plugin plugin) implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        plugin.getUserData().registerPlayer(event.getPlayer());
        plugin.getStatisticsManager().addStatistic(event.getPlayer(), Statistic.PLAYER_JOINS, 1);
    }

}
