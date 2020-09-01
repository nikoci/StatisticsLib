package org.dreamndelight.playerstatistics.listeners;

import org.dreamndelight.playerstatistics.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Main.getInstance().getUserData().registerPlayer(event.getPlayer());
    }

}
