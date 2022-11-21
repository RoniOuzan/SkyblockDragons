package me.maxiiiiii.skyblockdragons.player.skill.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerGetSkillXpEvent extends PlayerSDEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final SkillType skillType;
    private final double baseAmount;
    private final Multiplier multiplier;

    public PlayerGetSkillXpEvent(PlayerSD player, SkillType type, double baseAmount) {
        super(player);
        this.skillType = type;
        this.baseAmount = baseAmount;
        this.multiplier = new Multiplier();
    }

    public AbstractSkill getSkill() {
        return this.player.getSkills().get(this.skillType);
    }

    public double applyMultipliers() {
        return this.multiplier.multiply(this.baseAmount);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
