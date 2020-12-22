package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class JoinListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PlayerStatistics.get().getUserData().registerPlayer(event.getPlayer());
    }

}
