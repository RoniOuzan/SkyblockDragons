package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.block.Block;

@Getter
public class SkillXpSourceType<T> {
    public static final SkillXpSourceType<PlayerSD> ADMIN = new SkillXpSourceType<>(PlayerSD.class);
    public static final SkillXpSourceType<EntitySD> ENTITY = new SkillXpSourceType<>(EntitySD.class);
    public static final SkillXpSourceType<Block> BLOCK = new SkillXpSourceType<>(Block.class);

    private final Class<T> clazz;

    SkillXpSourceType(Class<T> clazz) {
        this.clazz = clazz;
    }
}
