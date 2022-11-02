package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damage.suppliers.FerocitySupplier;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;
import org.bukkit.entity.Projectile;

@Getter
public class ProjectileEntityDamageEntity extends RangeEntityDamageEntity {
    private final Projectile projectile;

    public ProjectileEntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier isFerocity, Projectile projectile) {
        super(attacker, victim, isFerocity);
        this.projectile = projectile;
    }

    public ProjectileEntityDamageEntity(EntitySD attacker, EntitySD victim, Projectile projectile) {
        this(attacker, victim, new FerocitySupplier(), projectile);
    }
}
