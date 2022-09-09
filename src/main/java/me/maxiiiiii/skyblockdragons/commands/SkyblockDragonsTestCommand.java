package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickCommand;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickSubCommand;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Laser;
import me.maxiiiiii.skyblockdragons.worlds.witherisland.WitherIsland;
import org.bukkit.Location;
import org.bukkit.entity.WitherSkull;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.worlds.witherisland.WitherIsland.wither;

public class SkyblockDragonsTestCommand extends QuickCommand {
    public SkyblockDragonsTestCommand() {
        addSubCommand(new QuickSubCommand("skull-rain", (player, args) -> {
            wither.skullRainAbility(wither.entitySD, player);
        }));
        addSubCommand(new QuickSubCommand("super-skull", (player, args) -> {
            wither.superSkull(wither.entitySD, player);
        }));
        addSubCommand(new QuickSubCommand("set-phase", (player, args) -> {
            if (args.length < 2){
                player.sendMessage("invalid number!");
                return;
            }
            int phase = Integer.parseInt(args[1]);
            wither.phase = phase;
            player.sendMessage(String.format("set phase to %s", phase));
        }));
        addSubCommand(new QuickSubCommand("blue-explode", (player, args) -> {
            if (args.length < 2){
                player.sendMessage("invalid number!");
                return;
            }
            int phase = Integer.parseInt(args[1]);
            wither.blueExplodeAbility(wither.entitySD, phase);
        }));
    }
}
