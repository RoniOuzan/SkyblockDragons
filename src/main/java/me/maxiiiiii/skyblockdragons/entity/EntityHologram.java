package me.maxiiiiii.skyblockdragons.entity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

@Getter
public class EntityHologram {
    private final ArmorStand stand;
    private final EntitySD entity;

    private static final Vector adder = new Vector(0, 0.2, 0);

    public EntityHologram(EntitySD entity) {
        this.entity = entity;
        this.stand = (ArmorStand) entity.getWorld().spawnEntity(entity.getEyeLocation().add(adder), EntityType.ARMOR_STAND);
        this.stand.setVisible(false);
        this.stand.setMarker(true);
        this.stand.setGravity(false);
        this.stand.setInvulnerable(true);
        this.stand.addScoreboardTag("EntityHealth");
        this.stand.setCustomNameVisible(true);
        this.update();

        SkyblockDragons.entitiesToKill.add(this.stand);

        Functions.While(() -> !this.entity.isDead(), 1L, i -> this.stand.teleport(this.entity.getEyeLocation().add(adder)));
    }

    public void update() {
        this.stand.setCustomName(this.entity.getCustomName() + " " + ChatColor.GREEN + Functions.getShortNumber(this.entity.type.getHealth()) + StatType.HEALTH.getIcon());
    }

    public void remove() {
        SkyblockDragons.entitiesToKill.remove(this.stand);
        this.stand.remove();
    }
}
