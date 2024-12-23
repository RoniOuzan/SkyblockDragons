package me.maxiiiiii.skyblockdragons.inventory.menus;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.CraftingTableMenu;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.RecipesMenu;
import me.maxiiiiii.skyblockdragons.item.pet.PetMenu;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.accessorybag.AccessoryBag;
import me.maxiiiiii.skyblockdragons.player.accessorybag.AccessoryBagMenu;
import me.maxiiiiii.skyblockdragons.player.accessorybag.PowerStone;
import me.maxiiiiii.skyblockdragons.player.bank.BankMenu;
import me.maxiiiiii.skyblockdragons.player.skill.SkillMenu;
import me.maxiiiiii.skyblockdragons.player.storage.StorageMenu;
import me.maxiiiiii.skyblockdragons.player.wardrobe.WardrobeMenu;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.warp.FastTravelMenu;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SkyblockMenu extends Menu {
    public SkyblockMenu(PlayerSD player) {
        super(player, "Skyblock Menu", 6, InventoryGlassType.ALL, true);
    }

    @Override
    public void update() {
        List<String> skullLores = Functions.loreBuilder("View your equipment, stats, and more!");
        skullLores.add("");
        skullLores.addAll(player.getStats().toCombatList().stream().map(Stat::toStringLore).collect(Collectors.toList()));
        skullLores.addAll(player.getStats().toMiscList().stream().map(Stat::toStringLore).collect(Collectors.toList()));
        skullLores.add("");
        skullLores.add(ChatColor.YELLOW + "Click to view!");

        this.setItem(13, Functions.applyHead(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Your Skyblock Profile", "PROFILE", skullLores), this.player.getPlayer()));
        this.setItem(19, createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Your Skills", "SKILLS", ChatColor.GRAY + "View your Skill progression and", ChatColor.GRAY + "rewards.", "", ChatColor.GRAY + "Skill Average: " + ChatColor.GOLD + (player.getSkills().getAverage() + "").replace(".0", ""), "", ChatColor.YELLOW + "Click to view!"));
        this.setItem(20, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Kits", "KITS", ChatColor.GRAY + "Claim tools and armors with", ChatColor.GRAY + "your rank.", "", ChatColor.YELLOW + "Click to view!"), "eb1f0030-7c48-4fe3-8a92-45396a44fedd", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTg3MDhkNGI0YWIxMGI5YmE4NWVkMWE5MjQyYmY4MTEwNWM1NTk2ZDc0M2YyY2EyMGEzMzg3ZTI5ZDA2MzM0NSJ9fX0="));
        this.setItem(21, createItem(Material.BOOK, ChatColor.GREEN + "Recipe Book", "RECIPE_BOOK", ChatColor.GRAY + "View the recipes to make things", ChatColor.GRAY + "and progress.", "", ChatColor.YELLOW + "Click to view!"));
        this.setItem(22, createItem(Material.EMERALD, ChatColor.GREEN + "Shop", "SHOP", ChatColor.GRAY + "Buy items to decorate, craft", ChatColor.GRAY + " and trade.", "", ChatColor.YELLOW + "Click to view!"));
        this.setItem(23, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Warps", "FAST_TRAVEL", ChatColor.GRAY + "Teleport to islands you", ChatColor.GRAY + "have already visited.", "", ChatColor.YELLOW + "Click to view!"), "b8c1ed51-5698-4a3c-a062-8ffd4bb471ed", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ=="));
        this.setItem(24, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Daily", "DAILY", ChatColor.GRAY + "Claim daily rewards with your", ChatColor.GRAY + "rank.", "", ChatColor.YELLOW + "Click to view!"), "36aa6338-5f47-4ede-afb7-6c37a3e1538d", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTJjNjJkODc2NjNjNTg4YWNiZDdjYWZiNWVlZjZhYTNjODg2YWVhZmU3YWJhYzJiOGE1MjcyYzM4OThhNWRhYyJ9fX0="));
        this.setItem(25, createItem(Material.CHEST, ChatColor.GREEN + "Storage", "STORAGE", ChatColor.GRAY + "Store global items that you want", ChatColor.GRAY + "access at any time form", ChatColor.GRAY + "anywhere here.", "", ChatColor.YELLOW + "Click to view!"));

        this.setItem(29, createItem(Material.WRITTEN_BOOK, ChatColor.GREEN + "Quests", "QUESTS", ChatColor.GRAY + "View your quests, progress", ChatColor.GRAY + "and rewards.", "", ChatColor.YELLOW + "Click to view!"));
        this.setItem(30, createItem(Material.BONE, ChatColor.GREEN + "Pets", "PETS", ChatColor.GRAY + "View and manage all of your", ChatColor.GRAY + "Pets.", "", ChatColor.GRAY + "Level up your pets faster by", ChatColor.GRAY + "gaining xp in their favorite", ChatColor.GRAY + "Skill!", "", ChatColor.GRAY + "Selected Pet: " + (player.getPlayerPet().getActivePetSlot() >= 0 ? player.getActivePetMaterial().getRarity().getColor() + player.getActivePetMaterial().getName() : ChatColor.RED + "None!"), "", ChatColor.YELLOW + "Click to view!"));
        this.setItem(31, createItem(Material.WORKBENCH, ChatColor.GREEN + "Crafting Table", "CRAFTING_TABLE", ChatColor.GRAY + "Craft items from the recipe", ChatColor.GRAY + "book.", "", ChatColor.YELLOW + "Click to view!"));
        this.setItem(32, Functions.setArmorColor(createItem(Material.LEATHER_CHESTPLATE, ChatColor.GREEN + "Wardrobe", "WARDROBE", ChatColor.GRAY + "Store armor sets and quickly", ChatColor.GRAY + "swap between them!", "", ChatColor.YELLOW + "Click to view!"), Color.fromBGR(127, 63, 178)));
        this.setItem(33, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Bank", "BANK", ChatColor.GRAY + "Contact your banker from", ChatColor.GRAY + "anywhere.", "", ChatColor.YELLOW + "Click to view!"), "34e13082-c38c-4a10-9932-509cf78f4ee1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA5Mjk5YTExN2JlZTg4ZDMyNjJmNmFiOTgyMTFmYmEzNDRlY2FlMzliNDdlYzg0ODEyOTcwNmRlZGM4MWU0ZiJ9fX0="));
        this.setItem(40, createItem(Material.GOLD_INGOT, ChatColor.YELLOW + "Sell", "SELL", ChatColor.GRAY + "Sell useful items to get back", ChatColor.GRAY + "their value with " + ChatColor.GOLD + "Coins" + ChatColor.GRAY + ".", "", ChatColor.YELLOW + "Click to view!"));

        if (player.getWorld().getName().equals("ASkyBlock"))
            this.setItem(47, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Warp to Hub", "HUB_WARP", ChatColor.GRAY + "Warp to the hub and explore", ChatColor.GRAY + "the other islands to progress.", "", ChatColor.YELLOW + "Click to warp!"), "b8c1ed51-5698-4a3c-a062-8ffd4bb471ed", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ=="));
        else
            this.setItem(47, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Warp to Your Island", "ISLAND_WARP", ChatColor.GRAY + "Warp to your private island", ChatColor.GRAY + "to store tools.", "", ChatColor.YELLOW + "Click to warp!"), "b8c1ed51-5698-4a3c-a062-8ffd4bb471ed", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ=="));

        this.setItem(49, createItem(Material.REDSTONE_TORCH_ON, ChatColor.GREEN + "Settings", "SETTINGS", ChatColor.GRAY + "View and edit your Skyblock", ChatColor.GRAY + "settings.", "", ChatColor.YELLOW + "Click to view!"));
        this.setItem(51, createItem(Material.POTION, ChatColor.GREEN + "Active Effects", "EFFECTS", ChatColor.GRAY + "View all of your active", ChatColor.GRAY + "potion effects.", "", ChatColor.YELLOW + "Click to view!"));

//        this.setItem(48, createItem(Material.NAME_TAG, ChatColor.GREEN + "Profile Management", "PROFILE"));
        AccessoryBag accessoryBag = player.getItems().getAccessoryBag();
        List<String> accessoryBagLores = new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "A special bag which can hold",
                ChatColor.GRAY + "Accessories within it.",
                "",
                ChatColor.GRAY + "Magical Power: " + ChatColor.GOLD + accessoryBag.getMagicalPower(),
                ChatColor.GRAY + "Selected Power Stone: " + ChatColor.GREEN + accessoryBag.getPowerStone().getName()
        ));
        if (accessoryBag.getPowerStone() != PowerStone.NONE) {
            accessoryBagLores.addAll(accessoryBag.getStats().stream().filter(s -> !s.isEmpty()).map(Stat::toAddLore).collect(Collectors.toList()));
        }
        accessoryBagLores.add("");
        accessoryBagLores.add(ChatColor.YELLOW + "Click to view!");
        this.setItem(53, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Accessory Bag", "ACCESSORY", accessoryBagLores), "c3ffd9cc-db06-4eea-ab09-571aa5454092", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0="));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (this.getNBT(e.getCurrentItem()).equals("PROFILE")) {
            new ProfileMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("SKILLS")) {
            new SkillMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("KITS")) {
            player.performCommand("dm open kits");
        } else if (this.getNBT(e.getCurrentItem()).equals("RECIPE_BOOK")) {
            new RecipesMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("SHOP")) {
            player.chat("/shop");
        } else if (this.getNBT(e.getCurrentItem()).equals("QUESTS")) {
//            player.performCommand("quests");
            player.sendMessage("§cComing soon...");
        } else if (this.getNBT(e.getCurrentItem()).equals("DAILY")) {
            player.performCommand("dm open rewards");
        } else if (this.getNBT(e.getCurrentItem()).equals("STORAGE")) {
            new StorageMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("EFFECTS")) {
            player.sendMessage("§cComing soon...");
        } else if (this.getNBT(e.getCurrentItem()).equals("PETS")) {
            new PetMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("CRAFTING_TABLE")) {
            new CraftingTableMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("WARDROBE")) {
            new WardrobeMenu(player, 1);
        } else if (this.getNBT(e.getCurrentItem()).equals("BANK")) {
            BankMenu.openBank(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("FAST_TRAVEL")) {
            new FastTravelMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("PROFILE")) {
            new ProfileMenu(player);
        } else if (this.getNBT(e.getCurrentItem()).equals("SETTINGS")) {
//            player.performCommand("settings");
            player.sendMessage("§cComing soon...");
        } else if (this.getNBT(e.getCurrentItem()).equals("ISLAND_WARP")) {
            player.performCommand("askyblock:island");
        } else if (this.getNBT(e.getCurrentItem()).equals("HUB_WARP")) {
            player.warp(Warp.HUB);
        } else if (this.getNBT(e.getCurrentItem()).equals("SELL")) {
            // TODO
        } else if (this.getNBT(e.getCurrentItem()).equals("ACCESSORY")) {
            new AccessoryBagMenu(player);
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
