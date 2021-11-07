package com.devflask.statisticslib.plugin.commands;

import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public record ReloadCommand(StatisticsPlugin statisticsPlugin) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage(statisticsPlugin.getConfigManager().PREFIX + "Reloading config.yml...");
        statisticsPlugin.getConfigManager().reloadConfig();
        return true;
    }
}
