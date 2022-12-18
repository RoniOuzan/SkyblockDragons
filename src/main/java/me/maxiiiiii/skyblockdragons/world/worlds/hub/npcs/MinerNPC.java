package me.maxiiiiii.skyblockdragons.world.worlds.hub.npcs;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.Enchant;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.modifiers.EnchantModifier;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.quests.mining.MineQuest;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.util.objects.reward.ItemReward;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.npc.interact.InteractableNPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.*;
import me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.group.ParallelGroupInteraction;
import me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.group.SequentialGroupInteraction;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinerNPC extends InteractableNPC {
    private final List<PlayerSD> gave;
    private final List<PlayerSD> upgraded;

    public MinerNPC(Location location) {
        super("MIKE", "Mike", location, EntityType.PLAYER, "Miner");

        this.gave = new ArrayList<>();
        this.upgraded = new ArrayList<>();

        addInteraction(p -> new SequentialGroupInteraction(this,
                new TalkWaitInteraction(this, "Hello there,", 1.5),
                new TalkWaitInteraction(this, "My name is Mike.", 1.5),
                new TalkWaitInteraction(this, "When i see you, i see someone that wants to join my adventure in this dangerous world.", 1.5),
                new TalkWaitInteraction(this, "I'm not sure if you can do this.", 1.5),
                new ParallelGroupInteraction(this,
                        new TalkWaitInteraction(this, "Take this Cobblestone Pickaxe.", 1.5),
                        new RewardInteraction(this, new ItemReward(new Item(p, Items.get("COBBLESTONE_PICKAXE"), new EnchantModifier(new Enchant(EnchantType.EFFICIENCY, 2)))))
                ),
                new TalkWaitInteraction(this, "Now prove you are worth to join to my adventure, mine " + ChatColor.GREEN + "20 " + ChatColor.WHITE + "coals and " + ChatColor.GREEN + "64 " + ChatColor.WHITE + "cobblestones.", 2),
                new StartQuestInteraction(this, new Quest(p))
        ));
        addInteraction(p -> new ConditionInteraction(this, () -> p.hasItem(Items.get("COBBLESTONE"), 64) && p.hasItem(Items.get("COAL"), 20) && p.getQuests().isCompleted(Quest.class),
                new SequentialGroupInteraction(this,
                        new RunInteraction(this, () -> {
                            this.gave.add(p);
                            p.removeItems(Items.get("COBBLESTONE"), 64);
                            p.removeItems(Items.get("COAL"), 20);
                        }),
                        new TalkWaitInteraction(this, "Wow, you really wants to join to my adventure.", 1.5),
                        new TalkWaitInteraction(this, "One last thing before we go.", 1.5),
                        new TalkWaitInteraction(this, "I think you need a better pickaxe.", 1.5),
                        new TalkWaitInteraction(this, "But i don't have the required materials to upgrade your pickaxe.", 1.5),
                        new TalkWaitInteraction(this, "Give me " + ChatColor.GREEN  + "40 " + ChatColor.WHITE + "Coals to upgrade your pickaxe", 1.5)

                ),
                new ConditionInteraction(this, () -> p.getQuests().isCompleted(Quest.class),
                        new SequentialGroupInteraction(this,
                                new TalkWaitInteraction(this, "I see you have done it, you mined " + ChatColor.GREEN + "64 " + ChatColor.WHITE + "cobblestones and " + ChatColor.GREEN + "20 " + ChatColor.WHITE + "coals.", 1.5),
                                new TalkWaitInteraction(this, "But it looks like you left the materials on the ground.", 1.5),
                                new TalkWaitInteraction(this, "Go take them before its too late and give me.", 1.5)
                        ),
                        new SequentialGroupInteraction(this,
                                new TalkWaitInteraction(this, "I see you have not get it.", 1.5),
                                new TalkWaitInteraction(this, "Mine " + ChatColor.GREEN + "20 " + ChatColor.WHITE + "coals and " + ChatColor.GREEN + "64 " + ChatColor.WHITE + "cobblestones and give them to me.", 1.5)
                        )
                )
        ).setRepeatUntil(gave::contains));
        addInteraction(p -> new ConditionInteraction(this, () -> p.hasItem(Items.get("COBBLESTONE_PICKAXE"), 1) && p.hasItem(Items.get("COAL"), 40),
                new SequentialGroupInteraction(this,
                        new RunInteraction(this, () -> {
                            p.playSound(Sound.BLOCK_STONE_BUTTON_CLICK_ON);
                            this.upgraded.add(p);
                            p.removeItems(Items.get("COBBLESTONE_PICKAXE"), 1);
                            p.removeItems(Items.get("COAL"), 40);
                            p.give(new Item(p, Items.get("COAL_PICKAXE"), new EnchantModifier(new Enchant(EnchantType.EFFICIENCY, 3), new Enchant(EnchantType.FORTUNE, 1))));
                        }),
                        new TalkWaitInteraction(this, "Ok, now you have all you need.", 1.5),
                        new TalkWaitInteraction(this, "But be careful, the place we go is not for everyone.", 1.5),
                        new TalkWaitInteraction(this, "Come with me to the Deep Mines, it's right over there in the cave.", 1.5)
                ),
                new ConditionInteraction(this, () -> p.hasItem(Items.get("COBBLESTONE_PICKAXE"), 1),
                        new TalkWaitInteraction(this, "You don't have the " + ChatColor.GREEN + "40 " + ChatColor.WHITE + ", so I can't upgrade your pickaxe without it.", 1.5),
                        new ConditionInteraction(this, () -> p.hasItem(Items.get("COAL"), 40),
                                new TalkWaitInteraction(this, "Give me your Cobblestone Pickaxe that I gave you earlier, I will upgrade it and then we can go to our adventure.", 1),
                                new TalkWaitInteraction(this, "You don't have the " + ChatColor.GREEN + "40 " + ChatColor.WHITE + "coals and the Cobblestone Pickaxe, give me them because i can't upgrade your armor without it.", 1)
                        )
                )
        ).setRepeatUntil(upgraded::contains));
    }

    public static class Quest extends MineQuest {
        public Quest(PlayerSD player) {
            super(player, WorldSD.HUB.getRegion("Mines"),
                    new Entry<>(BlockMaterial.get("STONE"), 64),
                    new Entry<>(BlockMaterial.get("COAL_ORE"), 20)
            );
        }

        @Override
        public Map<String, Object> serialize() {
            Map<String, Object> map = new HashMap<>();
            map.put("Player", player);
            return map;
        }

        public static Quest deserialize(Map<String, Object> args) {
            return new Quest((PlayerSD) args.get("Player"));
        }
    }
}
