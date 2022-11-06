package me.maxiiiiii.skyblockdragons.damage.types.entitydamage;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;
import org.bukkit.Bukkit;

@Getter
@Setter
public abstract class EntityDamage {
    protected EntitySD victim;
    protected double baseDamage;
    protected final Multiplier multiplier;

    public EntityDamage(EntitySD victim, double baseDamage) {
        this.victim = victim;
        this.baseDamage = baseDamage;
        this.multiplier = new Multiplier();
    }

    protected double calculateDamageFormula() {
        return baseDamage;
    }

    protected double getDamageReduction() {
        if (victim instanceof PlayerSD) {
            double defense = ((PlayerSD) victim).getStats().getDefense().get();
            return 1 - (defense / (defense + 100));
        }
        return 1;
    }

    public long getFinalDamage() {
        double damage = calculateDamageFormula();

        UpdateEntityDamageEvent event = new UpdateEntityDamageEvent(this);
        Bukkit.getPluginManager().callEvent(event);

        damage *= getDamageReduction();

        return (long) damage;
    }
}
