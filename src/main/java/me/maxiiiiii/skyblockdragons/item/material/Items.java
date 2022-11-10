package me.maxiiiiii.skyblockdragons.item.material;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.material.types.*;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class Items {
    public static Map<String, ItemMaterial> items = new LinkedHashMap<>();
    public static Map<String, ItemMaterial> itemMaterials = new HashMap<>();
    public static Map<String, PetMaterial> pets = new HashMap<>();
    public static Map<String, ItemMaterial> vanillaMaterials = new HashMap<>();

    public static ItemMaterial NULL = null;

    public static void registerItems() {
        Set<Class<? extends ItemMaterial>> materials = new Reflections("me.maxiiiiii").getSubTypesOf(ItemMaterial.class).stream().filter(c -> !Modifier.isAbstract(c.getModifiers())).collect(Collectors.toSet());
        for (Class<? extends ItemMaterial> materialClass : materials) {
            if (materialClass.isAnnotationPresent(MaterialUnregisters.class) ||
                    QuickMaterial.class.isAssignableFrom(materialClass) ||
                    VanillaMaterial.class.isAssignableFrom(materialClass)
            ) continue;

            try {
                ItemMaterial material = materialClass.newInstance();

                if (material instanceof PetMaterial)
                    pets.put(material.getItemID(), (PetMaterial) material);
                else
                    items.put(material.getItemID(), material);
            } catch (InstantiationException | IllegalAccessException e) {
                SkyblockDragons.logger.severe("Failed to register material " + e.getMessage());
            }
        }

        // Wither Island
        items.put("WITHER_SHARD", new QuickNormalMaterial(Material.FLINT, ItemFamily.ITEM, "Wither Shard", ItemType.ITEM, Rarity.EPIC, "", "", "", false, true, true));
        items.put("WITHER_SKULL", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Wither Skull", ItemType.ITEM, Rarity.EPIC, "d6e9ad20-9dc1-4e9e-b97f-fa663ece60a0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRhOTdjMDZjMDdhMDM1YmQ4NzQ2YjQ4ZDMyZGMwNGU4NDE2MGJkMTYxZjI2YmUxMjcyYjYyNzEyNTFhYWE3In19fQ==", "Used to spawn the wither boss", false, false, false));

        items.put("WITHER_STONE_STRENGTH", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Ares crystal", ItemType.ITEM, Rarity.LEGENDARY, "b83ce84d-5a82-4111-a1cf-7e797aeb4849", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY3MzZmZDk1ZDNhNmE0YWFhYzQ2NzA5YTA3YWVjN2YxYzM4ZjBhM2FhZTU3M2U2ZjQ4MzM4ODgxOTQxMmI2NSJ9fX0=", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.STRENGTH.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));
        items.put("WITHER_STONE_HEALTH", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Phanes crystal", ItemType.ITEM, Rarity.LEGENDARY, "eb2e049e-5563-4df7-be8a-1c89123a79a1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU0ODYxNWRmNmI3ZGRmM2FkNDk1MDQxODc2ZDkxNjliZGM5ODNhM2ZhNjlhMmFjYTEwN2U4ZjI1MWY3Njg3In19fQ==", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.HEALTH.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));
        items.put("WITHER_STONE_SPEED", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Hermes crystal", ItemType.ITEM, Rarity.LEGENDARY, "ca749fda-0403-496c-9ebe-7c770c653135", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTA2ZTZkODNjZjdlZDVjZjdiZjBlMDE4ZWNiNjAzOWIwNDZkOGRjNmRiNTU2OTAxNGZjYWIzN2I2MTdmMTM5OSJ9fX0=", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.SPEED.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));
        items.put("WITHER_STONE_CRIT", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Demeter crystal", ItemType.ITEM, Rarity.LEGENDARY, "9f04e709-14a0-4f82-ba72-2bc48f1f585c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVlZjY5NDY0NjI0ZjhlNmY4ZWI5NjI3MWUwMjY4MmZmOTY3NTUzYzFlMTg5NzMzZmMyNTQ2YjE4ZDk3In19fQ==", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.CRIT_DAMAGE.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));
        items.put("WITHER_STONE_INTELLIGENCE", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Athena crystal", ItemType.ITEM, Rarity.LEGENDARY, "14661ef6-e01b-4c8d-87ce-a9ba7ca61b1d", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE1ZjI5YTc2ZDFmOTFjMTY1ZjYzYmFhYzA0ODY3MGU3YjFkMzdjZTc4NWE0ZDljMjFkOGMzYTE3N2I1In19fQ==", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.INTELLIGENCE.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));

        // Normal Items
        items.put("RECOMBABULATOR", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Recombobulator 3000", ItemType.ITEM, Rarity.LEGENDARY, "96538e7f-6b56-3557-9b7d-458afe4239e9", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdjY2QzNmRjOGY3MmFkY2IxZjhjOGU2MWVlODJjZDk2ZWFkMTQwY2YyYTE2YTEzNjZiZTliNWE4ZTNjYzNmYyJ9fX0K", "Use in a " + ChatColor.GREEN + "Reforge Anvil "+ ChatColor.GRAY + "or at the Dungeon Blacksmith to permanently increase the rarity of a piece of armor, weapon or tool. An item's rarity can only be upgraded once!", false, false, false));
        items.put("HOT_POTATO_BOOK", new QuickNormalMaterial(Material.BOOK, ItemFamily.HOT_POTATO, "Hot Potato Book", ItemType.ITEM, Rarity.EPIC, "", "", "Combine this Book in an Anvil with a weapon or armor piece to gain a small but permanent stat boost!", true, false, false));
        items.put("FUMING_POTATO_BOOK", new QuickNormalMaterial(Material.BOOK, ItemFamily.HOT_POTATO, "Fuming Potato Book", ItemType.ITEM, Rarity.EPIC, "", "", "Use in an anvil to combine this book with a weapon or armor pieace to gain a small but permanent stat boost!" + " NEW_LINE NEW_LINE " + "This book bypasses the Hot Potato Book limit of 10, allowing you to upgrade an item up to " + ChatColor.GREEN + "15 " + ChatColor.GRAY + "times!", true, false, false));
        items.put("SKYBLOCK_MENU", new QuickNormalMaterial(Material.NETHER_STAR, ItemFamily.NULL, "Skyblock Menu", ItemType.ITEM, Rarity.UNCOMMON, "", "", ChatColor.GRAY + "View all of your Skyblock progress, including your Skills, Recipes and more!", true, false, false));
        items.put("SKYBLOCK_MENU_ARROW", new QuickNormalMaterial(Material.ARROW, ItemFamily.NULL, "Skyblock Menu", ItemType.ITEM, Rarity.UNCOMMON, "", "", ChatColor.GRAY + "View all of your Skyblock progress, including your Skills, Recipes and more!", true, false, true));

        items.put("SMALL_BACKPACK", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Small Backpack", ItemType.ITEM, Rarity.UNCOMMON, "0523fc65-c396-309b-b153-de7d8e5f666d", "eyJ0aW1lc3RhbXAiOjE1NjgyMTI5NjI1MzMsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMWQ4MzdjYTIyMmNiYzBiYzEyNDI2ZjVkYTAxOGMzYTkzMWI0MDYwMDg4MDA5NjBhOWRmMTEyYTU5NmU3ZDYyIn19fQ==", ChatColor.GRAY + "A bag with 9 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("MEDIUM_BACKPACK", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Medium Backpack", ItemType.ITEM, Rarity.RARE, "f1e3c520-3f66-38f0-a110-70e3c183df38", "eyJ0aW1lc3RhbXAiOjE1NjgyMTMwNTE0MjYsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmYzYjNhMDU0ODFjZGU3NzI0MDAwNWMwZGRjZWUxYzA2OWU1NTA0YTYyY2UwOTc3ODc5ZjU1YTM5Mzk2MTQ2In19fQ", ChatColor.GRAY + "A bag with 18 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("LARGE_BACKPACK", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Large Backpack", ItemType.ITEM, Rarity.EPIC, "f1e3c520-3f66-38f0-a110-70e3c183df38", "eyJ0aW1lc3RhbXAiOjE1NjgyMTMwNTE0MjYsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmYzYjNhMDU0ODFjZGU3NzI0MDAwNWMwZGRjZWUxYzA2OWU1NTA0YTYyY2UwOTc3ODc5ZjU1YTM5Mzk2MTQ2In19fQ", ChatColor.GRAY + "A bag with 27 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("GREATER_BACKPACK", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Greater Backpack", ItemType.ITEM, Rarity.EPIC, "f1e3c520-3f66-38f0-a110-70e3c183df38", "eyJ0aW1lc3RhbXAiOjE1NjgyMTMwNTE0MjYsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmYzYjNhMDU0ODFjZGU3NzI0MDAwNWMwZGRjZWUxYzA2OWU1NTA0YTYyY2UwOTc3ODc5ZjU1YTM5Mzk2MTQ2In19fQ", ChatColor.GRAY + "A bag with 36 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("JUMBO_BACKPACK", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Jumbo Backpack", ItemType.ITEM, Rarity.LEGENDARY, "49a240cd-8e28-3f5e-8bd3-b690ff1cabfc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWY4NDA1MTE2YzFkYWE3Y2UyZjAxMjU5MTQ1OGQ1MDI0NmQwYTQ2N2JjYjk1YTVhMmMwMzNhZWZkNjAwOGI2MyJ9fX0K", ChatColor.GRAY + "A bag with 45 slots which can be placed in your Storage Menu to store additional items.", false, false, false));

        items.put("EYE_OF_ENDERMAN", new QuickNormalMaterial(Material.ENDER_PEARL, ItemFamily.ENDERMAN, "Eye of Enderman", ItemType.ITEM, Rarity.RARE, "", "", "", true, true, true));
        items.put("EYE_OF_ENDER_GUARD", new QuickNormalMaterial(Material.EYE_OF_ENDER, ItemFamily.ENDERMAN, "Eye of Ender Guard", ItemType.ITEM, Rarity.EPIC, "", "", "", true, true, true));
        items.put("SUMMONING_EYE", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.NULL, "Summoning Eye", ItemType.ITEM, Rarity.EPIC, "36122cdc-6c97-4b97-990a-ef4df57db922", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFhOGZjOGRlNjQxN2I0OGQ0OGM4MGI0NDNjZjUzMjZlM2Q5ZGE0ZGJlOWIyNWZjZDQ5NTQ5ZDk2MTY4ZmMwIn19fQ", "Use this in " + ChatColor.DARK_PURPLE + "The End " + ChatColor.GRAY + "to summon Ender Dragons!", false, false, false));
        items.put("ADMIN_SUMMONING_EYE", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.NULL, "ADMIN Summoning Eye", ItemType.ITEM, Rarity.MYTHIC, "36122cdc-6c97-4b97-990a-ef4df57db922", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFhOGZjOGRlNjQxN2I0OGQ0OGM4MGI0NDNjZjUzMjZlM2Q5ZGE0ZGJlOWIyNWZjZDQ5NTQ5ZDk2MTY4ZmMwIn19fQ", "Use this in " + ChatColor.DARK_PURPLE + "The End " + ChatColor.GRAY + "to summon Ender Dragons! ADMIN ITEM!!!!", false, false, false));
        items.put("FROZEN_KEY", new QuickNormalMaterial(Material.TRIPWIRE_HOOK, ItemFamily.NULL, "Frozen Key", ItemType.ITEM, Rarity.LEGENDARY, "", "", "Use that" + ChatColor.GRAY + "to summon" + ChatColor.AQUA + "Frozen Dragon!", true, false, false));

        items.put("SUPERIOR_DRAGON_FRAGMENT", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.SUPERIOR_DRAGON, "Superior Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "7c3c5222-2aca-47c0-a1fc-493fec60f166", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmY4OWIxNTBiZTljNGM1MjQ5ZjM1NWY2OGVhMGM0MzkxMzAwYTliZTFmMjYwZDc1MGZjMzVhMTgxN2FkNzk2ZSJ9fX0=", false, true, true));
        items.put("STRONG_DRAGON_FRAGMENT", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.STRONG_DRAGON, "Strong Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "bab4c3e4-6e41-4d46-a3f7-17fb8b7f34a4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVlMzJmYmQ0YzdiMDNiODY5MDc4YWExZjQ5M2EzOTBlNmUxM2I0NjFkNjEzNzA3ZWFmYjMyNmRiY2QyYjRiNSJ9fX0=", false, true, true));
        items.put("YOUNG_DRAGON_FRAGMENT", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.YOUNG_DRAGON, "Young Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "e9a59e69-331d-4022-83ce-3bf7b3deb85a", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI1YmQ2YjY0ZThiZDZjNThmNWNkMWU3OWE1NTAyZDQ0NDhiYWZjMDA2ZDJmZTA1NjhmNmEwZDZiODZkNDQ5ZSJ9fX0=", false, true, true));
        items.put("UNSTABLE_DRAGON_FRAGMENT", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.UNSTABLE_DRAGON, "Unstable Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "990ea925-bb21-4c72-a5b6-229667ae2ac1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTgyMjhjMjM0YzM5MDNjNTEyYTVhMGFhNDUyNjBlN2I1NjdlMGUyMGVlZmM3ZDU2MWNjZWM5N2IyOTU4NzFhZiJ9fX0", false, true, true));
        items.put("WISE_DRAGON_FRAGMENT", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.WISE_DRAGON, "Wise Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "66c875ab-a643-43a2-920f-c7a6d217eb26", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ3NjIwYjJlNDkzNDk2M2JiMTI1MDgzMTBkMDU0OTRjMDY3ZGMzM2UwMDhjZWNmMmNkN2I0NTQ5NjU0ZmFiMyJ9fX0=", false, true, true));
        items.put("PROTECTOR_DRAGON_FRAGMENT", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.SUPERIOR_DRAGON, "Protector Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "1d128fd0-bc28-4fe4-8cdd-17e9db01fe35", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDhkZTMzOWFmNjNhMjI5YzkyMzhkMDI3ZTQ3ZjUzZWViNTYxNDFhNDE5ZjUxYjM1YzMxZWExNDk0YjQzNWRkMyJ9fX0=", false, true, true));
        items.put("OLD_DRAGON_FRAGMENT", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.OLD_DRAGON, "Old Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "9479343f-f19a-419a-a12e-a16880fd21ec", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FhMDlhZDE3N2ZiY2NjNTNmYTMxNmNjMDRiZGQyYzkzNjZiYWVkODg5ZGY3NmM1YTI5ZGVmZWE4MTcwZGVmNSJ9fX0=", false, true, true));

        items.put("COBALT", new QuickNormalMaterial(Material.PRISMARINE_SHARD, ItemFamily.COBALT, "Cobalt", ItemType.ITEM, Rarity.COMMON, "", "", false, true, true));
        items.put("CHLOROPHYTE", new QuickNormalMaterial(Material.EMERALD, ItemFamily.CHLOROPHYTE, "Chlorophyte", ItemType.ITEM, Rarity.UNCOMMON, "", "", false, true, true));
        items.put("LUMINATE", new QuickNormalMaterial(Material.QUARTZ, ItemFamily.LUMINATE, "Luminate", ItemType.ITEM, Rarity.RARE, "", "", false, true, true));
        items.put("DERNIC", new QuickNormalMaterial(Material.INK_SACK, ItemFamily.DERNIC, "Dernic", ItemType.ITEM, Rarity.EPIC, "3", "", false, true, true));
        items.put("HEMATITE", new QuickNormalMaterial(Material.NETHER_BRICK_ITEM, ItemFamily.HEMATITE, "Hematite", ItemType.ITEM, Rarity.LEGENDARY, "", "", false, true, true));
        items.put("VOID_CRYSTAL", new QuickNormalMaterial(Material.NETHER_STAR, ItemFamily.VOID_CRYSTAL, "Void Crystal", ItemType.ITEM, Rarity.MYTHIC, "", "", false, true, true));

        items.put("COBALT_SHARD", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.COBALT, "Cobalt Shard", ItemType.ITEM, Rarity.COMMON, "a0a3a357-99ea-4faa-b2ed-166a001c59d6", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGJmNGQwMzI5NWI1YTlhZDk5MDYxNTM4ZGIwMzIyMDMxNWJkODhlZjkxNzhiODI2YzhjNjI4ZWVkOTg0ZWRmZCJ9fX0=", true, true));
        items.put("CHLOROPHYTE_SHARD", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.CHLOROPHYTE, "Chlorophyte Shard", ItemType.ITEM, Rarity.UNCOMMON, "6e26d3e8-8776-4ecb-8319-b4aa448c8ce5", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTEwM2ExOTUzYjA1NWM2MDE2N2E3YWUyODQwMmVmNTY2YmJjYTI2ZDAxNmNiMGE0N2UzNDY3MjUyYTNlYTA3NiJ9fX0=", true, true));
        items.put("LUMINATE_SHARD", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.LUMINATE, "Luminate Shard", ItemType.ITEM, Rarity.RARE, "d97bce18-26b9-4b6d-a4b6-e468f19f62cd", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmMxMmZmN2RjYWQxYTIzNWEyODliYmI3NTU0MWRjOTlmZTNlNWY0MzkyZmExNmVlNmMwYTgwZmQxOTIyNmExIn19fQ==", true, true));
        items.put("DERNIC_SHARD", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.DERNIC, "Dernic Shard", ItemType.ITEM, Rarity.EPIC, "30bc6893-b5fa-40cc-9138-0265732450cd", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTJhMDEwNjNlZDJhMmJlZjEzN2FmNGEyYWRmZTRhYTE1ZmFjYzY3YTlmNTFlYzdjNjRlYzEwMzZiNjRjMWEzMSJ9fX0=", true, true));
        items.put("HEMATITE_SHARD", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.HEMATITE, "Hematite Shard", ItemType.ITEM, Rarity.LEGENDARY, "204063be-02cc-494f-b754-aaa6df8d2d6f", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E2ZGNmMjc1Y2Y1OGM2NGNhN2I0ZDFmYzRlYTAwOWEyYjU2OTk1ZjUxYjU0OTg3NGJhNzg5ODZjZGVhYjdkMyJ9fX0=", true, true));
        items.put("VOID_CRYSTAL_SHARD", new QuickNormalMaterial(Material.SKULL_ITEM, ItemFamily.VOID_CRYSTAL, "Void Crystal Shard", ItemType.ITEM, Rarity.MYTHIC, "3e7d42f3-7abc-45e3-8c68-4670da688924", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIwMWFlMWE4YTA0ZGY1MjY1NmY1ZTQ4MTNlMWZiY2Y5Nzg3N2RiYmZiYzQyNjhkMDQzMTZkNmY5Zjc1MyJ9fX0=", true, true));


        // Other Items
        items.put("NULL_OVOID", new QuickNormalMaterial(Material.MONSTER_EGG, ItemFamily.NULL, "Null Ovoid", ItemType.ITEM, Rarity.RARE, "58", "", true, true));

        items.putAll(pets);

        for (String itemID : items.keySet()) {
            ItemMaterial material = items.get(itemID);
            if (material.getItemID().equals(QuickMaterial.itemID)) {
                material.setItemID(itemID);
                items.put(itemID, material);
            }
        }

        itemMaterials = new HashMap<>(items);

        for (Material material : Material.values()) {
            if (material == Material.AIR) continue;
            if (!material.isItem()) continue;
            ItemStack itemStack = new ItemStack(material, 1, (short) 0);
            String localName = Functions.getLocalName(itemStack);
            String itemID = material.name();
            if (itemID.equals("SULPHUR"))
                itemID = "GUNPOWDER";
            if (items.containsKey(itemID) || itemID.contains("_NUGGET"))
                continue;
            Rarity rarity = Rarity.COMMON;
            if (Functions.isColorable(material)) {
                if (material == Material.INK_SACK)
                    for (short i = 0; i < 16; i++) {
                        vanillaMaterials.put(Functions.getColorName(15 - i).toUpperCase() + "_DYE", new VanillaMaterial(Functions.getColorName(15 - i).toUpperCase() + "_DYE", material, ItemFamily.VANILLA, Functions.setTitleCase(Functions.getColorName(15 - i) + " DYE"), ItemType.ITEM, rarity, false, false).setData(i));
                    }
                else
                    for (short i = 0; i < 16; i++) {
                        vanillaMaterials.put(Functions.getColorName(i).toUpperCase() + "_" + itemID, new VanillaMaterial(Functions.getColorName(i).toUpperCase() + "_" + itemID, material, ItemFamily.VANILLA, Functions.setTitleCase(Functions.getColorName(i) + " " +  itemID), ItemType.ITEM, rarity, false, false).setData(i));
                    }
            } else {
                short maxDurability = 16;
                for (int i = 1; i < maxDurability; i++) {
                    ItemStack newItemStack = new ItemStack(material, 1, (short) i);
                    String newLocalName = Functions.getLocalName(newItemStack);
                    if (localName.equals(newLocalName)) {
                        break;
                    }
                    vanillaMaterials.put(itemID + ":" + i, new VanillaMaterial(itemID + ":" + i, material, ItemFamily.VANILLA, newLocalName, ItemType.ITEM, rarity, false, false).setData(i));
                }
            }

            if (material == Material.NETHER_STAR || material == Material.BEDROCK)
                rarity = Rarity.LEGENDARY;
            vanillaMaterials.put(itemID, new VanillaMaterial(itemID, material, ItemFamily.VANILLA, localName, ItemType.ITEM, rarity, false, false));
        }
        vanillaMaterials.put("LAPIS", new VanillaMaterial("LAPIS", Material.INK_SACK, ItemFamily.VANILLA, "Lapis Lazuli", ItemType.ITEM, Rarity.COMMON, false, false).setData(4));
        vanillaMaterials.remove("BLUE_DYE");

        items.putAll(vanillaMaterials);

        List<String> keys = new ArrayList<>();
        try {
            for (String material : Items.items.keySet()) {
                ItemMaterial item = Items.items.get(material);
                if (item.getType() == ItemType.ITEM && !material.contains("ENCHANTED_") && !material.contains("BOOK") && !material.contains("BARDING") && !(item instanceof ItemAbilityAble)) {
                    keys.add(material);
                }
            }
            for (String material : keys) {
                ItemMaterial item = Items.get(material);
                int rarityAdder = 1;
                if (item.getMaterial().isBlock()) rarityAdder++;
                String itemID = "ENCHANTED_" + material;
                items.put(itemID, new QuickNormalMaterial(
                        itemID,
                        item.getMaterial(),
                        ItemFamily.ENCHANTED_ITEM,
                        "Enchanted " + item.getName(),
                        ItemType.ITEM,
                        Rarity.values()[item.getRarity().getLevel() + rarityAdder],
                        item.getItemSkull().getId(),
                        item.getItemSkull().getValue(),
                        true,
                        true,
                        true
                ));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        NULL = new QuickNormalMaterial(Material.BARRIER, ItemFamily.NULL, "Null", ItemType.NULL, Rarity.SPECIAL, ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.");
    }

    public static ItemMaterial get(String name) {
        String[] splitName = name.split(":");
        name = splitName[0];
        if (Functions.isInt(name)){
            name = Material.getMaterial(Functions.getIntOrDefault(name, 1)).name();
        }
        name = name.toUpperCase(Locale.ROOT);
        name = name.replace(":0", "");
        if (splitName.length > 1) {
            name = name + ":" + splitName[1];
        }
        return Items.items.getOrDefault(name, Items.NULL);
    }

    public static ItemMaterial get(ItemStack item) {
        return Items.items.getOrDefault(Functions.getId(item), Items.NULL);
    }

    public static ArmorMaterial getArmor(ItemStack item) {
        ItemMaterial material = Items.items.getOrDefault(Functions.getId(item), Items.NULL);
        return material instanceof ArmorMaterial ? (ArmorMaterial) material : ArmorMaterial.NULL;
    }

    public static PetMaterial getPet(ItemStack item) {
        ItemMaterial material = Items.items.getOrDefault(Functions.getId(item), Items.NULL);
        return material instanceof PetMaterial ? (PetMaterial) material : PetMaterial.NULL;
    }
}
