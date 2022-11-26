package me.maxiiiiii.skyblockdragons.world.worlds.deepermines.forge;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.BlockColor;
import me.maxiiiiii.skyblockdragons.util.objects.Time;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Forge {
    private final PlayerSD player;
    private final Map<Integer, ForgeSlot> forging;
    private final ForgeMilestone milestone;

    public Forge(PlayerSD player) {
        this.player = player;
        this.milestone = new ForgeMilestone(player);
        this.forging = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            if (Variables.get(player.getUniqueId(), "ForgeRecipe", i) != null)
                this.forging.put(i, new ForgeSlot(
                        player,
                        (ForgeRecipe) Variables.get(player.getUniqueId(), "ForgeRecipe", i),
                        Variables.getItemStack(player.getUniqueId(), "ForgeFromItem", i),
                        new Time(Variables.getDouble(player.getUniqueId(), "ForgeTime", i))
                ));
        }
    }

    public void newSlot(int slot, ForgeRecipe recipe, ItemStack fromItem) {
        if (this.forging.size() >= 5) {
            player.sendMessage(ChatColor.RED + "You have reached the limit of forges!");
            return;
        }

        this.forging.put(slot, new ForgeSlot(player, recipe, fromItem));
    }

    public void save() {
        for (int i = 1; i <= 5; i++) {
            if (forging.containsKey(i)) {
                Variables.set(this.player.getUniqueId(), "ForgeRecipe", i, forging.get(i).getRecipe());
                Variables.set(this.player.getUniqueId(), "ForgeFromItem", i, forging.get(i).getFromItem());
                Variables.set(this.player.getUniqueId(), "ForgeTime", i, forging.get(i).getTime().now);
            } else {
                Variables.set(this.player.getUniqueId(), "ForgeRecipe", i, null);
                Variables.set(this.player.getUniqueId(), "ForgeFromItem", i, null);
                Variables.set(this.player.getUniqueId(), "ForgeTime", i, null);
            }
        }

        this.milestone.save();
    }

    public void view() {
        new View();
    }

    public class View extends Menu {
        public  View() {
            super(Forge.this.player, "Forge", 6, InventoryGlassType.ALL, true);
        }

        @Override
        public void update() {
            this.setItem(48, getGlass(BlockColor.BLACK));
            this.setItem(50, createItem(Material.EMPTY_MAP, ChatColor.YELLOW + "Milestone", "MILESTONE", "", ChatColor.YELLOW + "Click to view!"));

            for (int i = 1; i <= 5; i++) {
                if (forging.containsKey(i)) {
                    double percent = forging.get(i).getTime().now() / forging.get(i).getRecipe().getDuration();
                    if (percent >= 1)
                        this.setItem(10 + i, addNBT(Functions.addLine(forging.get(i).getRecipe().getItem().clone(), "", ChatColor.YELLOW + "Click to claim!"), "FORGED"));
                    else
                        this.setItem(10 + i, addNBT(forging.get(i).getRecipe().getItem().clone(), "FORGING"));
                    if (percent >= 1) {
                        this.setItem(19 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.LIME, ChatColor.GREEN + forging.get(i).getTime().toString(), "TIME"));
                        this.setItem(28 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.LIME, ChatColor.GREEN + forging.get(i).getTime().toString(), "TIME"));
                        this.setItem(37 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.LIME, ChatColor.GREEN + forging.get(i).getTime().toString(), "TIME"));
                    } else if (percent >= 2d / 3d) {
                        this.setItem(19 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.YELLOW, ChatColor.YELLOW + forging.get(i).getTime().toString(), "TIME"));
                        this.setItem(28 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.LIME, ChatColor.GREEN + forging.get(i).getTime().toString(), "TIME"));
                        this.setItem(37 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.LIME, ChatColor.GREEN + forging.get(i).getTime().toString(), "TIME"));
                    } else if (percent >= 1d / 3d) {
                        this.setItem(19 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.RED, ChatColor.RED + forging.get(i).getTime().toString(), "TIME"));
                        this.setItem(28 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.YELLOW, ChatColor.YELLOW + forging.get(i).getTime().toString(), "TIME"));
                        this.setItem(37 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.LIME, ChatColor.GREEN + forging.get(i).getTime().toString(), "TIME"));
                    } else {
                        this.setItem(19 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.RED, ChatColor.RED + forging.get(i).getTime().toString(), "TIME"));
                        this.setItem(28 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.RED, ChatColor.RED + forging.get(i).getTime().toString(), "TIME"));
                        this.setItem(37 + i, createItem(Material.STAINED_GLASS_PANE, BlockColor.YELLOW, ChatColor.YELLOW + forging.get(i).getTime().toString(), "TIME"));
                    }
                } else {
                    this.setItem(10 + i, createItem(Material.FURNACE, ChatColor.GREEN + "Slot #" + i, "FORGE_SLOT", ChatColor.GRAY + "View and start forge processes", ChatColor.GRAY + "using the materials that you", ChatColor.GRAY + "have.", "", ChatColor.YELLOW + "Click to select process!"));
                    this.setItem(19 + i, getGlass(BlockColor.RED));
                    this.setItem(28 + i, getGlass(BlockColor.RED));
                    this.setItem(37 + i, getGlass(BlockColor.RED));
                }
            }
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (this.getNBT(e.getCurrentItem()).equals("FORGED")) {
                int slot = e.getSlot() - 10;
                this.player.give(forging.get(slot).getRecipe().getItem());
                forging.remove(slot);
            } else if (this.getNBT(e.getCurrentItem()).equals("FORGE_SLOT")) {
                int slot = e.getSlot() - 10;
                new ForgeRecipe.View(player, slot);
            } else if (this.getNBT(e.getCurrentItem()).equals("MILESTONE")) {
                Forge.this.milestone.view();
            }
        }

    }

    public static class Command extends CommandSD {
        @Override
        public void command(PlayerSD player, String[] args) {
            player.getForge().view();
        }

        @Override
        public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
            return tabs;
        }
    }
}
