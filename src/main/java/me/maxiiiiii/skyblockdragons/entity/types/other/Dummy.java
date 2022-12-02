package me.maxiiiiii.skyblockdragons.entity.types.other;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.entity.EntityType;

public class Dummy extends EntityMaterial {
    public Dummy() {
        super(EntityType.ZOMBIE, "Dummy", Integer.MAX_VALUE, 500000, 0, 0, 0, new Equipment(), 0, 1, false, 0, 0);
    }
}
