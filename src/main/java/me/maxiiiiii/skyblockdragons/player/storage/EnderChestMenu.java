package me.maxiiiiii.skyblockdragons.player.storage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EnderChestMenu extends Menu {
    private int page;

    public EnderChestMenu(PlayerSD player, int page) {
        super(player, "Ender Chest Page " + (page + 1), 6, InventoryGlassType.NOTHING, false, false);
        this.page = page;
    }

    @Override
    public void update() {
        for (int i = 0; i < 45; i++) {
            this.setItem(i + 9, this.player.getEnderChestSD().getItems().get(i + (this.page * 45)));
        }

        for (int i = 2; i < 7; i++) {
            this.setItem(i, GLASS);
        }

        this.setItem(0, CLOSE);
        this.setItem(1, GO_BACK);

        this.player.sendMessage(this.page);
        this.setItem(8, this.page >= 8 ? GLASS : Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Next Page", "NextPage", "", ChatColor.AQUA + "Right-Click to go to last page!", ChatColor.YELLOW + "Click to go to the next page!"), "23b3f9dc-f02c-4ea8-a949-dbd56b03602c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVmMzU2YWQyYWE3YjE2NzhhZWNiODgyOTBlNWZhNWEzNDI3ZTVlNDU2ZmY0MmZiNTE1NjkwYzY3NTE3YjgifX19"));
        this.setItem(7, this.page <= 0 ? GLASS : Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Previous Page", "PreviousPage", "", ChatColor.AQUA + "Right-Click to go to first page!", ChatColor.YELLOW + "Click to go to the previous page!"), "5df301dc-3d34-4c29-9f69-8e7cacb548e0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjUzNDc0MjNlZTU1ZGFhNzkyMzY2OGZjYTg1ODE5ODVmZjUzODlhNDU0MzUzMjFlZmFkNTM3YWYyM2QifX19"));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getSlot() >= 9) e.setCancelled(false);

        if (this.getNBT(e.getCurrentItem()).equals("NextPage")) {
            this.save();
            if (e.getClick().isLeftClick()) {
                this.page++;
            } else if (e.getClick().isRightClick()) {
                this.page = 8;
            }
            new EnderChestMenu(player, this.page);
        } else if (this.getNBT(e.getCurrentItem()).equals("PreviousPage")) {
            this.save();
            if (e.getClick().isLeftClick()) {
                this.page--;
            } else if (e.getClick().isRightClick()) {
                this.page = 0;
            }
            new EnderChestMenu(player, this.page);
        }
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent e) {
        this.save();
    }

    private void save() {
        for (int i = 0; i < 45; i++) {
            if (Functions.isNotAir(this.getInventory().getItem(i + 9))) {
                this.player.getEnderChestSD().setItem(i + (this.page * 45), this.getInventory().getItem(i + 9));
            } else {
                this.player.getEnderChestSD().removeItem(i + (this.page * 45));
            }
        }
    }

    public static class Command implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
            if (sender instanceof Player) {
                PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
                if (args.length > 0 && Functions.isInt(args[0])) {
                    new EnderChestMenu(player, Math.min(Math.max(Integer.parseInt(args[0]), 0), 8));
                } else {
                    new EnderChestMenu(player, 0);
                }
            }
            return true;
        }
    }
}
