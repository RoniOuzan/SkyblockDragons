package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PlayerUseAbilityListener implements Listener {
    public static final Map<PlayerSD, Map<ItemAbility, Cooldown<PlayerSD>>> cooldowns = new HashMap<>();

    @EventHandler
    public void onUseAbility(PlayerInteractEvent e) {
        ItemStack itemStack = e.getItem();

        if (!(Items.get(itemStack) instanceof ItemAbilityAble)) return;
        ItemAbilityAble material = (ItemAbilityAble) Items.get(itemStack);

//        ItemAbility ability = null;
//        boolean hasSneak = material.getAbilities().stream().anyMatch(a -> a.getAction().isShiftClick());
//
//        int finalI = 0;
//        for (int i = 0; i < material.getAbilities().size(); i++) {
//            ItemAbility itemAbility = material.getAbilities().get(i);
//
//            if (!itemAbility.getAction().isShiftClick() && hasSneak && itemAbility.getAction().isShiftClick()) continue;
//
//            if ((e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && itemAbility.getAction().isLeftClick()) {
//                ability = itemAbility;
//                finalI = i;
//                break;
//            } else if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && itemAbility.getAction().isRightClick()) {
//                ability = itemAbility;
//                finalI = i;
//                break;
//            }
//        }
//
//        if (ability == null) return;
//
//        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
//        Item item = new Item(player, itemStack);
//
//        if (item.getMaterial() != Items.get("BONEMERANG")) {
//            if (!cooldowns.containsKey(player)) {
//                cooldowns.put(player, new HashMap<>());
//            }
//            if (!cooldowns.get(player).containsKey(ability)) {
//                Map<ItemAbility, Cooldown<PlayerSD>> map = cooldowns.get(player);
//                map.put(ability, new Cooldown<>());
//                cooldowns.put(player, map);
//            }
//
////            if (Functions.cooldown(player, cooldowns.get(player).get(ability), ability.getCooldown() * 1000L, true)) return;
//        }
//        if (player.manaCost(itemStack, finalI)) return; // TODO: change this to the new mana cost system
//        if (item.getMaterial() instanceof ItemRequirementAble) {
//            if (!((ItemRequirementAble) item.getMaterial()).getRequirements().stream().allMatch(r -> r.hasRequirement(player))) {
//                player.sendMessage(ChatColor.RED + "You don't have the requirements to use this item!");
//                return;
//            }
//        }
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
        usedAbility.setupAbilityPerPlayer(player);
        if (usedAbility.hasCosts(player)) {
            usedAbility.applyCosts(player);
            usedAbility.onPlayerUse(new PlayerAbilityUsage(player, player.getItems().getTool(), usedAbility, e));
        }

//        PlayerUseAbilityEvent event = new PlayerUseAbilityEvent(player, player.getItems().getTool(), usedAbility);
//        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
