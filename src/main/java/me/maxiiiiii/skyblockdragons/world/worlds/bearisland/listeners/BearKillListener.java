package me.maxiiiiii.skyblockdragons.world.worlds.bearisland.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.modifiers.PetModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.pet.PetSupplier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Killer;
import me.maxiiiiii.skyblockdragons.util.objects.PickableItem;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.BearIsland;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.BearType;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.events.BearKillEvent;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.events.PlayerPlaceFurEvent;
import me.maxiiiiii.skyblockdragons.world.worlds.end.listeners.PlayerPlaceEyeListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class BearKillListener implements Listener {
    public static final Location UNiQUE_DROP_LOCATION = new Location(BearIsland.world, 5, 62, 13);
    public static final Location NORMAL_DROP_LOCATION = new Location(BearIsland.world, 1.5, 62, 19.5);

    @EventHandler
    public void onBearKill(BearKillEvent e) {
        List<Killer> killers = new ArrayList<>();
        for (PlayerSD player : e.getKillers().keySet()) {
            killers.add(new Killer(player, e.getKillers().get(player)));
        }
        Collections.sort(killers);

        BearType bearType = BearType.getBearType(e.getEntity().material.getName());

        if (bearType == null) {
            SkyblockDragons.logger.info(ChatColor.RED + "Cannot find the type of the bear!");
            return;
        }

        Map<PlayerSD, Integer> positions = new HashMap<>();
        Map<PlayerSD, Double> playerQuality = new LinkedHashMap<>();

        // 1 to make the place be easier to see
        for (int i = 1; i < killers.size() + 1; i++) {
            PlayerSD player = killers.get(i - 1).player;

            int eyes = PlayerPlaceEyeListener.amountOfPlacedEyes.getOrDefault(player, 0);
            int placeQuality;
            if (i == 1)
                placeQuality = 200;
            else if (i == 2)
                placeQuality = 175;
            else if (i == 3)
                placeQuality = 150;
            else if (i == 4)
                placeQuality = 125;
            else if (i == 5)
                placeQuality = 110;
            else if (i <= 8)
                placeQuality = 100;
            else if (i <= 12)
                placeQuality = 80;
            else if (i <= 18)
                placeQuality = 50;
            else
                placeQuality = 30;

//            double quality = placeQuality + ((damage / killers.get(0).damage) * 100) + (eyes * 100);
            double quality = placeQuality + (eyes * 100);
            playerQuality.put(player, quality);

            ItemStack uniqueDrop = getPlayerUniqueDrop(bearType, eyes, quality);

            int normalAmount = Functions.randomInt((int) (eyes * 0.5), eyes * 2);
            if (uniqueDrop != null) {
                new PickableItem(player, UNiQUE_DROP_LOCATION, uniqueDrop, true);
            } else
                normalAmount = Functions.randomInt(eyes, eyes * 2);

            normalAmount = (normalAmount + 1) * 3;
            new PickableItem(player, NORMAL_DROP_LOCATION, new Item(Items.get(bearType.name() + "_BEAR_FUR"), normalAmount), false);

            positions.put(player, i);
        }

        for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("BEAR_ISLAND")).collect(Collectors.toList())) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
            player.sendMessage("                      " + bearType.color + "" + ChatColor.BOLD + Functions.setTitleCase(bearType.name()) + " Bear");
            player.sendMessage(ChatColor.RESET + " ");
            player.sendMessage("           " + ChatColor.WHITE + e.getKiller().getDisplayName() + ChatColor.GRAY + " dealt the final hit!");
            player.sendMessage(ChatColor.RESET + " ");
            if (killers.size() > 0)
                player.sendMessage("           " + ChatColor.YELLOW + "" + ChatColor.BOLD + "1st Damager " + ChatColor.GRAY + "- " + killers.get(0).player.getDisplayName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + Functions.getNumberFormat(killers.get(0).damage));
            if (killers.size() > 1)
                player.sendMessage("         " + ChatColor.GOLD + "" + ChatColor.BOLD + "2nd Damager " + ChatColor.GRAY + "- " + killers.get(1).player.getDisplayName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + Functions.getNumberFormat(killers.get(1).damage));
            if (killers.size() > 2)
                player.sendMessage("       " + ChatColor.RED + "" + ChatColor.BOLD + "3rd Damager " + ChatColor.GRAY + "- " + killers.get(2).player.getDisplayName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + Functions.getNumberFormat(killers.get(2).damage));
            player.sendMessage(ChatColor.RESET + " ");
            if (e.getKillers().containsKey(SkyblockDragons.getPlayer(player))) {
                player.sendMessage("                  " + ChatColor.YELLOW + "Your Damage: " + ChatColor.GREEN + Functions.getNumberFormat(e.getKillers().get(SkyblockDragons.getPlayer(player))) + " " + ChatColor.GRAY + "(Position #" + (positions.get(SkyblockDragons.getPlayer(player))) + ")");
                player.sendMessage(ChatColor.RESET + " ");
            }
            player.sendMessage(ChatColor.RESET + "" + ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
        }

        BearIsland.bearDamage.clear();
        BearIsland.bear = null;
        PlayerPlaceFurListener.amountOfPlacedFurs.clear();
        PlayerPlaceFurEvent.resetAmountOfFurs();

        Functions.Wait(100L, BearIsland::resetFurs);

        for (Block block : Functions.loopBlocksHorizontally(BearIsland.MIDDLE_OF_LOOT, 5.5)) {
            block.setType(Material.DIRT);
        }
        Functions.Wait(400L, () -> {
            for (Block block : Functions.loopBlocksHorizontally(BearIsland.MIDDLE_OF_LOOT, 5.5)) {
                block.setType(Material.GRASS);
            }
        });
    }

    @Nullable
    public ItemStack getPlayerUniqueDrop(BearType bearType, int furs, double quality) {
        ItemStack uniqueDrop = null;
        if (quality >= 450) {
            if (Functions.chanceOf(0.05 * furs)) {
                uniqueDrop = new Item(Items.get("BEAR"), new PetModifier(new PetSupplier(Rarity.EPIC, 1, 0)));
            } else if (Functions.chanceOf(0.01 * furs)) {
                uniqueDrop = new Item(Items.get("BEAR"), new PetModifier(new PetSupplier(Rarity.LEGENDARY, 1, 0)));
            } else if (Functions.chanceOf(5 * furs) && bearType == bearType.RED_PANDA_BEAR) {
                uniqueDrop = new Item(Items.get("BEAR_CLOCK"), 1);
            } else if (Functions.chanceOf(5 * furs)) {
                uniqueDrop = new Item(Items.get(bearType.name() + "_FUR"), 1);
            }
        }
        if (uniqueDrop == null && quality >= 420)
            if (Functions.chanceOf(0.02 * furs))
                uniqueDrop = new Item(Items.get(bearType.name() + "_BEAR_HELMET_SKIN"));
        if (uniqueDrop == null && quality >= 410)
            if (Functions.chanceOf(30))
                uniqueDrop = new Item(Items.get(bearType.name() + "_BEAR_CHESTPLATE"));
        if (uniqueDrop == null && quality >= 360)
            if (Functions.chanceOf(30))
                uniqueDrop = new Item(Items.get(bearType.name() + "_BEAR_LEGGINGS"));
        if (uniqueDrop == null && quality >= 295)
            if (Functions.chanceOf(30))
                uniqueDrop = new Item(Items.get(bearType.name() + "_BEAR_HELMET"));
        if (uniqueDrop == null && quality >= 290)
            if (Functions.chanceOf(30))
                uniqueDrop = new Item(Items.get(bearType.name() + "_BEAR_BOOTS"));
        return uniqueDrop;
    }
}