package me.maxiiiiii.skyblockdragons.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class SoundUtil {
    public Sound sound;
    public float pitch;

    public SoundUtil(Sound sound, float pitch) {
        this.sound = sound;
        this.pitch = pitch;
    }

    public void play(Location location) {
        for (Player value : Functions.getPlayerShowedPets()) {
            value.playSound(location, sound, (3f - (float) value.getEyeLocation().distance(location)) / 5, pitch);
        }
    }

    public void play(Location location, double volume) {
        for (Player value : Functions.getPlayerShowedPets()) {
            value.playSound(location, sound, (float) volume, pitch);
        }
    }
}
