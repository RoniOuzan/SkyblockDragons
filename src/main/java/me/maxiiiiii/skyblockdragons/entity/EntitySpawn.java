package me.maxiiiiii.skyblockdragons.entity;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

@Data
public class EntitySpawn implements ConfigurationSerializable {

    private Location location;
    private String entityID;

    public EntitySpawn(Location location, String entityID) {
        this.location = location;
        this.entityID = entityID;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("location", location);
        map.put("entityID", entityID);
        return map;
    }

    public static EntitySpawn deserialize(Map<String, Object> map){
        return new EntitySpawn((Location) map.get("location"), (String) map.get("entityID"));
    }

    public EntityMaterial getEntityMaterial() {
        return EntityMaterial.get(entityID);
    }
}
