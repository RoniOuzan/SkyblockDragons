package me.maxiiiiii.skyblockdragons.commands;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import lombok.var;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickCommand;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickSubCommand;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Laser;
import me.maxiiiiii.skyblockdragons.worlds.witherisland.WitherIsland;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
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
        addSubCommand(new QuickSubCommand("skull-everywhere", (player, args) -> {
            wither.skullEverywhere(wither.entitySD);
        }));
        addSubCommand(new QuickSubCommand("dash", (player, args) -> {
            wither.dashToPlayer(wither.entitySD, player);
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
        addSubCommand(new QuickSubCommand("spawn-dragon", (player, args) -> {
            var dragon = player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDER_DRAGON);
            NBTEntity nbtEntity = new NBTEntity(dragon);
            nbtEntity.setInteger("DragonPhase", 1);
            player.sendMessage("Dragon scam scam spawn!");
            player.sendMessage(nbtEntity);
            SkyblockDragons.logger.info(nbtEntity.toString());
        }));
        addSubCommand(new QuickSubCommand("spawn-sd", (player, args) -> {
            EntitySD dragon = new EntitySD(player.getLocation(), EntityMaterial.get("OLD_DRAGON"));
            NBTEntity nbtEntity = new NBTEntity(dragon.entity);
            nbtEntity.setInteger("DragonPhase", 1);
            dragon.entity.setAI(true);
            player.sendMessage("DragonSD scam scam spawn!");
            player.sendMessage(nbtEntity);
            SkyblockDragons.logger.info(nbtEntity.toString());
        }));
    }
}
