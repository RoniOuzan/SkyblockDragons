package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sword_Of_The_Flame implements Listener {
    private final double DISTANCE_BETWEEN_FLAMES = 1;
    private final int AMOUNT_OF_FLAMES = 5;
    private final int HEIGHT = 6;
    private final double SPEED = 1;
    private final long DURATION = 10_000; // 10s

    private final Map<PlayerSD, List<Flame>> uses = new HashMap<>();
    private final List<PlayerSD> pushed = new ArrayList<>();

    @EventHandler
    public void onPlayerUseAbility(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("SWORD_OF_THE_FLAME")) return;

        PlayerSD player = e.getPlayer();
        if (uses.containsKey(player)) return;

        List<Flame> flames = new ArrayList<>();
        for (int i = AMOUNT_OF_FLAMES / -2; i <= AMOUNT_OF_FLAMES / 2; i++) {
            for (int j = 0; j <= HEIGHT; j++) {
                Location location = player.getLocation().add(player.getLocation().getDirection().setY(0))
                        .add(Functions.rotateVector(90, player.getLocation().getDirection()).multiply(i * DISTANCE_BETWEEN_FLAMES).setY(0));
                location.add(0, j, 0);
                flames.add(new Flame(location, System.currentTimeMillis()));
            }
        }
        uses.put(player, flames);

        Functions.While(() -> uses.containsKey(player) && !pushed.contains(player), 5L, i -> uses.get(player).forEach(Flame::spawn));
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (!Functions.getId(e.getItem()).equals("SWORD_OF_THE_FLAME")) return;
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

        if (!uses.containsKey(player) || pushed.contains(player)) return;

        pushed.add(player);

        ItemAbility ability = ((ToolMaterial) Items.get(e.getItem())).getAbilities().get(0);
        List<Flame> flames = uses.get(player);
        Functions.While(() -> System.currentTimeMillis() - flames.get(0).usedAt < DURATION, 5L, i -> {
            for (Flame flame : flames) {
                flame.location.add(flame.location.getDirection().multiply(SPEED));
                flame.spawn();
                for (Entity entity : Functions.loopEntities(flame.location, 1.5)) {
                    player.makeDamage(entity, Damage.DamageType.MAGIC, 1, ability.getAbilityDamage(), ability.getAbilityScaling());
                }
            }
            uses.put(player, flames);
        }, i -> {
            uses.remove(player);
            pushed.remove(player);
        });
    }

    private static class Flame {
        private final Location location;
        private final long usedAt;

        public Flame(Location location, long usedAt) {
            this.location = location.setDirection(location.getDirection().setY(0));
            this.usedAt = usedAt;
        }

        public void spawn() {
            location.getWorld().spawnParticle(Particle.FLAME, location, 3, 0, 0, 0, 0);
        }
    }
}
