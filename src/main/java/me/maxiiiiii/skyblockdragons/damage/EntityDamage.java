package me.maxiiiiii.skyblockdragons.damage;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;
import me.maxiiiiii.skyblockdragons.events.events.update.UpdateEntityDamageEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;
import org.bukkit.Bukkit;

@Getter
public class EntityDamage<A extends EntitySD, V extends EntitySD> {
    private static final Damage.DamageType DEFAULT_DAMAGE_TYPE = Damage.DamageType.NORMAL;

    private final A attacker;
    private final V victim;
    private final Damage.DamageType damageType;
    private final Equipment equipment;
    private final Multiplier multiplier;
    private boolean isCritHit = false;

    public EntityDamage(A attacker, V victim, Damage.DamageType damageType, Equipment equipment) {
        this.attacker = attacker;
        this.victim = victim;
        this.damageType = damageType;
        this.equipment = equipment;
        this.multiplier = new Multiplier();
    }

    public EntityDamage(A attacker, V victim, Damage.DamageType damageType) {
        this(attacker, victim, damageType, attacker.getItems());
    }

    public EntityDamage(A attacker, V victim, Equipment equipment) {
        this(attacker, victim, DEFAULT_DAMAGE_TYPE, equipment);
    }

    public EntityDamage(A attacker, V victim) {
        this(attacker, victim, DEFAULT_DAMAGE_TYPE, attacker.getItems());
    }

    public int getFinalDamage() { // TODO:
        if (attacker instanceof PlayerSD) {
            isCritHit = Functions.chanceOf(((PlayerSD) attacker).getStats().getCritChance().amount) && damageType.isCanBeCrit();
        }

        double defenseReducer = victim.getDefense() / (victim.getDefense() + 100);

        UpdateEntityDamageEvent event = new UpdateEntityDamageEvent(this);
        Bukkit.getPluginManager().callEvent(event);



        return 1;
    }
}
