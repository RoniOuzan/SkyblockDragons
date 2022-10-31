package me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public abstract class PlayerSDUpdateEvent extends PlayerSDEvent {
    public PlayerSDUpdateEvent(PlayerSD player) {
        super(player);
    }

    public boolean isThisItem(ItemMaterial material) {
        if (material instanceof ArmorMaterial) {
            return player.getPlayerItems().getArmor().stream().anyMatch(i -> i.getMaterial() == material);
        }
        return player.getPlayerItems().getToolMaterial() == material;
    }

    public boolean isNotThisItem(ItemMaterial material) {
        return !isThisItem(material);
    }
}
