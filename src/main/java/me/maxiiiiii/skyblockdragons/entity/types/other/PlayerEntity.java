package me.maxiiiiii.skyblockdragons.entity.types.other;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.entity.EntityType;

public class PlayerEntity extends EntityMaterial {
    public PlayerEntity() {
        super(EntityType.PLAYER, "", -1, -1, -1, -1, -1, new Equipment(), -1, 0, -1, -1);
    }

    @Override
    public void onSpawn(EntitySD entity) {

    }
}
