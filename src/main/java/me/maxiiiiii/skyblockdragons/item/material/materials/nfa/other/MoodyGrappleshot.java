package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.other;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.FlyToTimer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vex;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class MoodyGrappleshot extends ToolMaterial {
    public MoodyGrappleshot() {
        super("MOODY_GRAPPLESHOT",
                Material.TRIPWIRE_HOOK,
                ItemFamily.ITEM,
                "Moody Grappleshot",
                ItemType.ITEM,
                Rarity.EPIC,
                "",
                new BigPull()
        );
    }

    public static class BigPull extends ItemAbility {
        public BigPull() {
            super(AbilityAction.RIGHT_CLICK,
                    "Big Pull",
                    "Throw a hook to grab and pull monsters." + " NEW_LINE " + "Pulling a monster multiplies its damage taken by " + ChatColor.RED + "2x " + ChatColor.GRAY + "and stops it from flying."
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();
                Location location = player.getLocation();
                ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                stand.setGravity(false);
                stand.setVisible(false);
                stand.setHelmet(new ItemStack(Material.ANVIL));
                stand.setMarker(true);
                stand.addScoreboardTag("MOODY_GRAPPLESHOT");

                Vex vex = (Vex) location.getWorld().spawnEntity(location, EntityType.VEX);
                vex.setGravity(false);
                vex.setAI(false);
                vex.setInvulnerable(true);
                vex.setSilent(true);
                vex.addScoreboardTag("MOODY_GRAPPLESHOT");
                ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
                PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.ATTACH_ENTITY);
                packet.getModifier().writeDefaults();
                packet.getIntegers().write(0, vex.getEntityId());
                packet.getIntegers().write(1, player.getEntityId());
                player.sendProtocolPacket(packet);
                vex.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));

                Functions.Loop(15, 1L, i-> {
                    i++;
                    i = i >= 25 ? 50 - i : i;
                    Location newLocation = location.clone().add(location.clone().getDirection().multiply(i));
                    stand.teleport(newLocation);
                }, i -> new FlyToTimer(stand, player, new Vector(), 10, false, () -> {
                    stand.remove();
                    vex.remove();
                }));
                Functions.While(() -> !stand.isDead(), 1L, i -> {
                    vex.teleport(stand.getLocation().add(0, 0.7, 0));

                    EulerAngle angle = new EulerAngle(
                            0,
                            stand.getHeadPose().getY() + 0.25,
                            0
                    );
                    stand.setHeadPose(angle);

                    Functions.loopEntities(stand.getLocation().add(0, 1, 0), 2).stream().filter(en -> en instanceof Creature && !en.getScoreboardTags().contains("MOODY_GRAPPLESHOT")).forEach(creature -> creature.teleport(stand.getLocation().add(0, 2, 0)));
                });

            };
        }
    }
}
