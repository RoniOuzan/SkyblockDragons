package me.maxiiiiii.skyblockdragons.commands;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import lombok.var;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickCommand;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickSubCommand;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.witherisland.EntityWither;
import me.maxiiiiii.skyblockdragons.events.listeners.JoinQuitListener;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.crystals.Crystals;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.modifiers.CrystalModifier;
import me.maxiiiiii.skyblockdragons.item.modifiers.HotPotatoModifier;
import me.maxiiiiii.skyblockdragons.item.modifiers.ItemModifiers;
import me.maxiiiiii.skyblockdragons.player.food.GoldenMelonFood;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.TextMessage;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.world.worlds.witherisland.WitherIsland.wither;

public class SkyblockDragonsTestCommand extends QuickCommand {
    public SkyblockDragonsTestCommand() {
        addSubCommand(new QuickSubCommand("skull-rain", (player, args) -> {
            EntityWither type = (EntityWither) wither.material;
            type.skullRainAbility(wither, player);
        }));
        addSubCommand(new QuickSubCommand("super-skull", (player, args) -> {
            EntityWither type = (EntityWither) wither.material;
            type.superSkull(wither, player);
        }));
        addSubCommand(new QuickSubCommand("skull-everywhere", (player, args) -> {
            EntityWither type = (EntityWither) wither.material;
            type.skullEverywhere(wither);
        }));
        addSubCommand(new QuickSubCommand("dash", (player, args) -> {
            EntityWither type = (EntityWither) wither.material;
            type.dashToPlayer(wither, player);
        }));
        addSubCommand(new QuickSubCommand("set-phase", (player, args) -> {
            if (args.length < 2){
                player.sendMessage("invalid number!");
                return;
            }
            int phase = Integer.parseInt(args[1]);
            EntityWither type = (EntityWither) wither.material;
            type.phase = phase;
            player.sendMessage(String.format("set phase to %s", phase));
        }));
        addSubCommand(new QuickSubCommand("blue-explode", (player, args) -> {
            if (args.length < 2){
                player.sendMessage("invalid number!");
                return;
            }
            int phase = Integer.parseInt(args[1]);
            EntityWither type = (EntityWither) wither.material;
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
            player.sendMessage("ERROR ERROR");
//            EntitySpawn spawn = new EntitySpawn(player.getLocation(), "NULL");
//            Variables.set("Spawns", 0, spawn);
//            EntitySpawn spawnAtFile = Variables.get("Spawns", 0);
//            player.sendMessage("Spawn at file: " + spawnAtFile);
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
            Crystals crystals = modifiers.getCrystals();
            player.sendMessage("Crystals Before: " + crystals);
            crystals.add(CrystalType.ARES, 1);
            player.sendMessage("Crystals Put: " + crystals);
            var item = new Item(player, material1, modifiers, new CrystalModifier(crystals), new HotPotatoModifier(5));
            player.getEquipment().setItemInMainHand(item);

            modifiers = ItemModifiers.getModifiers(item);
            crystals = modifiers.getCrystals();
            int hotPotato = modifiers.getHotPotato();
            player.sendMessage("Crystals After: " + crystals);
            player.sendMessage("Hot Potato: " + hotPotato);
        }));
        addSubCommand(new QuickSubCommand("id", (player, args) -> {
            Item item = player.getItems().getTool();
            new TextMessage().append("Spigot Material: " + item.getType()).setClickAsSuggestCmd(item.getType().name()).save().append(" ItemMaterial: " + item.getMaterial().name()).setClickAsSuggestCmd(item.getMaterial().name()).save().send(player);
        }));
        addSubCommand(new QuickSubCommand("testFood", (player, args) -> {
            player.setFood(new GoldenMelonFood(30, 1));
        }));
        addSubCommand(new QuickSubCommand("sendFood", (player, args) -> {
            player.sendMessage(player.getFood());
        }));
        addSubCommand(new QuickSubCommand("target-block", (player, args) -> {
            Block block = player.getTargetBlock(null,50);
            player.sendMessage(block.getType());
        }));
        addSubCommand(new QuickSubCommand("craft", (player, args) -> {
            if (args.length < 2){
                player.sendMessage(ChatColor.RED + "Usage: /sdtest craft <recipe>");
                return;
            }
            String itemName = args[1];
            List<Recipe> recipes = Recipe.getRecipesFor(itemName);
            for (Recipe recipe : recipes) {
                if (Recipe.craftRecipe(player, recipe)){
                    player.sendMessage("Craft Worked! used recipe: " + recipe.name());
                }
            }
        }));
        addSubCommand(new QuickSubCommand("can-craft", (player, args) -> {
            player.sendMessage(Recipe.getRecipesCanCraft(player, 3));
        }));
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1){
            return super.onTabComplete(sender, command, label, args);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("craft")) {
            return Recipe.abcSorted.stream().map(Recipe::name).filter(s -> s.startsWith(args[1])).collect(Collectors.toList());
        }

        return null;
    }
}
