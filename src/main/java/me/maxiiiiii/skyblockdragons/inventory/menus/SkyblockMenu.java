package me.maxiiiiii.skyblockdragons.inventory.menus;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.CraftingTableMenu;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.RecipesMenu;
import me.maxiiiiii.skyblockdragons.item.objects.Stat;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.pet.PetMenu;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillMenu;
import me.maxiiiiii.skyblockdragons.player.storage.StorageMenu;
import me.maxiiiiii.skyblockdragons.player.wardrobe.WardrobeMenu;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.stream.Collectors;

public class SkyblockMenu extends Menu {
    public SkyblockMenu(PlayerSD player) {
        super(player, "Skyblock Menu", 6, InventoryGlassType.ALL, true);
    }

    @Override
    public void update() {
        this.setItem(13, Functions.applyHead(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Your Skyblock Profile", "STATS", player.getStats().stream().map(s -> s.type.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(s.amount)).collect(Collectors.toList())), this.player.getPlayer()));

        this.setItem(19, createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Your Skills", "SKILLS"));
        this.setItem(20, createItem(Material.PAINTING, ChatColor.GREEN + "Collection", "COLLECTION"));
        this.setItem(21, createItem(Material.BOOK, ChatColor.GREEN + "Recipe Book", "RECIPE_BOOK"));
        this.setItem(22, createItem(Material.EMERALD, ChatColor.GREEN + "Trades", "TRADES"));
        this.setItem(23, createItem(Material.WRITTEN_BOOK, ChatColor.GREEN + "Quests", "QUESTS"));
        this.setItem(24, createItem(Material.WATCH, ChatColor.GREEN + "Calender", "CALENDER"));
        this.setItem(25, createItem(Material.CHEST, ChatColor.GREEN + "Storage", "STORAGE"));

        this.setItem(29, createItem(Material.POTION, ChatColor.GREEN + "Active Effects", "EFFECTS"));
        this.setItem(30, createItem(Material.BONE, ChatColor.GREEN + "Pets", "PETS"));
        this.setItem(31, createItem(Material.WORKBENCH, ChatColor.GREEN + "Crafting Table", "CRAFTING_TABLE"));
        this.setItem(32, Functions.setArmorColor(createItem(Material.LEATHER_CHESTPLATE, ChatColor.GREEN + "Wardrobe", "WARDROBE"), Color.fromBGR(127, 63, 178)));
        this.setItem(33, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Bank", "BANK"), "34e13082-c38c-4a10-9932-509cf78f4ee1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA5Mjk5YTExN2JlZTg4ZDMyNjJmNmFiOTgyMTFmYmEzNDRlY2FlMzliNDdlYzg0ODEyOTcwNmRlZGM4MWU0ZiJ9fX0="));

        this.setItem(47, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Fast Travel", "FAST_TRAVEL"), "b8c1ed51-5698-4a3c-a062-8ffd4bb471ed", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ=="));
        this.setItem(48, createItem(Material.NAME_TAG, ChatColor.GREEN + "Profile Management", "PROFILE"));
        this.setItem(50, createItem(Material.REDSTONE_TORCH_ON, ChatColor.GREEN + "Settings", "SETTINGS"));
        this.setItem(53, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Accessory Bag", "ACCESSORY"), "c3ffd9cc-db06-4eea-ab09-571aa5454092", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0="));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (this.getNBT(e.getCurrentItem()).equals("STATS")) {
            new SkyblockMenu.Stats(this.player);
        } else if (this.getNBT(e.getCurrentItem()).equals("SKILLS")) {
            new SkillMenu(this.player);
        } else if (this.getNBT(e.getCurrentItem()).equals("COLLECTION")) {
        } else if (this.getNBT(e.getCurrentItem()).equals("RECIPE_BOOK")) {
            new RecipesMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("TRADES")) {

        } else if (this.getNBT(e.getCurrentItem()).equals("QUESTS")) {

        } else if (this.getNBT(e.getCurrentItem()).equals("CALENDER")) {

        } else if (this.getNBT(e.getCurrentItem()).equals("STORAGE")) {
            new StorageMenu(this.player);
        } else if (this.getNBT(e.getCurrentItem()).equals("EFFECTS")) {

        } else if (this.getNBT(e.getCurrentItem()).equals("PETS")) {
            new PetMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("CRAFTING_TABLE")) {
            new CraftingTableMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("WARDROBE")) {
            new WardrobeMenu(player, 1);
        } else if (this.getNBT(e.getCurrentItem()).equals("BANK")) {

        } else if (this.getNBT(e.getCurrentItem()).equals("FAST_TRAVEL")) {

        } else if (this.getNBT(e.getCurrentItem()).equals("PROFILE")) {

        } else if (this.getNBT(e.getCurrentItem()).equals("SETTINGS")) {

        } else if (this.getNBT(e.getCurrentItem()).equals("ACCESSORY_BAG")) {

        }
    }

    public static class Stats extends Menu {
        public Stats(PlayerSD player) {
            super(player, "Your Skyblock Profile", 6, InventoryGlassType.ALL, true);
        }

        @Override
        public void update() {
            this.setItem(4, Functions.applyHead(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Your Skyblock Profile", "STATS", player.getStats().stream().map(s -> s.type.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(s.amount)).collect(Collectors.toList())), this.player.getPlayer()));

            int i = 0;
            for (Stat stat : this.player.getStats()) {
                this.setItem(Functions.intToSlot(i), createItem(stat.type.getItem().getType(), stat.type.getItem().getDurability(), stat.type.getIconAndText() + " " + Functions.getNumberFormat(stat.amount), stat.type.name()));
                if (stat.type == StatType.DEFENSE) {
                    this.setItem(Functions.intToSlot(i), Functions.setLore(this.getItem(Functions.intToSlot(i)), ChatColor.GRAY + "Effective Health: " + ChatColor.GREEN + Functions.getNumberFormat(player.getStats().get(StatType.HEALTH).amount * (1 + (stat.amount / 100)))));
                }
                i++;
            }
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {

        }
    }

    public static class Command implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
            if (sender instanceof Player) {
                new SkyblockMenu(SkyblockDragons.getPlayer((Player) sender));
            }
            return true;
        }
    }
}
