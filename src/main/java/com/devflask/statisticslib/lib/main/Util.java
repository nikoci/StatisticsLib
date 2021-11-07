package com.devflask.statisticslib.lib.main;

import com.devflask.statisticslib.plugin.StatisticsPlugin;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Util {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    public static String generatePlayerID(StatisticsPlugin statisticsPlugin) {
        int length = 10;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            length--;
        }

        return (statisticsPlugin.getUserData().isPlayerIDAvailable(builder.toString())) ? builder.toString() : generatePlayerID(statisticsPlugin);
    }

    public static int getMaxCraftable(final CraftingInventory inventory) {
        if (inventory.getResult() == null)
            return 0;

        int resultCount = inventory.getResult().getAmount();
        int materialCount = Integer.MAX_VALUE;

        for (ItemStack stack : inventory.getMatrix())
            if (stack != null && stack.getAmount() < materialCount)
                materialCount = stack.getAmount();

        return resultCount * materialCount;
    }

    public static int getRemainingInvSpace(ItemStack stack, Inventory inventory) {
        ItemStack[] contents = inventory.getContents();
        int result = 0;

        for (ItemStack is : contents)
            if (is == null)
                result += stack.getMaxStackSize();
            else if (is.isSimilar(stack))
                result += Math.max(stack.getMaxStackSize() - is.getAmount(), 0);

        return result;
    }

}
