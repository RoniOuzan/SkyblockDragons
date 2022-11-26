package me.maxiiiiii.skyblockdragons.player.accessorybag;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.PowerStoneMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirements;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum PowerStone {
    TEST(new Stats(0, 0.35, 0.3, 0.075, 0, 0, 0.1, 0, 0.05, 0), new Stats(0, 10, 0, 0, 0, 0), new ItemStack(Material.STONE), new SkillRequirement(SkillType.COMBAT, 3)),

    WARRIOR(new Stats(0, 0.35, 0.3, 0.075, 0, 0, 0.1, 0, 0.05, 0), new ItemStack(Material.IRON_SWORD), true),
    PROTECTED(new Stats(0, 0, 0.15, 0.025, 0, 0, 0.2, 0.3, 0, 0.1), new ItemStack(Material.IRON_CHESTPLATE), true),
    DISCIPLINED(new Stats(0, 0.25, 0.25, 0.1, 0, 0, 0.15, 0.15, 0.025, 0.1), new ItemStack(Material.DIAMOND), true),
    ROBUST(new Stats(0, 0.1, 0.05, 0.05, 0, 0, 0.25, 0.25, 0.05, 0), new ItemStack(Material.APPLE), true),
    WARLOCK(new Stats(0, 0.1, 0.1, 0.075, 0, 0, 0.1, 0.1, 0.05, 0.4), new ItemStack(Material.INK_SACK, 1, (short) 4), true),

    NONE(new Stats(), new ItemStack(Material.BARRIER), false),
    ;

    private final Stats stats;
    private final Stats uniqueStats;
    private final ItemStack itemStack;
    private final boolean isStarter;
    private final Requirements requirements;

    PowerStone(Stats stats, Stats uniqueStats, ItemStack itemStack, boolean isStarter, Requirement... requirements) {
        this.stats = stats;
        this.uniqueStats = uniqueStats;
        this.itemStack = itemStack;
        this.isStarter = isStarter;
        this.requirements = new Requirements(requirements);
    }

    PowerStone(Stats stats, Stats uniqueStats, ItemStack itemStack, Requirement... requirements) {
        this(stats, uniqueStats, itemStack, false, requirements);
    }

    PowerStone(Stats stats, ItemStack itemStack , Requirement... requirements) {
        this(stats, new Stats(), itemStack, requirements);
    }

    PowerStone(Stats stats, ItemStack itemStack, boolean isStarter, Requirement... requirements) {
        this(stats, new Stats(), itemStack, isStarter, requirements);
    }

    public Stats getStats(PlayerSD player) {
        if (player == null) return stats;

        Stats stats = new Stats(this.getStats().toList());
        for (Stat stat : stats) {
            double amount = stat.get();
            double magical = player.getItems().getAccessoryBag().getMagicalPower();
            stat.set(Math.round(magical * (-0.0002 * amount * magical + amount))); // m(-0.0002sm + s) https://www.desmos.com/calculator/kmiikc5qum
        }
        return stats;
    }

    public String getName() {
        return Functions.setTitleCase(this.name().replace("_", " "));
    }

    public ItemStack getItemStack(PlayerSD player) {
        List<String> lores = new ArrayList<>();
        lores.add(ChatColor.DARK_GRAY + (this.isStarter() ? "Starter Stone" : "Advanced Stone"));
        lores.add("");
        lores.add(ChatColor.GRAY + "Stats");
        lores.addAll(this.getStats(player).stream().filter(s -> !s.isEmpty()).map(Stat::toAddLore).collect(Collectors.toList()));
        if (!this.getUniqueStats().isEmpty()) {
            lores.add("");
            lores.add(ChatColor.GRAY + "Unique Stats");
            lores.addAll(this.getUniqueStats().stream().filter(s -> !s.isEmpty()).map(Stat::toAddLore).collect(Collectors.toList()));
        }
        return Functions.createItem(this.itemStack.getType(), 1, this.itemStack.getDurability(), ChatColor.GREEN + this.getName(), lores);
    }

    public int compare(PowerStone o) {
        if (!this.isStarter() && o.isStarter()) return 1;
        if (this.isStarter() && !o.isStarter()) return -1;
        return this.getName().compareTo(o.getName());
    }

    public static Set<PowerStone> getStarterPowerStones() {
        return Arrays.stream(PowerStone.values()).filter(PowerStone::isStarter).collect(Collectors.toSet());
    }

    public static Set<PowerStone> getLearnPowerStones() {
        return Arrays.stream(PowerStone.values()).filter(p -> !p.isStarter() && p != NONE).collect(Collectors.toSet());
    }

    public static int getRarityMagicalPower(Rarity rarity) {
        switch (rarity) {
            case COMMON:
            case SPECIAL:
                return 3;
            case UNCOMMON:
                return 5;
            case RARE:
                return 8;
            case EPIC:
                return 12;
            case LEGENDARY:
                return 16;
            case MYTHIC:
                return 22;
            case DIVINE:
                return 30;
            default:
                return 0;
        }
    }

    public static PowerStone valueOf(ItemMaterial material) {
        if (!(material instanceof PowerStoneMaterial)) return PowerStone.NONE;

        for (PowerStone powerStone : values()) {
            if (((PowerStoneMaterial) material).getPowerStone() == powerStone) return powerStone;
        }
        return PowerStone.NONE;
    }
}
