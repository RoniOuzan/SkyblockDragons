package me.maxiiiiii.skyblockdragons.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.abilities.Wither_Impact;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.events.EntityHealth;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.enchants.UltimateEnchantType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.item.objects.Stat;
import me.maxiiiiii.skyblockdragons.material.*;
import me.maxiiiiii.skyblockdragons.player.pet.PetMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.interfaces.LoopTask;
import me.maxiiiiii.skyblockdragons.util.interfaces.Task;
import me.maxiiiiii.skyblockdragons.util.interfaces.While;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import me.maxiiiiii.skyblockdragons.util.objects.ParticlePacketUtil;
import me.maxiiiiii.skyblockdragons.util.objects.SignMenu;
import me.maxiiiiii.skyblockdragons.util.objects.SlotCooldown;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.material.Items.items;
import static me.maxiiiiii.skyblockdragons.material.Items.vanillaMaterials;
import static me.maxiiiiii.skyblockdragons.commands.menu.ItemList.openItemList;
import static me.maxiiiiii.skyblockdragons.storage.Variables.getVariable;
import static me.maxiiiiii.skyblockdragons.storage.Variables.getVariableValue;

public class Functions {
    public static ItemStack getSkull(ItemStack item, String id, String value) {
        if (value.isEmpty()) return item;

        NBTItem nbt = new NBTItem(item);
        NBTCompound skull = nbt.addCompound("SkullOwner");
        skull.setString("Id", id);
        NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        texture.setString("Value", value);
        nbt.applyNBT(item);

        nbt.applyNBT(item);

        return item;
    }

    public static String getInt(String num) {
        String[] texts = num.split("\\.");
        try {
            if (texts[1].equals("0")) {
                return texts[0];
            }
        } catch (IndexOutOfBoundsException ignored) {}
        return num;
    }

    public static void sendActionBar(PlayerSD player, String message) {
        int healthAdder = 0;
        if (Wither_Impact.witherShieldHealth.containsKey(player.getUniqueId()) && System.currentTimeMillis() - Wither_Impact.witherShield <= 5000) {
            healthAdder += Wither_Impact.witherShieldHealth.get(player.getUniqueId());
        }
        message = "" + ChatColor.RED + (int) (player.getHealth() + healthAdder) + "/" + (int) player.getMaxHealth() + Stat.HEALTH.getIcon() + " " + ChatColor.GOLD + message + " " + ChatColor.AQUA + (int) SkyblockDragons.players.get(player.getUniqueId()).getMana() + "/" + (int) SkyblockDragons.players.get(player.getUniqueId()).getIntelligence() + Stat.INTELLIGENCE.getIcon();
        player.sendActionBar(message);
    }

    public static void sendActionBar(PlayerSD player) {
        sendActionBar(player, "" + ChatColor.GREEN + (int) SkyblockDragons.players.get(player.getUniqueId()).getDefense() + Stat.DEFENSE.getIcon());
    }

    public static void setArmorColor(ItemStack item, Color color) {
        if (!(item.getType() == Material.LEATHER_HELMET || item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.LEATHER_LEGGINGS || item.getType() == Material.LEATHER_BOOTS)) {
            return;
        }
        LeatherArmorMeta leatherArmorMeta = item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
        leatherArmorMeta.setColor(color);
        item.setItemMeta(leatherArmorMeta);
    }

    public static String getShortNumber(double num) {
        String output = getInt(num + "");
        if (num >= 1000000000000000000d) {
            output = num + "";
        } else if (num >= 1000000000000000d) {
            output = Math.round(num / 1000000000000000d * 100d) / 100d + "Q";
        } else if (num >= 1000000000000d) {
            output = Math.round(num / 1000000000000d * 100d) / 100d + "T";
        } else if (num >= 1000000000d) {
            output = Math.round(num / 1000000000d * 100d) / 100d + "B";
        } else if (num >= 1000000d) {
            output = Math.round(num / 1000000d * 100d) / 100d + "M";
        } else if (num >= 1000d) {
            output = Math.round(num / 1000d * 100d) / 100d + "K";
        }
        return output;
    }

    public static String getNumberFormat(Object num) {
        if (isInt(num + "") || isDouble(num + "") || isByte(num + "") || isFloat(num + "") || isLong(num + "") || isShort(num + "")) {
            String output = getInt(num + "");
            output = output.replaceAll("(?<=\\d)(?=(\\d\\d\\d)+(?!\\d))", ",");
            return output;
        }
        return num + "";
    }

    public static void setName(ItemStack item, String name) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
    }

    public static void setLore(ItemStack item, ArrayList<String> lores) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lores);
        item.setItemMeta(itemMeta);
    }

    public static void setLore(ItemStack item, String... lore) {
        ArrayList<String> lores = (ArrayList<String>) Arrays.asList(lore);
        setLore(item, lores);
    }

    public static void setSkull(String nbt, String name, String id) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        NBTItem nbti = new NBTItem(head);
        NBTCompound skull = nbti.addCompound("SkullOwner");
        skull.setString("Name", name);
        skull.setString("Id", id);
        NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        texture.setString("Signature", nbt);
        texture.setString("Value", nbt);
        nbti.applyNBT(head);
    }

    public static ArrayList<Entity> loopEntities(Location center, double size) {
        List<Entity> entities = center.getWorld().getEntities();
        ArrayList<Entity> entity = new ArrayList<>();
        for (Entity value : entities) {
            if (center.distance(value.getLocation()) <= size) {
                entity.add(value);
            }
        }
        return entity;
    }

    public static ArrayList<Entity> loopEntitiesScope(Location center, int size, int size2, int size3) {
        List<Entity> entities = center.getWorld().getEntities();
        ArrayList<Entity> entity = new ArrayList<>();
        for (Entity value : entities) {
            if (center.distance(value.getLocation()) <= size) {
                if (Math.floor(center.distance(value.getLocation())) == Math.floor(size) || Math.floor(center.distance(value.getLocation())) == Math.floor(size2) || Math.floor(center.distance(value.getLocation())) == Math.floor(size3)) {
                    entity.add(value);
                }
            }
        }
        return entity;
    }

    public static ArrayList<Block> loopBlocksScopeHor(Location center, int size) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        int X = center.getBlockX();
        int Y = center.getBlockY();
        int Z = center.getBlockZ();
        for (int x = X - size; x <= X + size; x++) {
            for (int y = Y - size; y <= Y + size; y++) {
                for (int z = Z - size; z <= Z + size; z++) {
                    if (Math.floor(center.distance(center.getWorld().getBlockAt(x, y, z).getLocation())) == Math.floor(size)) {
                        if (Math.floor(center.getBlockY()) == Math.floor(y)) {
                            blocks.add(center.getWorld().getBlockAt(x, y, z));
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public static ArrayList<Block> loopBlocksScopeHor(Location center, int size, int size2) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        int X = center.getBlockX();
        int Y = center.getBlockY();
        int Z = center.getBlockZ();
        for (int x = X - size; x <= X + size; x++) {
            for (int y = Y - size; y <= Y + size; y++) {
                for (int z = Z - size; z <= Z + size; z++) {
                    if (Math.floor(center.distance(center.getWorld().getBlockAt(x, y, z).getLocation())) == Math.floor(size) || Math.floor(center.distance(center.getWorld().getBlockAt(x, y, z).getLocation())) == Math.floor(size2)) {
                        if (Math.floor(center.getBlockY()) == Math.floor(y)) {
                            blocks.add(center.getWorld().getBlockAt(x, y, z));
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public static ArrayList<Block> loopBlocksScopeHor(Location center, int size, int size2, int size3) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        int X = center.getBlockX();
        int Y = center.getBlockY();
        int Z = center.getBlockZ();
        for (int x = X - size; x <= X + size; x++) {
            for (int y = Y - size; y <= Y + size; y++) {
                for (int z = Z - size; z <= Z + size; z++) {
                    if (Math.floor(center.distance(center.getWorld().getBlockAt(x, y, z).getLocation())) == Math.floor(size) || Math.floor(center.distance(center.getWorld().getBlockAt(x, y, z).getLocation())) == Math.floor(size2) || Math.floor(center.distance(center.getWorld().getBlockAt(x, y, z).getLocation())) == Math.floor(size3)) {
                        if (Math.floor(center.getBlockY()) == Math.floor(y)) {
                            blocks.add(center.getWorld().getBlockAt(x, y, z));
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public static ArrayList<Block> loopBlocks(Location center, int size) {
        ArrayList<Block> blocks = new ArrayList<>();
        int X = center.getBlockX();
        int Y = center.getBlockY();
        int Z = center.getBlockZ();
        double doubleSize = size;
        for (int x = X - size; x <= X + size; x++) {
            for (int y = Y - size; y <= Y + size; y++) {
                for (int z = Z - size; z <= Z + size; z++) {
                    if (center.distance(center.getWorld().getBlockAt(x, y, z).getLocation()) <= doubleSize) {
                        blocks.add(center.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
        }
        return blocks;
    }

    public static ArrayList<Block> loopBlocksHorizontally(Location center, int size) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        int X = center.getBlockX();
        int Y = center.getBlockY();
        int Z = center.getBlockZ();
        double doubleSize = size;
        for (int x = X - size; x <= X + size; x++) {
            for (int y = Y - size; y <= Y + size; y++) {
                for (int z = Z - size; z <= Z + size; z++) {
                    if (center.distance(center.getWorld().getBlockAt(x, y, z).getLocation()) <= doubleSize) {
                        if (Math.floor(center.getY()) == Math.floor(y)) {
                            blocks.add(center.getWorld().getBlockAt(x, y, z));
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public static Location getCenter(Location loc) {
        loc.setX(Math.floor(loc.getBlockX()) + 0.5);
        loc.setY(Math.floor(loc.getBlockY()) + 0.5);
        loc.setZ(Math.floor(loc.getBlockZ()) + 0.5);
        return loc;
    }

    public static ArrayList<Block> loopBlocksCube(Location center, int size) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        int X = center.getBlockX();
        int Y = center.getBlockY();
        int Z = center.getBlockZ();
        for (int x = X - size; x < X + size; x++) {
            for (int y = Y - size; y < Y + size; y++) {
                for (int z = Z - size; z < Z + size; z++) {
                    blocks.add(center.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

    public static String setTitleCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

    public static ArrayList<String> loreBuilder(String lore, ChatColor color, int num) {
        String[] lores = lore.split(" ");
        int l = 0;
        int n = 0;
        ArrayList<String> list = new ArrayList<>();
        for (String str : lores) {
            if (str.equals("RESET_LENGTH")) {
                l = 0;
                continue;
            } else if (str.equals("NEW_LINE")) {
                list.add(color + "");
                l = 0;
                n++;
            } else {
                l += str.length() + 1;
                try {
                    list.set(n, list.get(n) + str + " ");
                } catch (IndexOutOfBoundsException ex) {
                    list.add(color + "");
                    list.set(n, list.get(n) + str + " ");
                }
                if (l > num) {
                    l = 0;
                    n++;
                }
            }
        }
        return list;
    }

    public static ArrayList<String> loreBuilder(String lore) {
        return loreBuilder(lore, ChatColor.GRAY, 30);
    }

    // "torch" or "tall" or "fence" or "SKULL" or "sign" or "button" or "bar" or "vine" or "ladder" or "bush" or "pane" or "carpet" or "water" or "lava" or "air" or "web" or "plate" or "string" or "door"
    public static boolean isSolid(Block b) {
        Material t = b.getType();
        if (t == Material.AIR || t == Material.TORCH || t == Material.LONG_GRASS || t == Material.FENCE || t == Material.SKULL || t == Material.SIGN || t == Material.SIGN_POST || t == Material.WALL_SIGN || t == Material.STONE_BUTTON || t == Material.WOOD_BUTTON || t == Material.IRON_FENCE || t == Material.VINE || t == Material.LADDER || t == Material.DEAD_BUSH || t == Material.THIN_GLASS || t == Material.STAINED_GLASS_PANE || t == Material.CARPET || t == Material.WATER || t == Material.WATER_LILY || t == Material.STATIONARY_WATER || t == Material.LAVA || t == Material.STATIONARY_LAVA || t == Material.WEB || t == Material.GOLD_PLATE || t == Material.IRON_PLATE || t == Material.STONE_PLATE || t == Material.WOOD_PLATE || t == Material.TRIPWIRE || t == Material.STRING || t == Material.TRIPWIRE_HOOK || t == Material.DARK_OAK_DOOR || t == Material.ACACIA_DOOR || t == Material.BIRCH_DOOR || t == Material.IRON_DOOR || t == Material.JUNGLE_DOOR || t == Material.SPRUCE_DOOR || t == Material.TRAP_DOOR || t == Material.WOOD_DOOR || t == Material.WOODEN_DOOR || t == Material.IRON_TRAPDOOR || t == Material.SNOW) {
            return false;
        } else if (t.isSolid()) {
            return true;
        }
        return true;
    }

    public static boolean isInt(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isLong(String num) {
        try {
            Long.parseLong(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isShort(String num) {
        try {
            Short.parseShort(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isByte(String num) {
        try {
            Byte.parseByte(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFloat(String num) {
        try {
            Float.parseFloat(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void hideEntity(Entity entity, Player player) {
        try {
            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
            PacketContainer packet = manager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
            packet.getIntegers().write(0, 1);
            int[] id = {entity.getEntityId()};
            packet.getIntegerArrays().write(0, id);
            manager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void sendPacketItem(int slot, Player player, ItemStack item) {
        try {
            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
            PacketContainer packet = manager.createPacket(PacketType.Play.Server.SET_SLOT);
            packet.getIntegers().write(0, 0);
            packet.getIntegers().write(1, slot + 36);
            packet.getItemModifier().write(0, item);
            manager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void sendPacketItem(int slot, Player player, ItemStack itemStack, Material material) {
        ItemStack item = itemStack.clone();
        item.setType(material);

        sendPacketItem(slot, player, item);
    }

    public static void sendPacketItem(int slot, Player player, ItemStack item, int ticks) {
        ItemStack itemStack = player.getInventory().getItem(slot);
        new BukkitRunnable() {
            private int loop = 0;

            @Override
            public void run() {
                if (loop >= ticks) {
                    sendPacketItem(slot, player, itemStack);
                    cancel();
                }
                sendPacketItem(slot, player, item);
                loop++;
            }
        }.runTaskTimer(SkyblockDragons.getInstance(), slot, 1L);
    }

    public static void sendPacketItem(int slot, Player player, ItemStack itemStack, Material material, int ticks) {
        ItemStack item = itemStack.clone();
        item.setType(material);

        new BukkitRunnable() {
            private int loop = 0;

            @Override
            public void run() {
                if (loop >= ticks) {
                    sendPacketItem(slot, player, itemStack);
                    cancel();
                }
                sendPacketItem(slot, player, item);
                loop++;
            }
        }.runTaskTimer(SkyblockDragons.getInstance(), slot, 1L);
    }

    public static void setItem(ItemStack item1, Material material, Long wait) {
        final Material material1 = item1.clone().getType();
        item1.setType(material);
        new BukkitRunnable() {
            @EventHandler
            public void run() {
                item1.setType(material1);
            }
        }.runTaskLater(SkyblockDragons.getInstance(), wait);
    }

    public static int randomInt(int min, int max) {
        max++;
        min *= 1000;
        max *= 1000;
        return (int) (Math.random() * (max - min) + min) / 1000;
    }

    public static double randomDouble(double min, double max) {
        min *= 1000;
        max *= 1000;
        return (Math.random() * (max - min) + min) / 1000;
    }

    public static Block getBlockBelow(Location location) {
        for (int i = 0; i < 200; i++) {
            Block block = location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - i - 1, location.getBlockZ());
            if (block.getLocation().clone().add(0, 1, 0).getBlock().getType() == Material.AIR && block.getType().isSolid()) {
                return block;
            }
        }
        return location.getWorld().getBlockAt(location);
    }

    public static Hologram createHologram(Location location, String text) {
        Hologram hologram = HologramsAPI.createHologram(SkyblockDragons.getInstance(), location);
        TextLine textLine = hologram.appendTextLine(text);
        return hologram;
    }

    public static Hologram createHologram(Location location, String text, int ticks) {
        Hologram hologram = HologramsAPI.createHologram(SkyblockDragons.getInstance(), location.clone());
        hologram.appendTextLine(text);
        new BukkitRunnable() {
            @Override
            public void run() {
                hologram.delete();
            }
        }.runTaskLater(SkyblockDragons.getInstance(), ticks);
        return hologram;
    }

    public static Hologram createHologram(Location location, ArrayList<String> text, int ticks) {
        Hologram hologram = HologramsAPI.createHologram(SkyblockDragons.getInstance(), location);
        for (String text1 : text) {
            hologram.appendTextLine(text1);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                hologram.delete();
            }
        }.runTaskLater(SkyblockDragons.getInstance(), ticks);
        return hologram;
    }

    public static String rainbow(String text){
        StringBuilder b = new StringBuilder();
        ChatColor[] ch = {ChatColor.WHITE, ChatColor.YELLOW, ChatColor.GOLD, ChatColor.RED, ChatColor.RED, ChatColor.WHITE};
        int i = 0;
        for (char c : text.toCharArray()) {
            b.append(ch[i]).append(c);
            i++;
            i = (i+ch.length) % ch.length; //modulus is amazing use it more
        }      //             ^------------------------------<
        return b.toString();
    }

    public static boolean isColorable(Material material) {
        return material == Material.WOOL || material == Material.INK_SACK || material == Material.STAINED_GLASS || material == Material.STAINED_GLASS_PANE || material == Material.CARPET || material == Material.BANNER || material == Material.STAINED_CLAY;
    }

    public static String getEntityName(Entity entity) {
        if (entity instanceof Player) {
            return "Player";
        }
        try {
            String name = entity.getCustomName().replaceAll("'", "");
            List<String> names = Arrays.stream(name.split(EntityHealth.SPLITTER)[0].split(" ")).collect(Collectors.toList());
            names.remove(0);
            names.remove(0);
            return String.join("_", names);
        } catch (IndexOutOfBoundsException ignored) {}
        return "Player";
    }

    public static EntityMaterial getEntityMaterial(LivingEntity entity) {
        String s = getEntityName(entity);

        return EntityMaterial.get(ChatColor.stripColor(s.toUpperCase()));
    }

    public static ItemMaterial getItemMaterial(ItemStack item) {
        if (!isNotAir(item)) return Items.NULL;

        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            String id = nbt.getString("id");
            Object[] value = items.values().stream().filter(material -> material.name().equals(id)).toArray();
            if (value.length > 0 && value[0] instanceof ItemMaterial)
                return (ItemMaterial) value[0];
        } catch (NullPointerException ignored) {}
        for (ItemMaterial material : vanillaMaterials.values()) {
            if (material.getMaterial() == item.getType()) {
                if (isColorable(material.getMaterial())) {
                    if (material.getId().equals(item.getDurability() + ""))
                        return material;
                } else
                    return material;
            }
        }
        return Items.NULL;
    }

    public static PetMaterial getPetMaterial(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            String id = nbt.getString("id");
            for (PetMaterial material : PetMaterial.Pets.values()) {
                if (material.name().equals(id)) {
                    return material;
                }
            }
        } catch (NullPointerException ignored) {}
        return PetMaterial.NULL;
    }

    public static void recursiveBreakBlocks(Location location, int amount, Material material) {
        if (amount <= 0) return;
        int breakedAmount = 0;
        for (Block block : loopBlocks(location, 1)) {
            if (block.getType() == material) {
                breakedAmount++;
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(material));
                recursiveBreakBlocks(block.getLocation(), amount - breakedAmount, material);
            }
        }
    }

    public static void recursiveBreakBlocks(Location location, int amount, Material material, int ticks) {
        if (amount <= 0) return;
        ArrayList<Block> blocks = loopBlocks(location, 1);
        int breakBlock = 0;
        for (Block block : blocks) {
            if (block.getType() == material) {
                breakBlock++;
            }
        }
        int amount1 = amount - breakBlock;
        for (Block block : blocks) {
            if (block.getType() == material) {
                block.getWorld().dropItem(block.getLocation(), new ItemStack(block.getType(), 1, block.getData()));
                block.setType(Material.AIR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        recursiveBreakBlocks(block.getLocation(), amount1, material, ticks);
                    }
                }.runTaskLater(SkyblockDragons.getInstance(), ticks);
            }
        }
    }

    public static boolean isMiddleSlot(int num) {
        int num1 = 10;
        int num2 = 16;
        for (int i = 0; i < 4; i++) {
            if (num >= num1 && num <= num2) {
                return true;
            }
            num1 += 9;
            num2 += 9;
        }
        return false;
    }

    public static int intToSlot(int num) {
        int num1 = 0;
        int num2 = 7;
        int adder = 10;

        while (true) {
            if (num >= num1 && num < num2) {
                return num + adder;
            }
            num1 += 7;
            num2 += 7;
            adder += 2;
        }
    }

    public static int slotToInt(int slot, int page) {
        if (slot % 9 == 0 || slot % 9 == 8)
            return -1;

        return slot - 10 - (((slot / 9) - 1) * 2) + (28 * (page - 1));
    }

    public static void openSign(Player player, ArrayList<String> strings) {
        new SignMenu(SkyblockDragons.getInstance()).
                open(player.getUniqueId(), new String[]{"&a&lThis", "&e&lis", "&d&lan", "&b&lexample!"},
                        (player1, text) ->
                                Arrays.stream(text).forEach(new Consumer<String>() {
                                    @Override
                                    public void accept(String t) {
                                        if (!t.equals("")) {
                                            strings.set(0, t);
                                            System.out.println(strings.get(0));
                                        }
                                    }
                                }));
    }

    public interface SignRunnable {
        void run(ArrayList<String> lines);
    }

    public static void openSign(Player player, SignRunnable runnable) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("");
        openSign(player, lines);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!lines.get(0).equals("")) {
                    runnable.run(lines);
                    cancel();
                }
            }
        }.runTaskTimer(SkyblockDragons.getInstance(), 0L, 10L);
    }

//    public static EnchantType getEnchant(String name) {
//        for (EnchantType enchantType : EnchantType.values()) {
//            if (enchantType.name().equals(name.toUpperCase())) {
//                return enchantType;
//            }
//        }
//        return EnchantType.NULL;
//    }
//
//    public static ArrayList<Enchant> getEnchants(ItemStack item) {
//        try {
//            ArrayList<Enchant> enchants = new ArrayList<>();
//            NBTItem nbt = new NBTItem(item);
//            NBTCompound compound = nbt.getCompound("Enchants");
//        } catch (NullPointerException ignored) {}
//    }

    public static boolean isItemMaterial(String name) {
        for (ItemMaterial itemMaterial : items.values()) {
            if (itemMaterial.name().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEnchant(String name) {
        for (EnchantType enchantType : EnchantType.Enchants.values()) {
            if (enchantType.name().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPlayerName(String name) {
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static ReforgeType getReforge(String name) {
        for (ReforgeType reforge : ReforgeType.values()) {
            if (reforge.name().equals(name.toUpperCase())) {
                return reforge;
            }
        }
        return ReforgeType.NULL;
    }

    public static ItemMaterial getSkin(String name) {
        for (ItemMaterial material : items.values()) {
            if (material.name().equals(name.toUpperCase())) {
                return material;
            }
        }
        return items.get("NULL");
    }

    public static ItemMaterial setSkin(ItemStack item) {
        ItemMaterial skin = Items.NULL;
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound nbt = nbtItem.getCompound("Item");
        if (!nbt.getString("Skin").equals("")) {
            skin = getSkin(nbt.getString("Skin"));
        }
        return skin;
    }

    public static Rarity getRarity(int level) {
        for (Rarity rarity : Rarity.values()) {
            if (rarity.getLevel() == level) {
                return rarity;
            }
        }
        return Rarity.SPECIAL;
    }

    public static Rarity getRarity(ChatColor color) {
        for (Rarity rarity : Rarity.values()) {
            if (rarity.getColor().equals(color)) {
                return rarity;
            }
        }
        return Rarity.SPECIAL;
    }

    public static void particleLine(Location loc1, Location loc2, Particle particle) {
        particleLine(loc1, loc2, particle, 0, 0, 0);
    }

    public static void particleLine(Location loc1, Location loc2, Particle particle, double red, double green, double blue) {
        final double DISTANCE = loc1.distance(loc2) * 5;
        double t = 0;
        for (int i = 0; i < DISTANCE; i++) {
            t = t + 0.05;
            Vector direction = new Vector(loc2.getX() - loc1.getX(), loc2.getY() - loc1.getY(), loc2.getZ() - loc1.getZ());
            double x = direction.getX() * t;
            double y = direction.getY() * t + 1.5;
            double z = direction.getZ() * t;
            loc1.add(x, y, z);
            loc1.getWorld().spawnParticle(particle, loc1, 0, red, green, blue);

            loc1.subtract(x, y, z);
        }
    }

    public static String getLocation(Location location) {
        double x = Math.floor(location.getX() * 100) / 100;
        double y = Math.floor(location.getY() * 100) / 100;
        double z = Math.floor(location.getZ() * 100) / 100;
        return x + "," + y + "," + z;
    }

    public static boolean chanceOf(double percent) {
        double chance = randomDouble(0, 100);
        return chance <= percent;
    }

    public static String getItemName(ItemStack item) {
        try {
            ItemMeta meta = item.getItemMeta();
            return meta.getDisplayName();
        } catch (NullPointerException e) {
            return item.getType().toString();
        }
    }

    public static ChatColor getEnchantColor(int level) {
        if (level == 5) {
            return ChatColor.GREEN;
        } else if (level == 6) {
            return ChatColor.BLUE;
        } else if (level == 7) {
            return  ChatColor.DARK_PURPLE;
        } else if (level == 8) {
            return ChatColor.GOLD;
        } else if (level == 9) {
            return ChatColor.LIGHT_PURPLE;
        } else if (level == 10) {
            return ChatColor.AQUA;
        }
        return ChatColor.WHITE;
    }

    public static Rarity getBookRarity(int level) {
        if (level == 5) {
            return Rarity.UNCOMMON;
        } else if (level == 6) {
            return Rarity.RARE;
        } else if (level == 7) {
            return Rarity.EPIC;
        } else if (level == 8) {
            return Rarity.LEGENDARY;
        } else if (level == 9 || level == 10) {
            return Rarity.MYTHIC;
        }
        return Rarity.COMMON;
    }

    public static Rarity getBookRarity(Map<EnchantType, Short> enchants) {
        short highest = 0;
        for (EnchantType enchant : EnchantType.Enchants.values()) {
            if (enchants.containsKey(enchant)) {
                if (enchant instanceof UltimateEnchantType) {
                    highest = 10;
                } else if (enchants.get(enchant) > highest) {
                    highest = enchants.get(enchant);
                }
            }
        }
        return getBookRarity(highest);
    }

    public static String getId(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            return nbt.getString("id");
        } catch (NullPointerException ignored) {}
        return "";
    }

    public static Rarity getRarity(ItemStack item) {
        ItemMaterial itemMaterial = getItemMaterial(item);
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound nbt = nbtItem.getCompound("Item");
        if (nbt.getBoolean("RarityUpgraded")) {
            return getRarity(itemMaterial.getRarity().getLevel() + 1);
        }
        return getRarity(itemMaterial.getRarity().getLevel());
    }

    public static Map<EnchantType, Short> getEnchants(ItemStack item) {
        Map<EnchantType, Short> enchants = new HashMap<>();
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            NBTCompound compound1 = nbt.getCompound("Enchants");
            NBTCompound compound2 = nbt.getCompound("UltimateEnchant");
            for (EnchantType enchant : EnchantType.Enchants.values()) {
                if (compound1.hasKey(enchant.name())) {
                    enchants.put(enchant, Short.parseShort(compound1.getInteger(enchant.name()) + ""));
                }
            }
            return enchants;
        } catch (NullPointerException ignored) {}
        return enchants;
    }

    public static ArrayList<EnchantType> getEnchantsList(ItemStack item) {
        ArrayList<EnchantType> enchants = new ArrayList<>();
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            NBTCompound compound = nbt.getCompound("Enchants");
            EnchantType[] enchantTypes = EnchantType.Enchants.values().toArray(new EnchantType[0]);
            Arrays.sort(enchantTypes);
            for (EnchantType enchant : enchantTypes) {
                if (compound.hasKey(enchant.name())) {
                    enchants.add(enchant);
                }
            }
        } catch (NullPointerException ignored) {}
        return enchants;
    }

    public static EnchantType getEnchant(String name) {
        for (EnchantType enchantType : EnchantType.Enchants.values()) {
            if (enchantType.name().equals(name.toUpperCase())) {
                return enchantType;
            }
        }
        return EnchantType.NULL;
    }

    public static SkinMaterial getSkin(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            SkinMaterial skin = SkinMaterial.NULL;
            if (!nbt.getString("Skin").equals("")) {
                skin = (SkinMaterial) Items.get(nbt.getString("Skin"));
            }
            return skin;
        } catch (NullPointerException ignored) {}
        return SkinMaterial.NULL;
    }

    public static ItemStack createItem(Material material, int amount, int data, String name, ArrayList<String> lores) {
        ItemStack item = new ItemStack(material, amount, (short) data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, int data, String name, String... lore) {
        return createItem(material, amount, data, name, new ArrayList<>(Arrays.asList(lore)));
    }

    public static ItemStack createItem(Material material, int amount, String name, String... lore) {
        return createItem(material, amount, (short) 0, name, lore);
    }

    public static ItemStack createItem(Material material, int amount, String name, ArrayList<String> lore) {
        return createItem(material, amount, (short) 0, name, lore);
    }

    public static ItemStack createItem(Material material, String name) {
        return createItem(material, 1, (short) 0, name, new ArrayList<>());
    }

    public static ItemStack createItem(Material material, int data, String name) {
        return createItem(material, 1, (short) data, name, new ArrayList<>());
    }

    public static ItemStack createItem(Material material, String name, String... lore) {
        return createItem(material, 1, (short) 0, name, lore);
    }

    public static ItemStack createItem(Material material, String name, ArrayList<String> lore) {
        return createItem(material, 1, (short) 0, name, lore);
    }

    public static ArrayList<Block> getBlocksCircle(Block vertex, double radius) {
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = vertex.getX(); i < (int) (Math.floor(radius) + vertex.getX()) * 2; i++) {
            double x = Math.sqrt(-Math.pow(i, 2) + Math.pow(radius, 2));
            Block block = vertex.getWorld().getBlockAt((int) (vertex.getZ() + x), vertex.getY(), i);
            blocks.add(block);
        }
        return blocks;
    }

    public static Entity getTargetEntity(Player player, double max, boolean stopAtBlock) {
        Location location = player.getLocation();

        for (int i = 0; i < max; i++) {
            Location loc = location.clone().add(location.clone().getDirection().multiply(i));


            for (Entity entity : loopEntities(loc, 1.5)) {
                if (entity instanceof Creature) {
                    return entity;
                }
            }
            if (loc.getBlock().getType() != Material.AIR && stopAtBlock) break;
        }
        return null;
    }

    public static Entity getTargetEntity(Player player, double max) {
        return getTargetEntity(player, max, true);
    }

    public static boolean cooldown(Player player, Cooldown cooldown, long milliseconds, boolean message) {
        long timeLeft = System.currentTimeMillis() - cooldown.getCooldown(player);
        if (timeLeft < milliseconds) {
            if (message) player.sendMessage(ChatColor.RED + "This ability in on cooldown for " + Math.floor((double) (milliseconds - timeLeft) / 1000.0) + "s.");
            return true;
        }
        cooldown.setCooldown(player,System.currentTimeMillis());
        return false;
    }

    public static boolean slotCooldown(Player player, int slot, SlotCooldown cooldown, long milliseconds, boolean message) {
        long timeLeft = System.currentTimeMillis() - cooldown.getCooldown(player + ":" + slot, slot);
        if (timeLeft < milliseconds) {
            if (message) player.sendMessage(ChatColor.RED + "This ability in on cooldown for " + Math.floor((double) (milliseconds - timeLeft) / 1000.0) + "s.");
            return true;
        }
        cooldown.setCooldown(player + ":" + slot, System.currentTimeMillis());
        return false;
    }

    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<>();
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public static Object getMapKey(HashMap hashMap, Object value) {
        for (Object key : hashMap.keySet()) {
            if (hashMap.get(key) == value) {
                return key;
            }
        }
        return null;
    }

    private static final String[] nicod = {"a", "e", "o", "u", "i"};

    public static String getPlural(String text) {
        String[] texts = text.split("");
        if (texts[texts.length - 1].equalsIgnoreCase("s") || (texts[texts.length - 2].equalsIgnoreCase("s") && texts[texts.length - 1].equalsIgnoreCase("h")) || (texts[texts.length - 2].equalsIgnoreCase("c") && texts[texts.length - 1].equalsIgnoreCase("h")) || texts[texts.length - 1].equalsIgnoreCase("x") || texts[texts.length - 1].equalsIgnoreCase("z")) {
            text += "es";
        } else if ((texts[texts.length - 2].equalsIgnoreCase("a") || texts[texts.length - 2].equalsIgnoreCase("e") || texts[texts.length - 2].equalsIgnoreCase("o") || texts[texts.length - 2].equalsIgnoreCase("u") || texts[texts.length - 2].equalsIgnoreCase("i")) && !(texts[texts.length - 1].equalsIgnoreCase("a") || texts[texts.length - 1].equalsIgnoreCase("e") || texts[texts.length - 1].equalsIgnoreCase("o") || texts[texts.length - 1].equalsIgnoreCase("u") || texts[texts.length - 1].equalsIgnoreCase("i")) && !(texts[texts.length - 3].equalsIgnoreCase("a") || texts[texts.length - 3].equalsIgnoreCase("e") || texts[texts.length - 3].equalsIgnoreCase("o") || texts[texts.length - 3].equalsIgnoreCase("u") || texts[texts.length - 3].equalsIgnoreCase("i"))) {
            text += texts[texts.length - 1] + "es";
        } else if ((texts[texts.length - 1].equalsIgnoreCase("f") || (texts[texts.length - 2].equalsIgnoreCase("f") && texts[texts.length - 1].equalsIgnoreCase("e"))) && !(text.equalsIgnoreCase("roof") || text.equalsIgnoreCase("belief") || text.equalsIgnoreCase("chef") || text.equalsIgnoreCase("chief"))) {
            text = "";
            int times = 0;
            int length = texts.length;
            if (texts[texts.length - 1].equalsIgnoreCase("e")) length -= 1;
            for (String word : texts) {
                times++;
                if (times >= length) {
                    text += "ves";
                } else {
                    text += word;
                }
            }
        } else if (texts[texts.length - 1].equalsIgnoreCase("y") && !(texts[texts.length - 2].equalsIgnoreCase("a") || texts[texts.length - 2].equalsIgnoreCase("e") || texts[texts.length - 2].equalsIgnoreCase("o") || texts[texts.length - 2].equalsIgnoreCase("u") || texts[texts.length - 2].equalsIgnoreCase("i"))) {
            int times = 0;
            for (String word : texts) {
                text = "";
                times++;
                if (times >= texts.length) {
                    text += "ies";
                } else {
                    text += word;
                }
            }
        } else if ((texts[texts.length - 1].equalsIgnoreCase("a") || texts[texts.length - 1].equalsIgnoreCase("e") || texts[texts.length - 1].equalsIgnoreCase("o") || texts[texts.length - 1].equalsIgnoreCase("u") || texts[texts.length - 1].equalsIgnoreCase("i")) && !(text.equalsIgnoreCase("photo") || text.equalsIgnoreCase("piano") || text.equalsIgnoreCase("halo") || text.equalsIgnoreCase("volcano"))) {
            text += "es";
        } else if (texts[texts.length - 2].equalsIgnoreCase("u") && texts[texts.length - 1].equalsIgnoreCase("s")) {
            int times = 0;
            for (String word : texts) {
                text = "";
                times++;
                if (times > texts.length - 1) {
                } else if (times == texts.length - 1) {
                    text += "i";
                } else {
                    text += word;
                }
            }
        } else if (texts[texts.length - 2].equalsIgnoreCase("i") && texts[texts.length - 1].equalsIgnoreCase("s")) {
            int times = 0;
            for (String word : texts) {
                text = "";
                times++;
                if (times == texts.length) {
                    text += "s";
                } else if (times == texts.length - 1) {
                    text += "e";
                } else {
                    text += word;
                }
            }
        } else if (texts[texts.length - 2].equalsIgnoreCase("o") && texts[texts.length - 1].equalsIgnoreCase("n")) {
            int times = 0;
            for (String word : texts) {
                text = "";
                times++;
                if (times > texts.length - 1) {
                } else if (times == texts.length - 1) {
                    text += "a";
                } else {
                    text += word;
                }
            }
        } else if (text.equalsIgnoreCase("sheep") || text.equalsIgnoreCase("series") || text.equalsIgnoreCase("species") || text.equalsIgnoreCase("deer") || text.equalsIgnoreCase("fish")) {
        } else if (text.equalsIgnoreCase("child")) text = "children";
        else if (text.equalsIgnoreCase("goose")) text = "geese";
        else if (text.equalsIgnoreCase("man")) text = "men";
        else if (text.equalsIgnoreCase("woman")) text = "women";
        else if (text.equalsIgnoreCase("tooth")) text = "teeth";
        else if (text.equalsIgnoreCase("foot")) text = "feet";
        else if (text.equalsIgnoreCase("mouse")) text = "mice";
        else if (text.equalsIgnoreCase("person")) text = "people";
        else {
            text += "s";
        }
        return text;
    }

    public static boolean isNotAir(ItemStack item) {
        try {
            Material material = item.getType();
            if (material == Material.AIR) return false;
        } catch (NullPointerException ignored) {
            return false;
        }
        return true;
    }

    public static int getHotPotato(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            return nbt.getInteger("HotPotato");
        } catch (NullPointerException ignored) {}
        return 0;
    }

    public static boolean isRecombed(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            return nbt.getBoolean("RarityUpgraded");
        } catch (NullPointerException ignored) {}
        return false;
    }

    public static boolean isStackable(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            return nbt.hasKey("Stack");
        } catch (NullPointerException ignored) {}
        return false;
    }

    public static ReforgeType getReforge(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            return getReforge(nbt.getString("Reforge"));
        } catch (NullPointerException ignored) {}
        return ReforgeType.NULL;
    }

    public static boolean isRecombable(ItemMaterial material) {
        return !(material instanceof SkinMaterial) && !(material instanceof ReforgeMaterial);
    }

    public static ArrayList<NecronBladeMaterial.NecronBladeAbility> getNecronScrolls(ItemStack item) {
        ArrayList<NecronBladeMaterial.NecronBladeAbility> scrolls = new ArrayList<>();
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            NBTCompound necronScrolls = nbt.getCompound("NecronScrolls");
            if (necronScrolls.getBoolean("IMPLOSION")) scrolls.add(NecronBladeMaterial.NecronBladeAbility.IMPLOSION);
            if (necronScrolls.getBoolean("WITHER_SHIELD")) scrolls.add(NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD);
            if (necronScrolls.getBoolean("SHADOW_WARP")) scrolls.add(NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP);
        } catch (NullPointerException ignored) {}
        return scrolls;
    }

    public static boolean isSkin(ItemMaterial material) {
        try {
            SkinMaterial skinMaterial = (SkinMaterial) material;
            return true;
        } catch (ClassCastException ignored) {}
        return false;
    }

    public static int numToSlot(int num) {
        switch (num) {
            case 0: return 10;
            case 1: return 11;
            case 2: return 12;
            case 3: return 19;
            case 4: return 20;
            case 5: return 21;
            case 6: return 28;
            case 7: return 29;
            case 8: return 30;
        }
        return 55;
    }

    public static void teleportForward(Player player, int blocks) {
        int tp = 0;
        player.teleport(player.getLocation().add(0, 1, 0));
        Location l = player.getLocation().clone();
        for (int i = 0; i < blocks; i++) {
            l.add(player.getLocation().getDirection().multiply(1)).getBlock();
            if (!isSolid(l.getBlock()) && !isSolid(l.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ()))) {
                tp++;
            } else {
                player.sendMessage(ChatColor.RED + "There is a block there!");
                break;
            }
        }
        if (tp != 0) {
            l = player.getLocation().clone().add(player.getLocation().getDirection().multiply(tp));
            player.teleport(l);
        }
    }

    public static int manaCostCalculator(int manaCost, ItemStack item) {
        return manaCostCalculator(manaCost, getEnchants(item));
    }

    public static int manaCostCalculator(int manaCost, Map<EnchantType, Short> enchants) {
        int finalCost = manaCost;
        for (EnchantType enchantType : enchants.keySet()) {
            if (enchantType.name().equals("ULTIMATE_WISE")) {
                finalCost -= manaCost * 0.1 * enchants.get(enchantType);
            }
        }
        return finalCost;
    }

    public static String integerToRoman(int num) {

        if (num == 0) return "0";

        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length ; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

    public static double average(double... nums) {
        double sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static boolean isSkillName(String name) {
        return name.equalsIgnoreCase("farming") || name.equalsIgnoreCase("mining") || name.equalsIgnoreCase("combat") || name.equalsIgnoreCase("foraging") || name.equalsIgnoreCase("fishing") || name.equalsIgnoreCase("enchanting") || name.equalsIgnoreCase("alchemy") || name.equalsIgnoreCase("taming") || name.equalsIgnoreCase("dungeoneering");
    }

    public static int getIntOrDefault(String value, int defaultValue) {
        int output;
        try {
            try {
                output = Integer.parseInt(value);
                return output;
            } catch (NumberFormatException ignored) {}
        } catch (NullPointerException ignored) {}
        return defaultValue;
    }

    public static double getDoubleOrDefault(String value, double defaultValue) {
        double output;
        try {
            try {
                output = Double.parseDouble(value);
                return output;
            } catch (NumberFormatException ignored) {}
        } catch (NullPointerException ignored) {}
        return defaultValue;
    }

    public static <T> T getNotNull(T value, T defaultValue) {
        T output;
        try {
            output = value;
            return output;
        } catch (NullPointerException ignored) {}
        return defaultValue;
    }

    public static int numberToItemSlot(int slot, int peace) {
        return (peace * 9) + (slot % 9);
    }

    public static Vector getVector(Player player, double yawDegrees, double pitchDegrees, double multiplayer) {
        Vector vector = new Vector();

        double rotX = player.getLocation().getYaw() + yawDegrees;
        double rotY = player.getLocation().getPitch() + pitchDegrees;

        vector.setY(-Math.sin(Math.toRadians(rotY)));

        double xz = Math.cos(Math.toRadians(rotY));

        vector.setX(-xz * Math.sin(Math.toRadians(rotX)));
        vector.setZ(xz * Math.cos(Math.toRadians(rotX)));

        return vector.multiply(multiplayer);
    }

    public static void Wait(long delay, Task task) {
        new BukkitRunnable() {
            @Override
            public void run() {
                task.task();
            }
        }.runTaskLater(SkyblockDragons.plugin, delay);
    }

    // Loop(amount, delay, (amount) -> {TASK});
    public static void Loop(int amount, long delay, LoopTask task, LoopTask onCancel) {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i >= amount) {
                    onCancel.task(i);
                    cancel();
                    return;
                }
                task.task(i);
                i++;
            }
        }.runTaskTimer(SkyblockDragons.plugin, 0L, delay);
    }

    public static void Loop(int amount, long delay, LoopTask loop) {
        Loop(amount, delay, loop, (i) -> {});
    }

    // While(() -> CONDITION, delay, (amount) -> {TASK});
    public static void While(While condition, long delay, LoopTask task, LoopTask onCancel) {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (!condition.task()) {
                    onCancel.task(i);
                    cancel();
                    return;
                }

                task.task(i);
                i++;
            }
        }.runTaskTimer(SkyblockDragons.plugin, 0L, delay);
    }

    public static void While(While condition, long delay, LoopTask loop) {
        While(condition, delay, loop, (i) -> {});
    }

    public static void particleCircle(Location location, Particle particle, double radius) {
        for (double i = 0; i < 2 * Math.PI ; i += 0.1) {
            double x = Math.cos(i) * radius;
            double y = Math.sin(i) * radius;

            Location particleLocation = new Location(location.getWorld(), location.getX() + x, location.getY() + y, location.getZ());
            location.getWorld().spawnParticle(particle, particleLocation, 0, 0, 0, 0, 0);
        }
    }

    public static Vector rotateAroundAxisX(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public static Vector rotateAroundAxisX1(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;

        y = y * 0.5;

        return v.setY(y).setZ(z);
    }

    public static Vector rotateAroundAxisY(Vector v, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static void playCircle(Player player, Particle particle, double radius, long delay) {
        Loop(360, delay, (i) -> {
            double angle = i * Math.PI / 180;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);

            Vector v0 = new Vector(x, 0, z);
            rotateAroundAxisX1(v0, 90);
            Vector v01 = new Vector(v0.getX(), v0.getY(), v0.getZ());
            rotateAroundAxisX(v01, -90);

            Vector v = new Vector(v01.getX(), v01.getY(), v01.getZ());
            rotateAroundAxisX(v, player.getLocation().getPitch() - 90);
            Vector v2 = new Vector(v.getX(), v.getY(), v.getZ());
            rotateAroundAxisY(v2, player.getLocation().getYaw());

            Location loc = player.getLocation().add(v2.getX(), v2.getY(), v2.getZ());

            player.getWorld().spawnParticle(particle, loc, 1, 0.1, 0.1, 0.1, 0);
        });
    }

    public static double stringCalculator(String string) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            return Math.round((double) engine.eval(string) * 100d) / 100d;
        } catch (ScriptException ignored) {}
        return 0;
    }

    public static String progressBar(double current, double need, int amount) {
        StringBuilder progressBar = new StringBuilder();
        double percent = current / need * 100d;
        for (int i = 0; i < 20; i++) {
            if (i < percent / (100d / amount)) {
                progressBar.append(ChatColor.GREEN);
            } else {
                progressBar.append(ChatColor.WHITE);
            }
            progressBar.append("-");
        }
        return progressBar.toString();
    }

    public static boolean nbtHasKey(ItemStack item, String key) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.hasKey(key))
                return true;
            NBTCompound nbt = nbtItem.getCompound("Item");
            return nbt.hasKey(key);
        } catch (NullPointerException ignored) {}
        return false;
    }

    public static void copyNBTStack(ItemStack toItem, ItemStack fromItem) {
        if (nbtHasKey(fromItem, "Stack")) {
            NBTItem nbtItemFrom = new NBTItem(fromItem);
            NBTCompound nbtFrom = nbtItemFrom.getCompound("Item");

            NBTItem nbtItem = new NBTItem(toItem, true);
            NBTCompound nbt = nbtItem.getCompound("Item");
            nbt.setInteger("Stack", nbtFrom.getInteger("Stack"));
            nbt.setString("Date", nbtFrom.getString("Date"));
        } else {
            NBTItem nbtItem = new NBTItem(toItem, true);
            NBTCompound nbt = nbtItem.getCompound("Item");
            nbt.removeKey("Stack");
            nbt.removeKey("Date");
        }
    }

    public static void setLine(ItemStack item, int line, String text) {
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.set(line - 1, text);
        meta.setLore(lores);
        item.setItemMeta(meta);
    }

    public static void putGlasses(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, createItem(Material.STAINED_GLASS_PANE, 1, 15, ChatColor.RESET + ""));
        }
    }

    public static void spawnParticle(ArrayList<Player> players, ParticlePacketUtil particle, Location location) {
        PacketContainer packet = particle.getParticle(location);
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        for (Player player : players) {
            if (particle.particle == EnumWrappers.Particle.REDSTONE) {
                for (int i = 0; i < particle.amount; i++) {
                    try {
                        protocolManager.sendServerPacket(player, packet);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    protocolManager.sendServerPacket(player, packet);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<Player> getPlayerShowedPets() {
        ArrayList<Player> players = new ArrayList<>();
        for (PlayerSD player : SkyblockDragons.players.values()) {
            if (!player.hidePets)
                players.add(player);
        }
        return players;
    }

    public static ArrayList<Player> getPlayerShowedPets(Location location, double distance) {
        ArrayList<Player> players = new ArrayList<>();
        for (PlayerSD player : SkyblockDragons.players.values()) {
            if (!player.hidePets && player.getLocation().distance(location) < distance)
                players.add(player);
        }
        return players;
    }

    public static String getColorName(int color) {
        switch (color) {
            case 0:
                return "White";
            case 1:
                return "Orange";
            case 2:
                return "Magenta";
            case 3:
                return "Light BLue";
            case 4:
                return "Yellow";
            case 5:
                return "Lime";
            case 6:
                return "Pink";
            case 7:
                return "Gray";
            case 8:
                return "Light Gray";
            case 9:
                return "Cyan";
            case 10:
                return "Purple";
            case 11:
                return "Blue";
            case 12:
                return "Brown";
            case 13:
                return "Green";
            case 14:
                return "Red";
            case 15:
                return "Black";
        }
        return "";
    }

    public static short getEnchantLevel(ItemStack item, EnchantType enchant) {
        if (!isNotAir(item)) return 0;

        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            NBTCompound enchants = nbt.getCompound("Enchants");
            if (enchants.hasKey(enchant.name())) {
                return enchants.getShort(enchant.name());
            }
        } catch (NullPointerException ignored) {}
        return 0;
    }

    public static boolean isEntityUndead(Entity entity) {
        return entity instanceof Skeleton || entity instanceof Zombie;
    }

    public static boolean isEntityBaneOfArthropods(Entity entity) {
        return entity instanceof Spider || entity instanceof Silverfish;
    }

    public static boolean isEntityBaneOfArthropods() {
        return false;
    }

    @SafeVarargs
    public static List<String> getTabs(String[] args, Collection<String>... collection) {
        List<String> tabs = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (i >= collection.length)
                continue;
            int finalI = i;
            tabs = collection[i].stream().filter(s -> s.startsWith(args[finalI])).collect(Collectors.toList());
        }
        return tabs;
    }
}
