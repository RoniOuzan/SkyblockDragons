package me.maxiiiiii.skyblockdragons.mining.objects;

import lombok.Data;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

@Data
public class BlockMaterials {
    private final List<Entry<Material, Integer>> material;

    public BlockMaterials(Object... materials) {
        this.material = new ArrayList<>();
        Object last = materials[0];
        for (int i = 1; i < materials.length; i++) {
            Object current = materials[i];
            if (last instanceof Material && current instanceof Integer) {
                material.add(new Entry<>((Material) last, (Integer) current));
            } else if (last instanceof Material) {
                material.add(new Entry<>((Material) last, 0));
            }

            if (i == materials.length - 1 && current instanceof Material) {
                material.add(new Entry<>((Material) current, 0));
            }

            last = current;
        }
    }

    public BlockMaterials(Material material) {
        this(material, 0);
    }

    public boolean contains(Material material) {
        return this.material.contains(new Entry<>(material, 0));
    }

    public boolean contains(Material material, int data) {
        return this.material.contains(new Entry<>(material, data));
    }
}
