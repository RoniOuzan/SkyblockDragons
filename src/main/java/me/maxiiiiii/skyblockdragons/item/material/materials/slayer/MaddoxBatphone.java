package me.maxiiiiii.skyblockdragons.item.material.materials.slayer;

import me.maxiiiiii.skyblockdragons.item.material.types.PhoneMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.slayer.SlayerMenu;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

public class MaddoxBatphone extends PhoneMaterial {
    public MaddoxBatphone() {
        super("MADDOX_BATPHONE",
                Material.SKULL_ITEM,
                ItemFamily.NULL,
                "Maddox Batphone",
                ItemType.PHONE,
                Rarity.UNCOMMON,
                "",
                new Whassup()
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("772aefd3-c075-4e1f-80bd-8c4adf5322d7",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTMzNmQ3Y2M5NWNiZjY2ODlmNWU4Yzk1NDI5NGVjOGQxZWZjNDk0YTQwMzEzMjViYjQyN2JjODFkNTZhNDg0ZCJ9fX0="
        );
    }

    private static class Whassup extends ItemAbility implements ItemAbilityCooldown {
        protected Whassup() {
            super(AbilityAction.RIGHT_CLICK,
                    "Whassup?",
                    "Lets you call " + ChatColor.DARK_PURPLE + "Maddox " + ChatColor.GRAY + ", when he's not busy."
            );
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 2;
        }

        @Override
        protected PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();
                player.sendMessage(ChatColor.YELLOW + "Ring!");
            Functions.Loop(12, 1, i1 ->
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 0.8f, 15f)
                , i1 -> Functions.Wait(8, () ->  {
                    player.sendMessage(ChatColor.YELLOW + "Ring! Ring!");
                    Functions.Loop(12, 1, i2 ->
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 0.8f, 15f)
                    , i2 -> Functions.Wait(8, () ->  {
                        player.sendMessage(ChatColor.YELLOW + "Ring! Ring! Ring!");
                        Functions.Loop(12, 1, i3 ->
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 0.8f, 15f)
                        , i3 -> Functions.Wait(8, () -> {
                            new SlayerMenu(player);
                            player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 0.8f, 0.7f);
                            player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_OFF, 0.8f, 0.7f);
                        }));
                    }));
                }));
            };
        }
    }
}
