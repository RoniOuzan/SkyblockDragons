package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.RevenantSlayer;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.SvenSlayer;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.TarantulaSlayer;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.VoidgloomSlayer;
import org.bukkit.ChatColor;

@Getter
public class Slayers {
    private final PlayerSD player;

    private final RevenantSlayer revenant;
    private final TarantulaSlayer tarantula;
    private final SvenSlayer sven;
    private final VoidgloomSlayer voidgloom;

    private final SlayerQuest quest;

    public Slayers(PlayerSD player) {
        this.player = player;
        this.revenant = new RevenantSlayer(player);
        this.tarantula = new TarantulaSlayer(player);
        this.sven = new SvenSlayer(player);
        this.voidgloom = new VoidgloomSlayer(player);

        this.quest = new SlayerQuest(player);
    }

    public void startQuest(SlayerType slayerType, int tier) {
        if (player.getPurse() < Slayer.getCostToStart(tier)) {
            player.sendMessage(ChatColor.RED + "You don't have enough money to start the quest!");
            return;
        }

        this.quest.start(slayerType, tier);
    }

    public void cancelQuest() {
        this.quest.cancel();
    }

    public void complete() {
        this.quest.complete();
    }

    public Slayer get(SlayerType slayerType) {
        switch (slayerType) {
            case REVENANT:
                return this.revenant;
            case TARANTULA:
                return this.tarantula;
            case SVEN:
                return this.sven;
            case VOIDGLOOM:
                return this.voidgloom;
        }
        return null;
    }

    public void save() {
        this.revenant.save(player);
        this.tarantula.save(player);
        this.sven.save(player);
        this.voidgloom.save(player);
        this.quest.save();
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
