package me.maxiiiiii.skyblockdragons.util;

import me.maxiiiiii.skyblockdragons.util.objects.ParticlePacketUtil;
import me.maxiiiiii.skyblockdragons.util.objects.ParticleUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class Particles {
    public static void circle(Location location, double radius, double amount, ParticleUtil particleUtil) {
        for (double i = 0; i < 360; i += (360 / amount)) {
            Location newLocation = location.clone().add(
                    Math.cos(i) * -location.getDirection().getZ() * radius,
                    Math.sin(i) * radius,
                    Math.cos(i) * location.getDirection().getX() * radius
            );

            particleUtil.spawn(newLocation);
        }
    }

    public static void line(Location loc1, Location loc2, ParticleUtil particle) {
        final double DISTANCE = loc1.distance(loc2) * 5;
        Location loc = loc1.clone();
        double t = 0;
        for (int i = 0; i < DISTANCE; i++) {
            t += 0.05;
            Vector direction = new Vector(loc2.getX() - loc1.getX(), loc2.getY() - loc1.getY(), loc2.getZ() - loc1.getZ());
            double x = direction.getX() * t;
            double y = direction.getY() * t + 1.5;
            double z = direction.getZ() * t;
            loc.add(x, y, z);
            particle.spawn(loc);

            loc.subtract(x, y, z);
        }
    }

    public static void line(Location loc1, Location loc2, List<Player> players, ParticlePacketUtil particle) {
        final double DISTANCE = loc1.distance(loc2) * 5;
        Location loc = loc1.clone();
        double t = 0;
        for (int i = 0; i < DISTANCE; i++) {
            t += 0.05;
            Vector direction = new Vector(loc2.getX() - loc1.getX(), loc2.getY() - loc1.getY(), loc2.getZ() - loc1.getZ());
            double x = direction.getX() * t;
            double y = direction.getY() * t + 1.5;
            double z = direction.getZ() * t;
            loc.add(x, y, z);
            particle.spawn(players, loc);

            loc.subtract(x, y, z);
        }
    }

    public static void square(Location location, List<Player> players, ParticlePacketUtil particle) {
        line(location.add(-0.5, -0.5, -0.5), location.add(0.5, -0.5, -0.5), players, particle);
        line(location.add(-0.5, 0.5, -0.5), location.add(0.5, 0.5, -0.5), players, particle);
        line(location.add(-0.5, -0.5, 0.5), location.add(0.5, -0.5, 0.5), players, particle);
        line(location.add(-0.5, 0.5, 0.5), location.add(0.5, 0.5, 0.5), players, particle);

        line(location.add(-0.5, -0.5, -0.5), location.add(-0.5, 0.5, -0.5), players, particle);
        line(location.add(-0.5, -0.5, 0.5), location.add(-0.5, 0.5, 0.5), players, particle);
        line(location.add(0.5, -0.5, -0.5), location.add(0.5, 0.5, -0.5), players, particle);
        line(location.add(0.5, -0.5, 0.5), location.add(0.5, 0.5, 0.5), players, particle);

        line(location.add(-0.5, -0.5, -0.5), location.add(-0.5, -0.5, 0.5), players, particle);
        line(location.add(0.5, -0.5, -0.5), location.add(0.5, -0.5, 0.5), players, particle);
        line(location.add(-0.5, 0.5, -0.5), location.add(-0.5, 0.5, 0.5), players, particle);
        line(location.add(0.5, 0.5, -0.5), location.add(0.5, 0.5, 0.5), players, particle);
    }
}
