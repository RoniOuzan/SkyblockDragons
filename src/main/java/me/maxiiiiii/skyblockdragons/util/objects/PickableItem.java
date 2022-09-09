package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class PickableItem implements Listener {
    public static final Map<Player, Cooldown<Player>> cooldowns = new HashMap<>();
    public static final Map<UUID, PickableItem> pickableItems = new HashMap<>();

    private PlayerSD player;
    private ArmorStand armorStand;
    private ItemStack itemStack;
    private boolean message;

    public PickableItem(PlayerSD player, Location location, ItemStack item, boolean message) {
        this.player = player;
        this.itemStack = item;
        this.armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        this.armorStand.setVisible(false);
        this.armorStand.addScoreboardTag("PickableItem");
        this.armorStand.setGravity(false);
        this.armorStand.setInvulnerable(true);
        this.armorStand.setMarker(true);
        this.armorStand.setCustomName(item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : "");
        this.armorStand.setCustomNameVisible(true);
        if (item.getType().name().contains("CHESTPLATE"))
            this.armorStand.setChestplate(item);
        else if (item.getType().name().contains("LEGGINGS"))
            this.armorStand.setLeggings(item);
        else if (item.getType().name().contains("BOOTS"))
            this.armorStand.setBoots(item);
        else if (item.getType().name().contains("HELMET") || item.getType() == Material.SKULL_ITEM)
            this.armorStand.setHelmet(item);
        else {
            this.armorStand.setItemInHand(item);
            this.armorStand.setRightArmPose(new EulerAngle(207, 270, 0));
        }
        this.message = message;

        if (player != null) {
            for (Player loop : Bukkit.getOnlinePlayers().stream().filter(p -> !p.getName().equals(player.getName())).collect(Collectors.toList())) {
                SkyblockDragons.entityHider.hideEntity(loop, this.armorStand);
            }
        }

        pickableItems.put(this.armorStand.getUniqueId(), this);

        Functions.Wait(400L, () -> {
            if (!this.armorStand.isDead()) {
                this.give();
            }
        });
    }

    public PickableItem(Location location, ItemStack item, boolean message) {
        this(null, location, item, message);
    }

    public PickableItem() {
    }

    public void give() {
        this.player.give(this.armorStand.getHelmet());
        this.player.give(this.armorStand.getChestplate());
        this.player.give(this.armorStand.getLeggings());
        this.player.give(this.armorStand.getBoots());
        this.player.give(this.armorStand.getItemInHand());
        this.armorStand.remove();

        if (this.message) {
            for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld() == this.getPlayer().getWorld()).collect(Collectors.toList())) {
                player.sendMessage(this.player.getDisplayName() + " " + ChatColor.YELLOW + "has obtained " + this.itemStack.getItemMeta().getDisplayName() + ChatColor.YELLOW + "!");
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!cooldowns.containsKey(e.getPlayer()))
            cooldowns.put(e.getPlayer(), new Cooldown<>());
        if (Functions.cooldown(e.getPlayer(), cooldowns.get(e.getPlayer()), 200, false)) return;

        if (e.getPlayer().getNearbyEntities(1, 1, 1).stream().anyMatch(e1 -> e1.getScoreboardTags().contains("PickableItem"))) {
            Entity entity = null;
            for (Entity loop : e.getPlayer().getNearbyEntities(1, 1, 1).stream().filter(e2 -> e2.getScoreboardTags().contains("PickableItem")).collect(Collectors.toList())) {
                entity = loop;
                break;
            }

            if (entity != null) {
                PickableItem pickableItem = pickableItems.get(entity.getUniqueId());
                if (pickableItem.getPlayer() != null && pickableItem.getPlayer().getPlayer() != e.getPlayer()) return;

                pickableItem.give();
            }
        }
    }
}
