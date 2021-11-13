package com.devflask.statisticslib.plugin.commands;

import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public record ManageStatisticsCommand(StatisticsPlugin plugin) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {
            player.openInventory(plugin.getSettingsInventory().get());
        }

        return true;
    }

}
