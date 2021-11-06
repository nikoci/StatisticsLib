package org.dreamndelight.playerstatistics.lib.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.dreamndelight.playerstatistics.lib.enums.Statistic;
import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

public class EntityBreedListener implements Listener {

    private final PlayerStatistics plugin;

    public EntityBreedListener(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityBreed(EntityBreedEvent event) {
        if (!event.isCancelled()) {
            if (event.getBreeder() instanceof Player) {
                Player player = (Player) event.getBreeder();
                plugin.getStatisticsManager().addStatistic(player, Statistic.ENTITYS_BRED, event.getEntityType(), 1);
            }
        }
    }

}
