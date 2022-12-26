package me.maxiiiiii.skyblockdragons.world.worlds.hub.npcs;

import me.maxiiiiii.skyblockdragons.inventory.ShopMenu;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class CombatShopNPC extends NPC {
    public CombatShopNPC(Location location) {
        super("Combat Shop", location, EntityType.VILLAGER);
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        new ShopMenu(e.getPlayer(), "Combat Shop",
                new Entry<>(new Item(Items.get("VILLAGE_TALISMAN")), 1000),
                new Entry<>(new Item(Items.get("VILLAGE_TALISMAN")), 1000)
        );
    }
}
