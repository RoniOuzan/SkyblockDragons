package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerUseAbilityListener implements Listener {
    public static final Cooldown<PlayerSD> shootCooldown = new Cooldown<>();

    @EventHandler
    public void onUseAbility(PlayerInteractEvent e) {
        ItemStack itemStack = e.getItem();

        if (!(Items.get(itemStack) instanceof ItemAbilityAble)) return;
        ItemAbilityAble material = (ItemAbilityAble) Items.get(itemStack);

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        ItemAbility usedAbility = null;
        for (ItemAbility ability : material.getAbilities()) {
            if (ability.getAction().isPlayerUsedAbility(e)) {
                usedAbility = ability;
                break;
            }
        }

        if (usedAbility == null) return;

        e.setCancelled(true);
        useAbility(player, usedAbility, e);
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) e.getEntity().getShooter());

            if (!(player.getItems().getTool().getMaterial() instanceof ItemAbilityAble)) return;
            ItemAbilityAble material = (ItemAbilityAble) player.getItems().getTool().getMaterial();

            if (Functions.cooldown(player, shootCooldown, 300, false)) return;

            ItemAbility usedAbility = null;
            for (ItemAbility ability : material.getAbilities()) {
                if (ability.getAction() == AbilityAction.SHOOT) {
                    usedAbility = ability;
                    break;
                }
            }

            if (usedAbility == null) return;

            e.setCancelled(true);
            useAbility(player, usedAbility, e);
        }
    }

    public void useAbility(PlayerSD player, ItemAbility usedAbility, Event e) {
        usedAbility.setupAbilityPerPlayer(player);
        if (usedAbility.hasCosts(player)) {
            usedAbility.applyCosts(player);
            usedAbility.onPlayerUse(new PlayerAbilityUsage(player, player.getItems().getTool(), usedAbility, e));
        }

        PlayerUseAbilityEvent event = new PlayerUseAbilityEvent(player, player.getItems().getTool(), usedAbility);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
