package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.other;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.util.Functions.cooldown;

public class GrapplingHook extends ToolMaterial {
    public GrapplingHook() {
        super("GRAPPLING_HOOK",
                Material.FISHING_ROD,
                ItemFamily.GRAPPLING_HOOK,
                "Grappling Hook",
                ItemType.ITEM ,
                Rarity.UNCOMMON,
                "",
                new TravelHook()
        );
    }

    public static class TravelHook extends ItemAbility implements ItemAbilityCooldown, Listener {
        public TravelHook() {
            super(AbilityAction.NONE,
                    "Travel Hook",
                    ChatColor.GRAY + "Travel around in style using this " + ChatColor.GREEN + "Grappling Hook" + ChatColor.GRAY + "."
            );
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 2;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {};
        }

        private final Cooldown<Player> cooldown = new Cooldown<>();

        @EventHandler
        public void onFish(PlayerFishEvent e) {
            if (e.getState() != PlayerFishEvent.State.FAILED_ATTEMPT && e.getState() != PlayerFishEvent.State.IN_GROUND) return;

            ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();

            if (!Functions.getId(item).equals("GRAPPLING_HOOK")) return;

            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

            if (cooldown(player, cooldown, (long) (this.getFinalCooldown(player) * 1000), true)) return;

            Location l1 = player.getLocation();
            Location l2 = e.getHook().getLocation();

            Vector v = new Vector(
                    (l2.getX() - l1.getX()) / 2,
                    (l2.getY() - l1.getY()) / 8 + 0.5,
                    (l2.getZ() - l1.getZ()) / 2);

            player.setVelocity(v);
        }
    }
}
