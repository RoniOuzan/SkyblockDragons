package me.maxiiiiii.skyblockdragons.player.skill.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.MultiplierUpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateSkillXpEvent extends MultiplierUpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final SkillType skill;
    private final double baseAmount;

    public UpdateSkillXpEvent(PlayerSD player, SkillType skill, double baseAmount) {
        super(player);
        this.skill = skill;
        this.baseAmount = baseAmount;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
