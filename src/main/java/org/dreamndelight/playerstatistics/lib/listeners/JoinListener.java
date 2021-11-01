package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class JoinListener implements Listener {

    private final PlayerStatistics plugin;

    public JoinListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        plugin.getLib().getUserData().registerPlayer(event.getPlayer());
    }

}
