package me.maxiiiiii.skyblockdragons.worlds.end.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.pet.Pet;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Killer;
import me.maxiiiiii.skyblockdragons.util.objects.PickableItem;
import me.maxiiiiii.skyblockdragons.worlds.end.DragonType;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.worlds.end.events.DragonKillEvent;
import me.maxiiiiii.skyblockdragons.worlds.end.events.PlayerPlaceEyeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class DragonKillListener implements Listener {
    public static final Location UNiQUE_DROP_LOCATION = new Location(TheEnd.world, 5, 62, 13);
    public static final Location NORMAL_DROP_LOCATION = new Location(TheEnd.world, 1.5, 62, 19.5);

    @EventHandler
    public void onDragonKill(DragonKillEvent e) {
        List<Killer> killers = new ArrayList<>();
        for (PlayerSD player : e.getKillers().keySet()) {
            killers.add(new Killer(player, e.getKillers().get(player)));
        }
        Collections.sort(killers);

        DragonType dragonType = DragonType.getDragonType(e.getEntity().type.getName());

        if (dragonType == null) {
            SkyblockDragons.logger.info(ChatColor.RED + "Cannot find the type of the dragon!");
            return;
        }

        Map<PlayerSD, Integer> positions = new HashMap<>();

        // 1 to make the place be easier to see
        for (int i = 1; i < killers.size() + 1; i++) {
            PlayerSD player = killers.get(i - 1).player;
            double damage = killers.get(i - 1).damage;

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

            double quality = placeQuality + ((damage / killers.get(0).damage) * 100) + (eyes * 100);

            ItemStack uniqueDrop = null;
            if (quality >= 450) {
                if (Functions.chanceOf(0.05 * eyes)) {
                    uniqueDrop = new Pet(PetMaterial.get("ENDER_DRAGON"), Rarity.EPIC, 1, 0, true);
                } else if (Functions.chanceOf(0.01 * eyes)) {
                    uniqueDrop = new Pet(PetMaterial.get("ENDER_DRAGON"), Rarity.LEGENDARY, 1, 0, true);
                } else if (Functions.chanceOf(5 * eyes) && dragonType == DragonType.SUPERIOR) {
                    uniqueDrop = new Item(Items.get("DRAGON_HORN"), 1);
                } else if (Functions.chanceOf(2 * eyes)) {
                    uniqueDrop = new Item(Items.get("DRAGON_CLAW"), 1);
                } else if (Functions.chanceOf(3 * eyes)) {
                    uniqueDrop = new Item(Items.get("ASPECT_OF_THE_DRAGON"), 1);
                }
            }
            if (uniqueDrop == null && quality >= 420)
                if (Functions.chanceOf(0.02 * eyes))
                    uniqueDrop = new Item(Items.get(dragonType.name() + "_DRAGON_HELMET_SKIN"));
            if (uniqueDrop == null && quality >= 410)
                if (Functions.chanceOf(30))
                    uniqueDrop = new Item(Items.get(dragonType.name() + "_DRAGON_CHESTPLATE"));
            if (uniqueDrop == null && quality >= 360)
                if (Functions.chanceOf(30))
                    uniqueDrop = new Item(Items.get(dragonType.name() + "_DRAGON_LEGGINGS"));
            if (uniqueDrop == null && quality >= 295)
                if (Functions.chanceOf(30))
                    uniqueDrop = new Item(Items.get(dragonType.name() + "_DRAGON_HELMET"));
            if (uniqueDrop == null && quality >= 290)
                if (Functions.chanceOf(30))
                    uniqueDrop = new Item(Items.get(dragonType.name() + "_DRAGON_BOOTS"));

            int normalAmount = Functions.randomInt((int) (eyes * 0.5), eyes * 2);
            if (uniqueDrop != null) {
                new PickableItem(player, UNiQUE_DROP_LOCATION, uniqueDrop, true);
            } else
                normalAmount = Functions.randomInt(eyes, eyes * 2);

            new PickableItem(player, NORMAL_DROP_LOCATION, new Item(Items.get(dragonType.name() + "_DRAGON_FRAGMENT"), normalAmount), false);

            positions.put(player, i);
        }

        for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("TheEnd")).collect(Collectors.toList())) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
            player.sendMessage("                      " + dragonType.color + "" + ChatColor.BOLD + Functions.setTitleCase(dragonType.name()) + " Dragon");
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

        TheEnd.dragonDamage.clear();
        PlayerPlaceEyeListener.amountOfPlacedEyes.clear();
        PlayerPlaceEyeEvent.resetAmountOfEyes();

        Functions.Wait(100L, TheEnd::resetEyes);

        for (Block block : Functions.loopBlocksHorizontally(TheEnd.MIDDLE_OF_LOOT, 5.5)) {
            block.setType(Material.OBSIDIAN);
        }
        Functions.Wait(400L, () -> {
            for (Block block : Functions.loopBlocksHorizontally(TheEnd.MIDDLE_OF_LOOT, 5.5)) {
                block.setType(Material.ENDER_STONE);
            }
        });
    }
}
