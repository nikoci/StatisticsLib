package com.devflask.statisticslib.lib.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.devflask.statisticslib.lib.main.PlayerStatistics;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final PlayerStatistics plugin;

    public ReloadCommand(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage("§d[PlayerStatisticsLib] Reloading config.yml...");
        plugin.getConfigManager().reloadConfig();
        return true;
    }
}
