package me.maxiiiiii.skyblockdragons.entity;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

@Data
public class EntitySpawn implements ConfigurationSerializable {
    private final Location location;
    private final EntityMaterial entity;
    private final boolean save;

    public EntitySpawn(Location location, EntityMaterial entity, boolean save) {
        this.location = location;
        this.entity = entity;
        this.save = save;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("location", location);
        map.put("entity", entity.name());
        return map;
    }

    public static EntitySpawn deserialize(Map<String, Object> map){
        return new EntitySpawn((Location) map.get("location"), EntityMaterial.get((String) map.get("entity")), true);
    }
}
