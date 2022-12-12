package me.maxiiiiii.skyblockdragons.world.worlds.hub.npcs;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.Enchant;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.modifiers.EnchantModifier;
import me.maxiiiiii.skyblockdragons.util.objects.reward.ItemReward;
import me.maxiiiiii.skyblockdragons.world.npc.interact.InteractableNPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.RewardInteraction;
import me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.TalkInteraction;
import me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.group.ParallelGroupInteraction;
import me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.group.SequentialGroupInteraction;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class MinerNPC extends InteractableNPC {
    public MinerNPC(Location location) {
        super("Mike", location, EntityType.PLAYER, "Miner");

        addInteraction(new SequentialGroupInteraction(this,
                new TalkInteraction(this, "Hello there,", 2),
                new TalkInteraction(this, "My name is Mike.", 2),
                new TalkInteraction(this, "When i see you, i see someone that wants to adventure and be in danger.", 2),
                new ParallelGroupInteraction(this,
                        new TalkInteraction(this, "Take this, it will help you", 2),
                        new RewardInteraction(this, new ItemReward(p -> new Item(p, Items.get("COBBLESTONE_PICKAXE"), new EnchantModifier(new Enchant(2, EnchantType.EFFICIENCY)))))
                ),
                new TalkInteraction(this, "Now prove you are worth it, mine " + ChatColor.GREEN + "20 " + ChatColor.WHITE + "coals and " + ChatColor.GREEN + "64 " + ChatColor.WHITE + "cobblestones", 2)
        ));
    }
}
