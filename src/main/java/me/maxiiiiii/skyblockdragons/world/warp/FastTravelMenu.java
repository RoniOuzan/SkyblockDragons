package me.maxiiiiii.skyblockdragons.world.warp;

import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FastTravelMenu extends Menu {
    private static final ItemStack HAVE_NOT_VISITED = createItem(Material.BEDROCK, ChatColor.RED + "Unknown", "UNKNOWN", ChatColor.GRAY + "You have not been in this", ChatColor.GRAY + "world yet!", "", ChatColor.YELLOW + "Click to travel!");

    public FastTravelMenu(PlayerSD player) {
        super(player,
                "Fast Travel",
                4,
                InventoryGlassType.ALL,
                false
        );
    }

    @Override
    public void update() {
        List<ItemStack> items = Arrays.stream(Warp.values()).filter(w -> w != Warp.UNWARPABLE).map(w -> {
            if (w.hasRequirements(player))
                return createItem(w.getItem(), ChatColor.GREEN + w.getName(), w.name(), ChatColor.DARK_GRAY + "/warp " + w.name().toLowerCase(), "", ChatColor.YELLOW + "Click to warp!");
            else
                return HAVE_NOT_VISITED;
        }).collect(Collectors.toList());

        this.putItemsOnCenter(2, items);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        String nbt = this.getNBT(e.getCurrentItem());
        if (nbt.equals("UNKNOWN")) {
            player.sendMessage(ChatColor.RED + "You haven't been in this world yet! Visit the world to unlock this warp.");
        } else if (Arrays.stream(Warp.values()).map(Enum::name).collect(Collectors.toList()).contains(nbt)) {
            Warp warp = Warp.valueOf(nbt);
            player.closeInventory();
            warp.warp(player);
        }
    }
}
