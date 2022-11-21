package me.maxiiiiii.skyblockdragons.entity.types.other;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.entity.EntityType;

public class NullEntity extends EntityMaterial {
    public NullEntity() {
        super(EntityType.SKELETON, "Null", 0, 1, 0, 0, 0, new Equipment(), 0, 1, 0, 0);
    }
}
