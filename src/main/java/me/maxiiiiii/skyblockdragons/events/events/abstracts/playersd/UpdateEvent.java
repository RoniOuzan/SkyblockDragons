package me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.EntitySDEvent;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;

public abstract class UpdateEvent extends EntitySDEvent {
    public UpdateEvent(EntitySD entity) {
        super(entity);
    }

    public boolean isThisItemVictim(ItemMaterial material) {
        if (material instanceof ArmorMaterial) {
            return entity.getItems().getArmor().stream().anyMatch(i -> i.getMaterial() == material);
        }
        return entity.getItems().getToolMaterial() == material;
    }

    public boolean isNotThisItem(ItemMaterial material) {
        return !isThisItemVictim(material);
    }
}
