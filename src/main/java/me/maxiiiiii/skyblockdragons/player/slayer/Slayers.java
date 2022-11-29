package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.skill.events.PlayerGetSkillXpEvent;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.RevenantSlayer;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.SvenSlayer;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.TarantulaSlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

@Getter
public class Slayers implements Listener {
    private final PlayerSD player;

    private final RevenantSlayer revenant;
    private final TarantulaSlayer tarantula;
    private final SvenSlayer sven;

    private SlayerQuest quest;

    public Slayers(PlayerSD player) {
        this.player = player;
        this.revenant = new RevenantSlayer(player);
        this.tarantula = new TarantulaSlayer(player);
        this.sven = new SvenSlayer(player);

        this.quest = null;

        Bukkit.getPluginManager().registerEvents(this, SkyblockDragons.plugin);
    }

    public void startQuest(SlayerType slayerType, int level) {
        this.quest = new SlayerQuest(slayerType, level);
    }

    public void cancelQuest() {
        this.quest = null;
    }

    public Slayer get(SlayerType slayerType) {
        switch (slayerType) {
            case REVENANT:
                return this.revenant;
            case TARANTULA:
                return this.tarantula;
            case SVEN:
                return this.sven;
        }
        return null;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerGetSkillXP(PlayerGetSkillXpEvent e) {
        if (this.quest != null && e.getSkillType() == SkillType.COMBAT) {
            this.quest.giveXp(e.getFinalAmount());
            this.player.getScoreboardSD().update();
        }
    }

    @Override
    public String toString() {
        return "Slayers{" +
                "player=" + player +
                ", revenant=" + revenant +
                ", tarantula=" + tarantula +
                ", sven=" + sven +
                ", quest=" + quest +
                '}';
    }
}
