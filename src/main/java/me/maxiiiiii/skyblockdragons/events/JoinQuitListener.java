package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e)  {
//        SkyblockDragons.logger.info("scam " + e.getPlayer().getName());

        Functions.Wait(1L, () -> {
            Player playerBukkit = e.getPlayer();
            if (SkyblockDragons.getPlayer(playerBukkit) == null) {
                new PlayerSD(playerBukkit);
            } else {
                SkyblockDragons.getPlayer(playerBukkit).update(playerBukkit);
            }

            PlayerSD player = SkyblockDragons.getPlayer(playerBukkit);

            if (!playerBukkit.hasPlayedBefore()){
                starterKit(player);
            }
            player.logLogin();
        });
    }

    public static void starterKit(PlayerSD player) {
        player.getEquipment().setBoots(new Item(Items.get("ENDER_BOOTS")));
        player.getEquipment().setLeggings(new Item(Items.get("ENDER_LEGGINGS")));
        player.getEquipment().setChestplate(new Item(Items.get("ENDER_CHESTPLATE")));
        player.getEquipment().setHelmet(new Item(Items.get("ENDER_HELMET")));
        player.give(new Item(Items.get("ASPECT_OF_THE_END")));
        player.give(new Item(Items.get("GOLDEN_SKELETON_BOW")));
        player.give(new Item(Items.get("DIAMOND_PICKAXE")));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        if (player.getPlayerPet().activePet >= 0) {
            player.getPlayerPet().petArmorStand.armorStand.remove();
            player.getPlayerPet().petArmorStand.hologram.delete();
        }
        player.logLogout();
        player.save();
//        SkyblockDragons.players.remove(e.getPlayer().getUniqueId());
    }


}
