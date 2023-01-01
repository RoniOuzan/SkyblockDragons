package me.maxiiiiii.skyblockdragons.inventory.menus;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProfileMenu extends Menu {
    protected final PlayerSD target;

    public ProfileMenu(PlayerSD player, PlayerSD target) {
        super(player,
                player.equals(target) ? "Your Profile" : target.getName() + "'s Profile",
                6,
                InventoryGlassType.ALL,
                true);

        this.target = target;
    }

    public ProfileMenu(PlayerSD player) {
        this(player, player);
    }

    @Override
    public void update() {
        this.setItem(22, Functions.applyHead(Functions.createItem(Material.SKULL_ITEM, 1, 3, ChatColor.WHITE + target.getName() + "'s Profile"), target));

        this.placeItem(10, target.getItems().getHelmet(), "Helmet", "HELMET");
        this.placeItem(19, target.getItems().getChestplate(), "Chestplate", "CHESTPLATE");
        this.placeItem(28, target.getItems().getLeggings(), "Leggings", "LEGGINGS");
        this.placeItem(37, target.getItems().getBoots(), "Boots", "BOOTS");
        this.placeItem(20, target.getItems().getTool(), "Hand", "HAND");
        this.placeItem(29, target.getItems().getPet(), "Pet", "PET");

        this.setItem(24, StatsType.COMBAT.item.apply(target));
        this.setItem(25, StatsType.GATHERING.item.apply(target));
        this.setItem(33, StatsType.WISDOM.item.apply(target));
        this.setItem(34, StatsType.MISC.item.apply(target));
    }

    private void placeItem(int slot, Item item, String defaultName, String nbt) {
        if (item == null || item.getMaterial().getItemID().equals("NULL")) {
            this.setItem(slot, createItem(Material.STAINED_GLASS_PANE, 8, ChatColor.GRAY + "Empty " + defaultName + " Slot", nbt, ChatColor.GRAY + "Equip an item on your " + defaultName + " and it", ChatColor.GRAY + "will be showed here."));
        } else {
            this.setItem(slot, item);
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        String nbt = this.getNBT(e.getCurrentItem());
        if (nbt.contains("STATS")) {
            new StatsViewer(StatsType.valueOf(nbt.replace("_STATS", "")));
        }
    }

    private class StatsViewer extends PageMenu {
        private final StatsType type;

        public StatsViewer(StatsType type) {
            super(ProfileMenu.this.target,
                    "Your Stats Breakdown",
                    6,
                    InventoryGlassType.ALL,
                    type.stats.apply(ProfileMenu.this.target).stream().map(s -> addNBT(s.getItem(), "STAT_" + s.getType().name())).collect(Collectors.toList()),
                    true
            );
            this.type = type;
        }

        @Override
        public void update() {
            super.update();

            this.setItem(4, this.type.item.apply(player));
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            String nbt = this.getNBT(e.getCurrentItem());
            if (nbt.contains("STAT_")) {
                new StatAddersViewer(StatTypes.get(nbt.replace("STAT_", "")));
            }
        }

        private class StatAddersViewer extends PageMenu {
            public StatAddersViewer(StatType statType) {
                super(ProfileMenu.this.player,
                        "Your " + statType.getName() + " Breakdown",
                        6,
                        InventoryGlassType.ALL,
                        ProfileMenu.this.player.getStats().get(statType).getStatAdders().stream().sorted().map(s -> s.getItem(statType)).collect(Collectors.toList()),
                        true
                );
            }

            @Override
            public void onInventoryClick(InventoryClickEvent e) {
            }
        }
    }

    public static class Command implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
            if (sender instanceof Player) {
                PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
                if (args.length > 0) {
                    new ProfileMenu(player, SkyblockDragons.getPlayer(args[0]));
                } else {
                    new ProfileMenu(player);
                }
            }
            return true;
        }
    }

    public static class Event implements Listener {
        @EventHandler
        public void onClickOnPlayer(PlayerInteractEntityEvent e) {
            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
            if (e.getRightClicked() instanceof Player && !e.getRightClicked().hasMetadata("NPC") && player.getItems().getTool() == null) {
                new ProfileMenu(player, SkyblockDragons.getPlayer((Player) e.getRightClicked()));
            }
        }
    }

    private enum StatsType {
        COMBAT(p -> p.getStats().toCombatList(), p -> {
            List<String> lores = Functions.loreBuilder("Gives you a better chance at fighting strong monsters.");
            lores.add("");
            lores.addAll(p.getStats().toCombatList().stream().map(Stat::toStringLore).collect(Collectors.toList()));
            lores.add("");
            lores.add(ChatColor.YELLOW + "Click for details!");
            lores.add(11, ChatColor.RED + p.getStats().getHealth().getType().getIcon() + " Effective Health " + ChatColor.WHITE + Functions.getNumberFormat(p.getStats().getEffectiveHealth()) + (p.getStats().getHealth().getType() instanceof PercentageStat ? "%" : ""));
            return createItem(Material.DIAMOND_SWORD, ChatColor.RED + "Combat Stats", "COMBAT_STATS", lores);

        }),
        GATHERING(p -> p.getStats().toGatheringList(), p -> {
            List<String> lores = Functions.loreBuilder("Lets you collect and harvest better items, or more of them.");
            lores.add("");
            lores.addAll(p.getStats().toGatheringList().stream().map(Stat::toStringLore).collect(Collectors.toList()));
            lores.add("");
            lores.add(ChatColor.YELLOW + "Click for details!");
            return createItem(Material.IRON_PICKAXE, ChatColor.YELLOW + "Gathering Stats", "GATHERING_STATS", lores);

        }),
        WISDOM(p -> p.getStats().toWisdomList(), p -> {
            List<String> lores = Functions.loreBuilder("Increases the " + ChatColor.AQUA + "XP " + ChatColor.GRAY + "you gain on your skills.");
            lores.add("");
            lores.addAll(p.getStats().toWisdomList().stream().map(Stat::toStringLore).collect(Collectors.toList()));
            lores.add("");
            lores.add(ChatColor.YELLOW + "Click for details!");
            return createItem(Material.BOOK, ChatColor.AQUA + "Wisdom Stats", "WISDOM_STATS", lores);

        }),
        MISC(p -> p.getStats().toMiscList(), p -> {
            List<String> lores = Functions.loreBuilder("Augments various aspects of your gameplay.");
            lores.add("");
            lores.addAll(p.getStats().toMiscList().stream().map(Stat::toStringLore).collect(Collectors.toList()));
            lores.add("");
            lores.add(ChatColor.YELLOW + "Click for details!");
            return createItem(Material.WATCH, ChatColor.LIGHT_PURPLE + "Misc Stats", "MISC_STATS", lores);
        });

        private final Function<PlayerSD, List<Stat>> stats;
        private final Function<PlayerSD, ItemStack> item;

        StatsType(Function<PlayerSD, List<Stat>> stats, Function<PlayerSD, ItemStack> item) {
            this.stats = stats;
            this.item = item;
        }
    }
}
