package com.devflask.statisticslib.lib.listeners;

import com.devflask.statisticslib.lib.enums.Statistic;
import com.devflask.statisticslib.lib.main.PlayerStatistics;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import static com.devflask.statisticslib.lib.main.Util.getMaxCraftable;
import static com.devflask.statisticslib.lib.main.Util.getRemainingInvSpace;


public record ItemCraftListener(PlayerStatistics plugin) implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCraftItem(final CraftItemEvent event) {
        if (!event.isCancelled()) {
            assert event.getWhoClicked() instanceof Player;
            if (event.getCurrentItem() == null) return;
            //TODO: Test if multiple crafts through e.g shiftclick is calculated correctly (with (half)full inventory, drops, etc.)
            plugin.getStatisticsManager().addStatistic((Player) event.getWhoClicked(), Statistic.ITEMS_CRAFTED, event.getCurrentItem().getType(), calculateAmount(event));
        }
    }

    private int calculateAmount(final CraftItemEvent event) {
        ItemStack stack = event.getRecipe().getResult().clone();
        ClickType click = event.getClick();

        int amount = stack.getAmount();

        switch (click) {
            case NUMBER_KEY:

                if (event.getWhoClicked().getInventory().getItem(event.getHotbarButton()) != null)
                    amount = 0;
                break;

            case DROP:
            case CONTROL_DROP:

                if (!(event.getCursor() == null || event.getCursor().getType() == Material.AIR))
                    amount = 0;
                break;

            case SHIFT_RIGHT:
            case SHIFT_LEFT:
                if (amount == 0)
                    break;

                int maxCraftable = getMaxCraftable(event.getInventory());
                int space = getRemainingInvSpace(stack, event.getInventory());

                if (space < maxCraftable)
                    maxCraftable = ((space + amount - 1) / amount) * amount;

                amount = maxCraftable;
                break;
        }

        return amount;
    }

}
