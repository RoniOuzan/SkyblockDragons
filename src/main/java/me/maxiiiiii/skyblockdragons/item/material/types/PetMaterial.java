package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetRarity;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.SoundUtil;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.util.particle.ParticlePacketUtil;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class PetMaterial extends ItemMaterial implements ItemStatsAble, ItemRequirementAble {
    public static final PetMaterial NULL = new NullPetMaterial();

    public static final double[] needXp = {0, 100, 110, 120, 130, 145, 160, 175, 190, 210, 230, 250, 275, 300, 330, 360, 400, 440, 490, 540, 600, 660, 730, 800, 880, 960, 1050, 1150, 1260, 1380, 1510, 1650, 1800, 1960, 2130, 2310, 2500, 2700, 2920, 3160, 3420, 3700, 4000, 4350, 4750, 5200, 5700, 6300, 7000, 7800, 8700, 9700, 10800, 12000, 13300, 14700, 16200, 17800, 19500, 21300, 23200, 25200, 27400, 29800, 32400, 35200, 38200, 38200, 41400, 44800, 48400, 52200, 56200, 60400, 64800, 69400, 74200, 79200, 84700, 90700, 97200, 104200, 111700, 119700, 128200, 137200, 146700, 156700, 167700, 179700, 192700, 206700, 221700, 237700, 254700, 272700, 291700, 311700, 333700, 357700, 383700, 411700, 476700, 516700, 561700, 611700, 666700, 726700, 791700, 861700, 936700, 1016700, 1101700, 1191700, 1286700, 1386700, 1496700, 1616700, 1746700, 1886700};

    private final Stats stats;
    private final List<PetRarity> abilities;
    private final SkillType skill;
    private final int maxLevel;
    private final ParticlePacketUtil[] particles;
    private final SoundUtil[] sounds;
    private final List<Requirement> requirements;

    public PetMaterial(String itemID, String name, Stats stats, List<PetRarity> abilities, SkillType skill, int maxLevel, Object... array) {
        super(itemID, Material.SKULL_ITEM, ItemFamily.NULL, name, ItemType.PET, Rarity.NONE);
        this.stats = stats;
        this.abilities = abilities;
        this.skill = skill;
        this.maxLevel = maxLevel;

        List<ParticlePacketUtil> particles = new ArrayList<>();
        List<SoundUtil> sounds = new ArrayList<>();
        List<Requirement> requirements = new ArrayList<>();
        for (Object object : array) {
            if (object instanceof ParticlePacketUtil) {
                particles.add((ParticlePacketUtil) object);
            } else if (object instanceof Particle) {
                particles.add(new ParticlePacketUtil((Particle) object));
            } else if (object instanceof SoundUtil) {
                sounds.add((SoundUtil) object);
            } else if (object instanceof Sound) {
                sounds.add(new SoundUtil((Sound) object, 1f));
            } else if (object instanceof Requirement) {
                requirements.add((Requirement) object);
            }
        }

        this.particles = particles.toArray(new ParticlePacketUtil[0]);
        this.sounds = sounds.toArray(new SoundUtil[0]);

        this.requirements = requirements;
    }

    public PetMaterial(String itemID, String name, Stats stats, List<PetRarity> abilities, SkillType skill, Object... array) {
        this(itemID, name, stats, abilities, skill, 100, array);
    }

    public double getNeedXp(int level) {
        int index = level + this.rarityToNeedXp();
        if (level >= this.getMaxLevel())
            return needXp[needXp.length - 1];
        return needXp[index];
    }

    private int rarityToNeedXp() {
        switch (this.rarity) {
            case COMMON:
                return 0;
            case UNCOMMON:
                return 6;
            case RARE:
                return 11;
            case EPIC:
                return 16;
            case LEGENDARY:
            case MYTHIC:
            case DIVINE:
            case SPECIAL:
                return 20;
        }
        return 0;
    }

    public static PetMaterial get(String name) {
        for (String key : Items.pets.keySet()) {
            if (key.equals(name)) {
                return Items.pets.get(key);
            }
        }
        return NULL;
    }

    @MaterialUnregisters
    private static class NullPetMaterial extends PetMaterial {
        public NullPetMaterial() {
            super("Null",
                    "Null",
                    new Stats(),
                    Arrays.asList(new PetRarity(Rarity.NONE)),
                    SkillType.COMBAT,
                    new ParticlePacketUtil(Particle.REDSTONE, 1, 0, 0, 1f, 5)
            );
        }
    }
}
