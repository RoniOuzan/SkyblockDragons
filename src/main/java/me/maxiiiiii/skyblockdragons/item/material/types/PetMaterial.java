package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.SoundUtil;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.util.particle.ParticlePacketUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class PetMaterial extends ItemMaterial implements ItemStatsAble, ItemRequirementAble {
    public static final PetMaterial NULL = new PetMaterial("Null", "", "", new Stats(), Arrays.asList(Rarity.NONE), Arrays.asList(new PetAbility("Null", "", new ArrayList<>(Arrays.asList(Rarity.SPECIAL)))), SkillType.COMBAT, new ParticlePacketUtil(Particle.REDSTONE, 1, 0, 0, 1f, 5));

    private final Stats stats;
    private final List<Rarity> rarities;
    private final List<PetAbility> abilities;
    private final SkillType skill;
    private final int maxLevel;
    private final ParticlePacketUtil[] particles;
    private final SoundUtil[] sounds;
    private final List<Requirement> requirements;

    public PetMaterial(String name, String id, String nbt, Stats stats, List<Rarity> rarities, List<PetAbility> abilities, SkillType skill, int maxLevel, Object... array) {
        super(itemID, Material.SKULL_ITEM, ItemFamily.NULL, name, ItemType.PET, Rarity.NONE, id, nbt, 0);
        this.stats = stats;
        this.rarities = rarities;
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

    public PetMaterial(String name, String id, String nbt, Stats stats, List<Rarity> rarities, List<PetAbility> abilities, SkillType skill, Object... array) {
        this(name, id, nbt, stats, rarities, abilities, skill, 100, array);
    }

    public static String stringWithMath(String string, int level) {
        String[] strings = string.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].contains("LEVEL")) continue;

            String value = strings[i];
            boolean percent = value.contains("%");
            value = value.replaceAll("%", "");
            value = value.replaceAll("LEVEL", level + "");
            value = Functions.stringCalculator(ChatColor.stripColor(value)) + "";

            strings[i] = ChatColor.GREEN + value + (percent ? "%" : "") + ChatColor.GRAY;
        }
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (String s : strings) {
            stringBuilder.append(" ").append(s);
        }
        string = stringBuilder.toString();
        return string.replaceAll(" {2}", "");
    }

    public static PetMaterial get(String name) {
        for (String key : Items.pets.keySet()) {
            if (key.equals(name)) {
                return Items.pets.get(key);
            }
        }
        return NULL;
    }
}
