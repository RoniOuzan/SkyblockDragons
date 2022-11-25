package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class PowerStoneMenu extends PageMenu {
    protected PowerStoneMenu(PlayerSD player) {
        super(player,
                "Power Stone Selector",
                6,
                InventoryGlassType.SURROUND,
                player.getItems().getAccessoryBag().getUnlockedPowerStones().stream().sorted(PowerStone::compare).map(p -> {
                    if (p == player.getItems().getAccessoryBag().getPowerStone())
                        return addLine(addNBT(p.getItemStack(player), "SELECTED_POWER_" + p.name()), "", ChatColor.RED + "Click to unselect!");
                    return addLine(addNBT(p.getItemStack(player), "POWER_" + p.name()), "", ChatColor.YELLOW + "Click to select!");
                }).collect(Collectors.toList()),
                false
        );
    }

    @Override
    public void update() {
        super.update();

        AccessoryBag accessoryBag = player.getItems().getAccessoryBag();

        this.setItem(47, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Accessory Bag Shortcut", "SHORTCUT", ChatColor.GRAY + "Quickly access your accessory", ChatColor.GRAY + "bag from right here!", "", ChatColor.YELLOW + "Click to open!"), "c3ffd9cc-db06-4eea-ab09-571aa5454092", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0="));

        List<String> accessoryLores = Rarity.getRarities().stream().map(r -> ChatColor.GOLD.toString() + PowerStone.getRarityMagicalPower(r) + " MP " + ChatColor.GRAY + "x " + r.getColor() + accessoryBag.getItems().stream().filter(i -> i.getRarity() == r).count() + " Accs " + ChatColor.GRAY + "= " + ChatColor.GOLD + (PowerStone.getRarityMagicalPower(r) * accessoryBag.getItems().stream().filter(i -> i.getRarity() == r).count()) + " MP").collect(Collectors.toList());
        accessoryLores.add("");
        accessoryLores.add(ChatColor.GRAY + "Total: " + ChatColor.GOLD + accessoryBag.getMagicalPower() + " Magical Power");
        this.setItem(48, createItem(Material.PAPER, ChatColor.GREEN + "Accessories Breakdown", "BREAKDOWN", accessoryLores));

        this.setItem(50, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Learn Power from Stones", "LEARN", ChatColor.GRAY + "Combine " + ChatColor.GREEN + "9x " + ChatColor.GRAY + "identical " + ChatColor.BLUE + "Power", ChatColor.BLUE + "Stones " + ChatColor.GRAY + "that you may find across ", ChatColor.GREEN + "Skyblock " + ChatColor.GRAY + "to " + ChatColor.GRAY + "permanently unlock", ChatColor.GRAY + "their " + ChatColor.GREEN + "power" + ChatColor.GRAY + ".", "", ChatColor.YELLOW + "Click to combine!"), "04631294-8d69-4aa1-8f47-7b0a3bd07fba", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFlMWY2MTYyZGI0MjI0NTYzOTYwOWY3MjhhNGUxMzRlZDdiZDdkZTNjMTVhNzc5MmQyMTlhNmUyYTlkYiJ9fX0="));

        List<String> tuningLores = new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "Optimize your build to your liking",
                ChatColor.GRAY + "by using " + ChatColor.YELLOW + "Tuning Points" + ChatColor.GRAY + ".",
                "",
                ChatColor.GRAY + "Every " + ChatColor.GOLD + "10 Magical Power " + ChatColor.GRAY + "grants",
                ChatColor.YELLOW + "1 Tuning Point" + ChatColor.GRAY + ".",
                "",
                ChatColor.GRAY + "Magical Power: " + ChatColor.GOLD + accessoryBag.getMagicalPower(),
                ChatColor.GRAY + "Tuning Points: " + ChatColor.YELLOW + accessoryBag.getTuningPoints(),
                ""));
        if (!accessoryBag.getTuning().isEmpty()) {
            tuningLores.add(ChatColor.GRAY + "Your Tuning:");
            tuningLores.addAll(accessoryBag.getTuningStats().entrySet().stream().map(e -> e.getKey().toAddLore(e.getValue())).collect(Collectors.toList()));
            tuningLores.add("");
        }
        tuningLores.add(ChatColor.YELLOW + "Click to tune!");
        this.setItem(51, createItem(Material.REDSTONE_COMPARATOR, ChatColor.GREEN + "Tuning Points", "TUNING", tuningLores));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        String nbt = this.getNBT(e.getCurrentItem());
        if (nbt.equals("SHORTCUT")) {
            new AccessoryBagMenu(player);
        } else if (nbt.equals("LEARN")) {
            new Learn();
        } else if (nbt.equals("TUNING")) {
            new Tuning();
        } else if (nbt.contains("SELECTED_POWER_")) {
            PowerStone power = PowerStone.valueOf(nbt.replace("SELECTED_POWER_", ""));
            player.getItems().getAccessoryBag().setPowerStone(PowerStone.NONE);
            player.sendMessage(ChatColor.GREEN + "You have unselected the " + power.getName() + " power stone.");
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1f);
            player.closeInventory();
        } else if (nbt.contains("POWER_")) {
            PowerStone power = PowerStone.valueOf(nbt.replace("POWER_", ""));
            player.getItems().getAccessoryBag().setPowerStone(power);
            player.sendMessage(ChatColor.GREEN + "You have selected the " + power.getName() + " power stone.");
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1f);
            player.closeInventory();
        }
    }

    public static final Map<StatType, Double> AMOUNT_PER_POINT = new HashMap<>();

    static {
        AMOUNT_PER_POINT.put(StatTypes.HEALTH, 5.0);
        AMOUNT_PER_POINT.put(StatTypes.DEFENSE, 3.0);
        AMOUNT_PER_POINT.put(StatTypes.SPEED, 1.5);
        AMOUNT_PER_POINT.put(StatTypes.INTELLIGENCE, 2.0);

        AMOUNT_PER_POINT.put(StatTypes.STRENGTH, 1.0);
        AMOUNT_PER_POINT.put(StatTypes.CRIT_DAMAGE, 1.0);
        AMOUNT_PER_POINT.put(StatTypes.CRIT_CHANCE, 0.2);
        AMOUNT_PER_POINT.put(StatTypes.ATTACK_SPEED, 0.3);
    }

    public class Tuning extends Menu {
        public Tuning() {
            super(PowerStoneMenu.this.player,
                    "Stats Tuning",
                    6,
                    InventoryGlassType.ALL,
                    false
            );
        }

        @Override
        public void update() {
            List<String> tuning = new ArrayList<>(Arrays.asList(
                    ChatColor.GRAY + "Optimize your build to your liking",
                    ChatColor.GRAY + "by using " + ChatColor.YELLOW + "Tuning Points" + ChatColor.GRAY + ".",
                    "",
                    ChatColor.GRAY + "Every " + ChatColor.GOLD + "10 Magical Power " + ChatColor.GRAY + "grants",
                    ChatColor.YELLOW + "1 Tuning Point" + ChatColor.GRAY + ".",
                    "",
                    ChatColor.GRAY + "Magical Power: " + ChatColor.GOLD + player.getItems().getAccessoryBag().getMagicalPower(),
                    ChatColor.GRAY + "Tuning Points: " + ChatColor.YELLOW + player.getItems().getAccessoryBag().getTuningPoints(),
                    ""));
            if (!player.getItems().getAccessoryBag().getTuning().isEmpty()) {
                tuning.add(ChatColor.GRAY + "Your Tuning: " + 0);
                tuning.add("");
            }
            tuning.add(ChatColor.YELLOW + "Click to tune!");
            this.setItem(4, createItem(Material.REDSTONE_COMPARATOR, ChatColor.GREEN + "Tuning Points", "TUNING", tuning));

            this.setItem(20, get(StatTypes.HEALTH));
            this.setItem(21, get(StatTypes.DEFENSE));
            this.setItem(29, get(StatTypes.SPEED));
            this.setItem(30, get(StatTypes.INTELLIGENCE));

            this.setItem(23, get(StatTypes.STRENGTH));
            this.setItem(24, get(StatTypes.CRIT_DAMAGE));
            this.setItem(32, get(StatTypes.CRIT_CHANCE));
            this.setItem(33, get(StatTypes.ATTACK_SPEED));
        }

        private ItemStack get(StatType stat) {
            return addLine(addNBT(stat.getItem(), "STAT_" + stat.name()),
                    "",
                    ChatColor.GRAY + "You have: " + player.getStats().get(stat).toString(),
                    "",
                    ChatColor.GRAY + "Stat tuned: " + ChatColor.YELLOW + player.getItems().getAccessoryBag().getTuning().getOrDefault(stat, 0),
                    ChatColor.GRAY + "Per point: " + stat.getColor() + (AMOUNT_PER_POINT.get(stat) + "").replace(".0", "") + stat.getIcon(),
                    ChatColor.GRAY + "Points left: " + ChatColor.YELLOW + (player.getItems().getAccessoryBag().getTuningPoints() - player.getItems().getAccessoryBag().getUsedTuning()),
                    "",
                    ChatColor.YELLOW + "Right-click to add a point!",
                    ChatColor.AQUA + "Left-click to remove a point!",
                    ChatColor.DARK_GRAY + "Hold shift for 10 points!"
            );
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            String nbt = this.getNBT(e.getCurrentItem());
            if (nbt.contains("STAT_")) {
                StatType stat = StatTypes.get(nbt.replace("STAT_", ""));
                AccessoryBag accessoryBag = player.getItems().getAccessoryBag();
                if (e.getClick().isRightClick()) {
                    if (accessoryBag.getLeftTuningPoints() > 0) {
                        int amount = e.getClick().isShiftClick() ? Math.min(accessoryBag.getLeftTuningPoints(), 10) : 1;
                        accessoryBag.tune(stat, amount);
                        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1f);
                        this.update();
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have tuning points left!");
                    }
                } else if (e.getClick().isLeftClick()) {
                    if (accessoryBag.getTuning().getOrDefault(stat, 0) > 0) {
                        int amount = e.getClick().isShiftClick() ? -Math.min(accessoryBag.getTuning().get(stat), 10) : -1;
                        accessoryBag.tune(stat, amount);
                        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1f);
                        this.update();
                    } else {
                        player.sendMessage(ChatColor.RED + "You haven't tune this stat yet!");
                    }
                }
            }
        }
    }

    public class Learn extends Menu {
        public Learn() {
            super(PowerStoneMenu.this.player,
                    "Learn Power from Stones",
                    6,
                    InventoryGlassType.ALL,
                    false
            );
        }

        @Override
        public void update() {
            List<String> lores = new ArrayList<>(Arrays.asList(
                    ChatColor.GRAY + "Fill all the slots on the right",
                    ChatColor.GRAY + "with identical " + ChatColor.BLUE + "Power Stones",
                    ChatColor.GRAY + "to permanently unlock their",
                    ChatColor.GREEN + "power" + ChatColor.GRAY + ".",
                    ""
            ));
            if (this.isFull())
                lores.add(ChatColor.YELLOW + "Click to learn!");
            else
                lores.add(ChatColor.RED + "Missing some stones!");

            this.setItem(20, Functions.applySkull(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + "Learn New Power", "LEARN", lores), "04631294-8d69-4aa1-8f47-7b0a3bd07fba", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFlMWY2MTYyZGI0MjI0NTYzOTYwOWY3MjhhNGUxMzRlZDdiZDdkZTNjMTVhNzc5MmQyMTlhNmUyYTlkYiJ9fX0="));
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (this.getNBT(e.getCurrentItem()).equals("LEARN")) {
                if (isFull()) {

                }
            }
        }

        private boolean isFull() {
            int amount = 0;
            for (int i = 0; i < 9; i++) {
                if (Functions.isNotAir(this.getItem(13 + i + (9 * (i / 3)))))
                    amount++;
            }
            return amount == 9;
        }
    }

    public static class Command extends CommandSD {
        @Override
        public void command(PlayerSD player, String[] args) {
            new PowerStoneMenu(player);
        }

        @Override
        public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
            return tabs;
        }
    }
}
