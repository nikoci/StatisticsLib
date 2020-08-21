package com.dehys.norbecore.listeners;

import com.dehys.norbecore.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Main.getInstance().getUserData().registerPlayer(event.getPlayer());
    }

}
