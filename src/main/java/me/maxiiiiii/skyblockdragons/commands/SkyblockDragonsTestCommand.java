package me.maxiiiiii.skyblockdragons.commands;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import lombok.var;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickCommand;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickSubCommand;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.EntitySpawn;
import me.maxiiiiii.skyblockdragons.entity.types.witherisland.EntityWither;
import me.maxiiiiii.skyblockdragons.events.JoinQuitListener;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.modifiers.CrystalModifier;
import me.maxiiiiii.skyblockdragons.item.modifiers.HotPotatoModifier;
import me.maxiiiiii.skyblockdragons.item.modifiers.ItemModifiers;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Laser;
import me.maxiiiiii.skyblockdragons.worlds.witherisland.WitherIsland;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkull;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Map;

import static me.maxiiiiii.skyblockdragons.worlds.witherisland.WitherIsland.wither;

public class SkyblockDragonsTestCommand extends QuickCommand {
    public SkyblockDragonsTestCommand() {
        addSubCommand(new QuickSubCommand("skull-rain", (player, args) -> {
            EntityWither type = (EntityWither) wither.type;
            type.skullRainAbility(wither, player);
        }));
        addSubCommand(new QuickSubCommand("super-skull", (player, args) -> {
            EntityWither type = (EntityWither) wither.type;
            type.superSkull(wither, player);
        }));
        addSubCommand(new QuickSubCommand("skull-everywhere", (player, args) -> {
            EntityWither type = (EntityWither) wither.type;
            type.skullEverywhere(wither);
        }));
        addSubCommand(new QuickSubCommand("dash", (player, args) -> {
            EntityWither type = (EntityWither) wither.type;
            type.dashToPlayer(wither, player);
        }));
        addSubCommand(new QuickSubCommand("set-phase", (player, args) -> {
            if (args.length < 2){
                player.sendMessage("invalid number!");
                return;
            }
            int phase = Integer.parseInt(args[1]);
            EntityWither type = (EntityWither) wither.type;
            type.phase = phase;
            player.sendMessage(String.format("set phase to %s", phase));
        }));
        addSubCommand(new QuickSubCommand("blue-explode", (player, args) -> {
            if (args.length < 2){
                player.sendMessage("invalid number!");
                return;
            }
            int phase = Integer.parseInt(args[1]);
            EntityWither type = (EntityWither) wither.type;
            type.blueExplodeAbility(wither, phase);
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
        addSubCommand(new QuickSubCommand("starter", (player, args) -> {
            JoinQuitListener.starterKit(player);
        }));
        addSubCommand(new QuickSubCommand("new-spawn", (player, args) -> {
            EntitySpawn spawn = new EntitySpawn(player.getLocation(), "NULL");
            Variables.set("Spawns", 0, spawn);
            EntitySpawn spawnAtFile = Variables.get("Spawns", 0);
            player.sendMessage("Spawn at file: " + spawnAtFile);
        }));
        addSubCommand(new QuickSubCommand("vars-save", (player, args) -> {
            Variables.save();
        }));
        addSubCommand(new QuickSubCommand("vars-load", (player, args) -> {
            Variables.load();
        }));
        addSubCommand(new QuickSubCommand("log-login", (player, args) -> {
            player.logLogin();
        }));
        addSubCommand(new QuickSubCommand("mining-fatigue", (player, args) -> {
            player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
            if (args.length < 2){
                player.sendMessage(ChatColor.RED + "Usage: /sdtest mining-fatigue <level>");
                return;
            }
            int level = Integer.parseInt(args[1]);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100000, level, true), true);

            player.sendMessage(String.format("You got Mining fatigue %s", player.getPotionEffect(PotionEffectType.SLOW_DIGGING)));
        }));
        addSubCommand(new QuickSubCommand("test-crystals", (player, args) -> {
            var item1 = player.getEquipment().getItemInMainHand();
            ItemMaterial material1 = Functions.getItemMaterial(item1);
            ItemModifiers modifiers = ItemModifiers.getModifiers(item1);
            Map<CrystalType, Short> crystals = modifiers.getCrystals();
            player.sendMessage("Crystals Before: " + crystals);
            crystals.put(CrystalType.POWER, (short) 1);
            player.sendMessage("Crystals Put: " + crystals);
            var item = new Item(player, material1, modifiers, new CrystalModifier(crystals), new HotPotatoModifier(5));
            player.getEquipment().setItemInMainHand(item);

            modifiers = ItemModifiers.getModifiers(item);
            crystals = modifiers.getCrystals();
            int hotPotato = modifiers.getHotPotato();
            player.sendMessage("Crystals After: " + crystals);
            player.sendMessage("Hot Potato: " + hotPotato);
        }));
    }
}
