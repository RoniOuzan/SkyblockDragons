package me.maxiiiiii.skyblockdragons.mining.objects;

import lombok.Data;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

@Data
public class BlockMaterials {
    private final Map<Material, Integer> material;

    public BlockMaterials(Object... materials) {
        this.material = new HashMap<>();
        Object last = materials[0];
        for (int i = 1; i < materials.length; i++) {
            Object current = materials[i];
            if (last instanceof Material && current instanceof Integer) {
                material.put((Material) last, (Integer) current);
            } else if (last instanceof Material) {
                material.put((Material) last, 0);
            }

            if (i == materials.length - 1 && current instanceof Material) {
                material.put((Material) current, 0);
            }

            last = current;
        }
    }

    public BlockMaterials(Material material) {
        this(material, 0);
    }

    public boolean contains(Material material) {
        return this.material.containsKey(material);
    }

    public boolean contains(Material material, int data) {
        return this.material.containsKey(material) && this.material.get(material) == data;
    }
}
