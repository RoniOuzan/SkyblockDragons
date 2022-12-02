package me.maxiiiiii.skyblockdragons.player.skill.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.skill.SkillXpSource;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerGetSkillXpEvent extends PlayerSDEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final SkillType skillType;
    private final double baseAmount;
    private final double finalAmount;
    private final SkillXpSource<?> source;

    public PlayerGetSkillXpEvent(PlayerSD player, SkillType type, double baseAmount, double finalAmount, SkillXpSource<?> source) {
        super(player);
        this.skillType = type;
        this.baseAmount = baseAmount;
        this.finalAmount = finalAmount;
        this.source = source;
    }

    public AbstractSkill getSkill() {
        return this.player.getSkills().get(this.skillType);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
