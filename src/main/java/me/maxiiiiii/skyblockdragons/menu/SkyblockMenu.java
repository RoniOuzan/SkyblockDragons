package me.maxiiiiii.skyblockdragons.menu;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Stat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class SkyblockMenu {
    public static void openSkyblockMenu(Player player) {
        Inventory inv = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Skyblock Menu");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta glassItemMeta = glass.getItemMeta();
        glassItemMeta.setDisplayName(ChatColor.RESET + "");
        glass.setItemMeta(glassItemMeta);

        for (int i = 0; i < 54; i++) {
            inv.setItem(i, glass);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeItemMeta = close.getItemMeta();
        closeItemMeta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(closeItemMeta);
        close.getItemMeta().setLore(Arrays.asList("", ChatColor.YELLOW + "Click to close!"));
        inv.setItem(49, close);

        ItemStack playerStats = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) playerStats.getItemMeta();
        skullMeta.setDisplayName(ChatColor.GREEN + "Your Skyblock Profile");
        if (!skullMeta.hasOwner()) {
            skullMeta.setOwner(player.getName());
        }
        playerStats.setItemMeta(skullMeta);
        Functions.setLore(playerStats, new ArrayList<>(Arrays.asList(
            Stat.HEALTH.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getHealth()),
            Stat.DEFENSE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getDefense()),
            Stat.SPEED.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getSpeed() + "%"),
            Stat.INTELLIGENCE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getIntelligence()),
            Stat.DAMAGE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getDamage()),
            Stat.STRENGTH.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getStrength()),
            Stat.CRIT_DAMAGE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getCritDamage() + "%"),
            Stat.CRIT_CHANCE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getCritChance() + "%"),
            Stat.ATTACK_SPEED.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getAttackSpeed() + "%"),
            Stat.FEROCITY.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getFerocity())
        )));
        inv.setItem(13, playerStats);

        ItemStack skills = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta skillsItemMeta = skills.getItemMeta();
        skillsItemMeta.setDisplayName(ChatColor.GREEN + "Your Skills");
        skills.setItemMeta(skillsItemMeta);
        inv.setItem(19, skills);

        ItemStack collection = new ItemStack(Material.PAINTING);
        ItemMeta collectionItemMeta = collection.getItemMeta();
        collectionItemMeta.setDisplayName(ChatColor.GREEN + "Collection");
        collection.setItemMeta(collectionItemMeta);
        inv.setItem(20, collection);

        ItemStack recipes = new ItemStack(Material.BOOK);
        ItemMeta recipesItemMeta = recipes.getItemMeta();
        recipesItemMeta.setDisplayName(ChatColor.GREEN + "Recipe Book");
        recipes.setItemMeta(recipesItemMeta);
        inv.setItem(21, recipes);

        ItemStack trades = new ItemStack(Material.EMERALD);
        ItemMeta tradesItemMeta = trades.getItemMeta();
        tradesItemMeta.setDisplayName(ChatColor.GREEN + "Your Skills");
        trades.setItemMeta(tradesItemMeta);
        inv.setItem(22, trades);

        ItemStack questLog = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta questLogItemMeta = questLog.getItemMeta();
        questLogItemMeta.setDisplayName(ChatColor.GREEN + "Your Skills");
        questLog.setItemMeta(questLogItemMeta);
        inv.setItem(23, questLog);

        ItemStack calenderAndEvents = new ItemStack(Material.WATCH);
        ItemMeta calenderAndEventsItemMeta = calenderAndEvents.getItemMeta();
        calenderAndEventsItemMeta.setDisplayName(ChatColor.GREEN + "Calender And Events");
        calenderAndEvents.setItemMeta(calenderAndEventsItemMeta);
        inv.setItem(24, calenderAndEvents);

        ItemStack storage = new ItemStack(Material.CHEST);
        ItemMeta storageItemMeta = storage.getItemMeta();
        storageItemMeta.setDisplayName(ChatColor.GREEN + "Storage");
        storage.setItemMeta(storageItemMeta);
        inv.setItem(25, storage);

        ItemStack activeEffects = new ItemStack(Material.POTION);
        ItemMeta activeEffectsItemMeta = activeEffects.getItemMeta();
        activeEffectsItemMeta.setDisplayName(ChatColor.GREEN + "Active Effects");
        activeEffects.setItemMeta(activeEffectsItemMeta);
        inv.setItem(29, activeEffects);

        ItemStack pets = new ItemStack(Material.BONE);
        ItemMeta petsItemMeta = pets.getItemMeta();
        petsItemMeta.setDisplayName(ChatColor.GREEN + "Pets");
        pets.setItemMeta(petsItemMeta);
        inv.setItem(30, pets);

        ItemStack craftingTable = new ItemStack(Material.WORKBENCH);
        ItemMeta craftingTableItemMeta = craftingTable.getItemMeta();
        craftingTableItemMeta.setDisplayName(ChatColor.GREEN + "Crafting Table");
        craftingTable.setItemMeta(craftingTableItemMeta);
        inv.setItem(31, craftingTable);

        ItemStack wardrobe = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta leatherArmorMeta = wardrobe.hasItemMeta() ? (LeatherArmorMeta) wardrobe.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(wardrobe.getType());
        leatherArmorMeta.setColor(Color.fromRGB(127, 63, 178));
        leatherArmorMeta.setDisplayName(ChatColor.GREEN + "Wardrobe");
        wardrobe.setItemMeta(leatherArmorMeta);
        inv.setItem(32, wardrobe);

        ItemStack bank = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta bankItemMeta = bank.getItemMeta();
        bankItemMeta.setDisplayName(ChatColor.GREEN + "Bank");
        bank.setItemMeta(bankItemMeta);
        Functions.getSkull(bank, "34e13082-c38c-4a10-9932-509cf78f4ee1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA5Mjk5YTExN2JlZTg4ZDMyNjJmNmFiOTgyMTFmYmEzNDRlY2FlMzliNDdlYzg0ODEyOTcwNmRlZGM4MWU0ZiJ9fX0=");
        inv.setItem(33, bank);

        ItemStack fastTravel = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta fastTravelItemMeta = fastTravel.getItemMeta();
        fastTravelItemMeta.setDisplayName(ChatColor.AQUA + "Fast Travel");
        fastTravel.setItemMeta(fastTravelItemMeta);
        Functions.getSkull(fastTravel, "b8c1ed51-5698-4a3c-a062-8ffd4bb471ed", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ==");
        inv.setItem(47, fastTravel);

        ItemStack profile = new ItemStack(Material.NAME_TAG);
        ItemMeta profileItemMeta = profile.getItemMeta();
        profileItemMeta.setDisplayName(ChatColor.GREEN + "Profile Management");
        profile.setItemMeta(profileItemMeta);
        inv.setItem(48, profile);

        ItemStack settings = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta settingsItemMeta = settings.getItemMeta();
        settingsItemMeta.setDisplayName(ChatColor.GREEN + "Settings");
        settings.setItemMeta(settingsItemMeta);
        inv.setItem(50, settings);

        ItemStack accessoryBag = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta accessoryBagItemMeta = accessoryBag.getItemMeta();
        accessoryBagItemMeta.setDisplayName(ChatColor.GREEN + "Accessory Bag");
        accessoryBag.setItemMeta(accessoryBagItemMeta);
        Functions.getSkull(accessoryBag, "c3ffd9cc-db06-4eea-ab09-571aa5454092", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0=");
        inv.setItem(53, accessoryBag);

        player.openInventory(inv);
    }

    public static void openSkyblockProfileMenu(Player player) {
        Inventory inv = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Your Skyblock Profile");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta glassItemMeta = glass.getItemMeta();
        glassItemMeta.setDisplayName(ChatColor.RESET + "");
        glass.setItemMeta(glassItemMeta);

        for (int i = 0; i < 54; i++) {
            inv.setItem(i, glass);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeItemMeta = close.getItemMeta();
        closeItemMeta.setDisplayName(ChatColor.GREEN + "Close");
        closeItemMeta.setLore(Arrays.asList("", ChatColor.YELLOW + "Click to close!"));
        close.setItemMeta(closeItemMeta);
        inv.setItem(49, close);

        ItemStack goBack = new ItemStack(Material.ARROW);
        ItemMeta goBackItemMeta = goBack.getItemMeta();
        goBackItemMeta.setDisplayName(ChatColor.GREEN + "Go Back");
        goBackItemMeta.setLore(Arrays.asList("", ChatColor.YELLOW + "Click to Go Back!"));
        goBack.setItemMeta(goBackItemMeta);
        inv.setItem(48, goBack);

        ItemStack playerStats = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) playerStats.getItemMeta();
        skullMeta.setDisplayName(ChatColor.GREEN + "Your Skyblock Profile");
        if (!skullMeta.hasOwner()) {
            skullMeta.setOwner(player.getName());
        }
        playerStats.setItemMeta(skullMeta);
        Functions.setLore(playerStats, new ArrayList<>(Arrays.asList(
            Stat.HEALTH.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getHealth()),
            Stat.DEFENSE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getDefense()),
            Stat.SPEED.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getSpeed() + "%"),
            Stat.INTELLIGENCE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getIntelligence()),
            Stat.DAMAGE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getDamage()),
            Stat.STRENGTH.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getStrength()),
            Stat.CRIT_DAMAGE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getCritDamage() + "%"),
            Stat.CRIT_CHANCE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getCritChance() + "%"),
            Stat.ATTACK_SPEED.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getAttackSpeed() + "%"),
            Stat.FEROCITY.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getFerocity())
        )));
        inv.setItem(4, playerStats);

        ItemStack health = new ItemStack(Material.GOLDEN_APPLE);
        Functions.setName(health, Stat.HEALTH.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getHealth()));
        inv.setItem(19, health);

        ItemStack defense = new ItemStack(Material.IRON_CHESTPLATE);
        Functions.setName(defense, Stat.DEFENSE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getDefense()));
        inv.setItem(20, defense);

        ItemStack speed = new ItemStack(Material.SUGAR);
        Functions.setName(speed, Stat.SPEED.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getSpeed()) + "%");
        inv.setItem(21, speed);

        ItemStack intelligence = new ItemStack(Material.ENCHANTED_BOOK);
        Functions.setName(intelligence, Stat.INTELLIGENCE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getIntelligence()));
        inv.setItem(22, intelligence);

        ItemStack damage = new ItemStack(Material.BLAZE_ROD);
        Functions.setName(damage, Stat.DAMAGE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getDamage()));
        inv.setItem(23, damage);

        ItemStack strength = new ItemStack(Material.BLAZE_POWDER);
        Functions.setName(strength, Stat.STRENGTH.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getStrength()));
        inv.setItem(24, strength);

        ItemStack critDamage = new ItemStack(Material.INK_SACK, 1, (short) 4);
        Functions.setName(critDamage, Stat.CRIT_DAMAGE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getCritDamage()) + "%");
        inv.setItem(25, critDamage);

        ItemStack critChance = new ItemStack(Material.INK_SACK, 1, (short) 4);
        Functions.setName(critChance, Stat.CRIT_CHANCE.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getCritChance()) + "%");
        inv.setItem(28, critChance);

        ItemStack attackSpeed = new ItemStack(Material.GOLD_AXE);
        Functions.setName(attackSpeed, Stat.ATTACK_SPEED.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getAttackSpeed()) + "%");
        inv.setItem(29, attackSpeed);

        ItemStack ferocity = new ItemStack(Material.INK_SACK, 1, (short) 1);
        Functions.setName(ferocity, Stat.FEROCITY.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(SkyblockDragons.players.get(player.getUniqueId()).getFerocity()));
        inv.setItem(30, ferocity);

        player.openInventory(inv);
    }
}
