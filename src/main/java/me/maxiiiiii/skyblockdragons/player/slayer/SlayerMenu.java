package me.maxiiiiii.skyblockdragons.player.slayer;

import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SlayerMenu extends Menu {
    public SlayerMenu(PlayerSD player) {
        super(player,
                "Slayer",
                4,
                InventoryGlassType.ALL,
                false
        );
    }

    @Override
    public void update() {
        if (!player.getSlayers().getQuest().isActive()) {
            this.setItem(11, getItem(SlayerType.REVENANT));
            this.setItem(13, getItem(SlayerType.TARANTULA));
            this.setItem(15, getItem(SlayerType.SVEN));
            this.setItem(30, createItem(
                    Material.INK_SACK,
                    player.getSlayers().getQuest().isAutoSlayer() ? 10 : 8,
                    ChatColor.AQUA + "Auto Slayer",
                    "AUTO_SLAYER",
                    ChatColor.GRAY + "Upon defeating a boss, " + ChatColor.GREEN + "automatically " + ChatColor.GRAY + "completes the",
                    "quest and starts another of the",
                    ChatColor.GRAY + "same type if you have enough " + ChatColor.GOLD + "coins " + ChatColor.GRAY + "in your purse.",
                    "",
                    ChatColor.GRAY + "Currently: " + (player.getSlayers().getQuest().isAutoSlayer() ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"),
                    "",
                    ChatColor.YELLOW + "Click to disable!")
            );
        } else if (player.getSlayers().getQuest().getState() == SlayerQuest.SlayerQuestState.SLAIN) {
            ChatColor color = SlayerType.getTiersColors(player.getSlayers().getQuest().getTier());
            this.setItem(13, createItem(Material.WHITE_GLAZED_TERRACOTTA, 5, ChatColor.GREEN + "Slayer Quest Completed", "SLAYED", Functions.loreBuilder("You completed the slayer quest " + color + player.getSlayers().getQuest().getType().getName() + color + " Tier " + color + player.getSlayers().getQuest().getTier() + ChatColor.GRAY + ", now you can claim the rewards and progress!" + " RESET_LENGTH NEW_LINE NEW_LINE " + ChatColor.YELLOW + "Click to claim!")));
        } else if (player.getSlayers().getQuest().getState() != SlayerQuest.SlayerQuestState.FAILED) {
            ChatColor color = SlayerType.getTiersColors(player.getSlayers().getQuest().getTier());
            this.setItem(13, createItem(Material.WHITE_GLAZED_TERRACOTTA, 14, ChatColor.RED + "Cancel Slayer Quest", "CANCEL", Functions.loreBuilder("You have started " + color + player.getSlayers().getQuest().getType().getName() + color + "Tier " + color + player.getSlayers().getQuest().getTier() + ChatColor.GRAY + ", you can cancel this quest but you will not get refunds!" + " NEW_LINE NEW_LINE " + ChatColor.YELLOW + "Click to cancel!")));
        } else {
            ChatColor color = SlayerType.getTiersColors(player.getSlayers().getQuest().getTier());
            this.setItem(13, createItem(Material.WHITE_GLAZED_TERRACOTTA, 14, ChatColor.RED + "Failed The Quest", "FAILED", Functions.loreBuilder("You have started " + color + player.getSlayers().getQuest().getType().getName() + color + "Tier " + color + player.getSlayers().getQuest().getTier() + ChatColor.GRAY + ", but you failed to slay it!" + " NEW_LINE NEW_LINE " + ChatColor.YELLOW + "Click to continue!")));
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        String nbt = this.getNBT(e.getCurrentItem());
        if (nbt.contains("SLAYER_")) {
            SlayerType slayerType = SlayerType.valueOf(nbt.replace("SLAYER_", ""));
            new SlayerTypeMenu(slayerType);
        } else if (nbt.equals("AUTO_SLAYER")) {
            player.getSlayers().getQuest().toggleAutoSlayer();
            this.update();
        } else if (nbt.equals("SLAYED")) {
            player.getSlayers().complete();
            this.update();
        } else if (nbt.equals("CANCEL")) {
            player.getSlayers().cancelQuest();
            player.sendMessage(ChatColor.RED + "You have cancelled the slayer quest!");
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 10f);
            this.update();
        } else if (nbt.equals("FAILED")) {
            player.getSlayers().cancelQuest();
            player.sendMessage(ChatColor.RED + "You have failed the slayer quest!");
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 10f);
            this.update();
        }
    }

    private ItemStack getItem(SlayerType slayer) {
        return addLine(addNBT(slayer.getItem(), "SLAYER_" + slayer.name()),
                "",
                ChatColor.GRAY + slayer.getName() + " Slayer: " + ChatColor.YELLOW + "LVL " + player.getSlayers().get(slayer).getLevel(),
                "",
                ChatColor.YELLOW + "Click to view boss!"
        );
    }

    private class SlayerTypeMenu extends Menu {
        private final SlayerType slayerType;

        public SlayerTypeMenu(SlayerType slayerType) {
            super(SlayerMenu.this.player,
                    "Slayer",
                    6,
                    InventoryGlassType.ALL,
                    false
            );
            this.slayerType = slayerType;
        }

        @Override
        public void update() {
            this.setItem(11, createItem(slayerType.getItemStack(), ChatColor.RED + slayerType.getName() + " 1", "TIER_1"));
            this.setItem(12, createItem(slayerType.getItemStack(), ChatColor.RED + slayerType.getName() + " 2", "TIER_2"));
            this.setItem(13, createItem(slayerType.getItemStack(), ChatColor.RED + slayerType.getName() + " 3", "TIER_3"));
            this.setItem(14, createItem(slayerType.getItemStack(), ChatColor.RED + slayerType.getName() + " 4", "TIER_4"));
            this.setItem(15, createItem(slayerType.getItemStack(), ChatColor.RED + slayerType.getName() + " 5", "TIER_5"));
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            String nbt = this.getNBT(e.getCurrentItem());
            if (nbt.contains("TIER_")) {
                int tier = Integer.parseInt(nbt.replace("TIER_", ""));
                player.getSlayers().startQuest(slayerType, tier);
                player.closeInventory();
            }
        }
    }
}
