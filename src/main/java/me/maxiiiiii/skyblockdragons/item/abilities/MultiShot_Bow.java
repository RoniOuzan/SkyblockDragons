package me.maxiiiiii.skyblockdragons.item.abilities;

import lombok.Data;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.util.Functions.getVector;

@Data
public class MultiShot_Bow implements Listener {
    private final Cooldown<Player> cooldown = new Cooldown<>();
    private String bow;
    private int amount;
    private long maxPowerTime = 1000L;
    private double maxPower = 3;
    private int yawDiff = 5;

    public MultiShot_Bow(String bow, int amount) {
        this.bow = bow;
        this.amount = amount;
    }

    public MultiShot_Bow(String bow, int amount, long maxPowerTime) {
        this.bow = bow;
        this.amount = amount;
        this.maxPowerTime = maxPowerTime;
    }

    public MultiShot_Bow(String bow, int amount, long maxPowerTime, double maxPower, int yawDiff) {
        this.bow = bow;
        this.amount = amount;
        this.maxPowerTime = maxPowerTime;
        this.maxPower = maxPower;
        this.yawDiff = yawDiff;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).equals(bow)) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        cooldown.setCooldown(player, System.currentTimeMillis());
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntityType() == EntityType.ARROW) {
                Player player = (Player) e.getEntity().getShooter();
                ItemStack item = player.getEquipment().getItemInMainHand();
                Projectile projectile = e.getEntity();
                if (Functions.getId(item).equals(bow)){
                    Long diff = cooldown.getCurrentCooldown(player);
                    if (diff > maxPowerTime){
                        diff = maxPowerTime;
                    }
                    if (diff >= 50) {
                        cooldown.setCooldown(player, System.currentTimeMillis());
                        projectile.remove();
                        double multiplier = (double) diff/maxPowerTime * maxPower;
                        int yaw = 0;
                        for (int i = 0; i < amount; i++) {
                            Vector vector = getVector(player, yaw, 0, multiplier);
                            Arrow arrow = player.launchProjectile(Arrow.class, vector);
//                            player.sendMessage("shot arrow with yaw " + yaw);
                            arrow.addScoreboardTag(bow);
                            if (i % 2 == 0) {
                                yaw = Math.abs(yaw) + yawDiff;
                            }
                            else{
                                yaw = -yaw;
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntityType() == EntityType.ARROW) {
                if (e.getEntity().getScoreboardTags().contains("Terminator")) {
                    e.getEntity().remove();
                }
            }
        }
    }
}
