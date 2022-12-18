package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Data;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;

@Data
public class Entry<A, B> implements ConfigurationSerializable {
    private A a;
    private B b;

    public Entry(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", a);
        map.put("b", b);
        return map;
    }

    public static <A, B> Entry<A, B> deserialize(Map<String, Object> args) {
        return (Entry<A, B>) new Entry<>(args.get("a"), args.get("b"));
    }

    public static <A, B> List<Entry<A, B>> fromMap(Map<A, B> map) {
        List<Entry<A, B>> entries = new ArrayList<>();
        for (Map.Entry<A, B> entry : map.entrySet()) {
            entries.add(new Entry<>(entry.getKey(), entry.getValue()));
        }
        return entries;
    }

    public static <A, B> Map<A, B> toMap(Collection<Entry<A, B>> entries) {
        Map<A, B> map = new HashMap<>();
        for (Entry<A, B> entry : entries) {
            map.put(entry.getA(), entry.getB());
        }
        return map;
    }

    @SafeVarargs
    public static <A, B> Map<A, B> toMap(Entry<A, B>... entries) {
        Map<A, B> map = new HashMap<>();
        for (Entry<A, B> entry : entries) {
            map.put(entry.getA(), entry.getB());
        }
        return map;
    }
}
