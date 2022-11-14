package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.material.types.ShortBowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerUseAbilityListener implements Listener {
    private final Cooldown<Player> cooldown = new Cooldown<>();

    @EventHandler
    public void onUseAbility(PlayerInteractEvent e) {
        ItemStack itemStack = e.getItem();

        if (Items.get(itemStack) instanceof ShortBowMaterial && !Functions.cooldown(e.getPlayer(), cooldown, 200, false)) {
            Projectile projectile = e.getPlayer().getWorld().spawnArrow(new Location(WorldSD.HUB.getWorld(), 0, 0, 0), e.getPlayer().getLocation().getDirection(), 0, 0);
            Bukkit.getPluginManager().callEvent(new EntityShootBowEvent(e.getPlayer(), itemStack, projectile, -1));
            e.setCancelled(true);
        }

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
        useAbility(player, player.getItems().getTool(), usedAbility, material, e);
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        if (e.getProjectile().getScoreboardTags().contains("UNEVENTABLE")) return;

        if (e.getEntity() instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) e.getEntity());

            if (!(player.getItems().getTool().getMaterial() instanceof ItemAbilityAble)) return;
            ItemAbilityAble material = (ItemAbilityAble) player.getItems().getTool().getMaterial();

            ItemAbility usedAbility = null;
            for (ItemAbility ability : material.getAbilities()) {
                if (ability.getAction() == AbilityAction.SHOOT) {
                    usedAbility = ability;
                    break;
                }
            }

            if (usedAbility == null) return;

            e.setCancelled(true);
            useAbility(player, player.getItems().getTool(), usedAbility, material, e);
        }
    }

    public void useAbility(PlayerSD player, Item item, ItemAbility usedAbility, ItemAbilityAble material, Event e) {
        if (material instanceof ItemRequirementAble && !((ItemRequirementAble) material).getRequirements().hasRequirements(player)) {
            player.sendNoRequirementsMessage("ability");
            return;
        }

        usedAbility.setupAbilityPerPlayer(player);
        if (usedAbility.hasCosts(player, item)) {
            usedAbility.applyCosts(player, item);
            usedAbility.onPlayerUse(new PlayerAbilityUsage(player, player.getItems().getTool(), usedAbility, e));
        }

        PlayerUseAbilityEvent event = new PlayerUseAbilityEvent(player, player.getItems().getTool(), usedAbility);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
