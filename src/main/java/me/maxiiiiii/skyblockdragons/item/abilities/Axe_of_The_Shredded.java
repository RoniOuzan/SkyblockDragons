package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.players;

public class Axe_of_The_Shredded implements Listener {
    private final HashMap<Player, Integer> manaCost = new HashMap<>();
    private final HashMap<Player, Long> timeUsed = new HashMap<>();

    @EventHandler
    public void onPlayerUseAbility(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("AXE_OF_THE_SHREDDED")) return;

        PlayerSD player = e.getPlayer();
        Location location = player.getLocation();

        if (!players.get(player.getUniqueId()).manaCost(manaCost.getOrDefault(player, 10) * 2, player.getEquipment().getItemInMainHand(), 0)) return;

        if (timeUsed.getOrDefault(player, System.currentTimeMillis() + 5000L) - System.currentTimeMillis() >= 5000) manaCost.put(player, 20);
        else
            if (manaCost.get(player) < 320) manaCost.put(player, manaCost.getOrDefault(player, 10) * 2);

        timeUsed.put(player, System.currentTimeMillis());

        player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1f, 1f);
        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        stand.setVisible(false);
        stand.setGravity(false);
        stand.setMarker(true);
        stand.addScoreboardTag("AxeOfTheShredded");

        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        stand.setItemInHand(axe);

        Loop(50, 1L, (i) -> {
            if (stand.getLocation().add(0, 1, 0).getBlock().getType().isSolid()) {
                stand.remove();
                return;
            }

            Location newLocation = location.clone().add(location.clone().getDirection().multiply(i));
            stand.teleport(newLocation);
            stand.setRightArmPose(new EulerAngle(Math.toRadians(i * 40), 0, 0));
        }, (i) -> stand.remove());
    }
}
