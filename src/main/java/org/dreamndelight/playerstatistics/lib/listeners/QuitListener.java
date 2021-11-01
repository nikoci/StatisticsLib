package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.event.Listener;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class QuitListener implements Listener {

    private final PlayerStatistics plugin;

    public QuitListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }


}
