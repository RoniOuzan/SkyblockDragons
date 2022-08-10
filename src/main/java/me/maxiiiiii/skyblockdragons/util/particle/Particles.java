package me.maxiiiiii.skyblockdragons.util.particle;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Particles {
    public static void circle(AbstractParticle particle, Location location, double radius, double amount, Object... objects) {
        for (double i = 0; i < 360; i += (360 / amount)) {
            Location newLocation = location.clone().add(
                    Math.cos(i) * -location.getDirection().getZ() * radius,
                    Math.sin(i) * radius,
                    Math.cos(i) * location.getDirection().getX() * radius
            );

            particle.spawn(newLocation, objects);
            runRunnables(i, newLocation, objects);
        }
    }

    public static void line(AbstractParticle particle, Location loc1, Location loc2, double gapBetween, Object... objects) {
        Vector vector = new Vector(
                loc2.getX() - loc1.getX(),
                loc2.getY() - loc1.getY(),
                loc2.getZ() - loc1.getZ()
        ).normalize();
        for (int i = 0; i < (int) (loc1.distance(loc2) / gapBetween); i++) {
            particle.spawn(loc1.clone().add(vector.clone().multiply(i * gapBetween)), objects);
            runRunnables(i, loc1, objects);
        }
    }

    public interface LineRunnable {
        void run(double amount, Location location);
    }

    public static void line(AbstractParticle particle, Location loc1, Location loc2, double gapBetween, int delay, Object... objects) {
        Vector vector = new Vector(
                loc2.getX() - loc1.getX(),
                loc2.getY() - loc1.getY(),
                loc2.getZ() - loc1.getZ()
        ).normalize();
        Functions.Loop((int) (loc1.distance(loc2) / gapBetween), delay, i -> {
            Location location = loc1.clone().add(vector.clone().multiply(i * gapBetween));
            particle.spawn(location, asPlayerList(objects));
            runRunnables(i, location, objects);
        });
    }

    public static void line(AbstractParticle particle, Location loc1, Location loc2, int delay, Object... objects) {
        line(particle, loc1, loc2, loc1.distance(loc2) / 100, delay, objects);
    }

    public static void square(AbstractParticle particle, Location location, Object... player) {
        List<Player> players = asPlayerList(player);

        Location loc = new Location(location.getWorld(), Math.floor(location.getX()) + 0.5, Math.floor(location.getY()) + 0.5, Math.floor(location.getZ()) + 0.5);
        line(particle, loc.clone().add(-0.5, -0.5, -0.5), loc.clone().add(0.5, -0.5, -0.5), 0.2, players);
        line(particle, loc.clone().add(-0.5, 0.5, -0.5), loc.clone().add(0.5, 0.5, -0.5), 0.2, players);
        line(particle, loc.clone().add(-0.5, -0.5, 0.5), loc.clone().add(0.5, -0.5, 0.5), 0.2, players);
        line(particle, loc.clone().add(-0.5, 0.5, 0.5), loc.clone().add(0.5, 0.5, 0.5), 0.2, players);

        line(particle, loc.clone().add(-0.5, -0.5, -0.5), loc.clone().add(-0.5, 0.5, -0.5), 0.2, players);
        line(particle, loc.clone().add(-0.5, -0.5, 0.5), loc.clone().add(-0.5, 0.5, 0.5), 0.2, players);
        line(particle, loc.clone().add(0.5, -0.5, -0.5), loc.clone().add(0.5, 0.5, -0.5), 0.2, players);
        line(particle, loc.clone().add(0.5, -0.5, 0.5), loc.clone().add(0.5, 0.5, 0.5), 0.2, players);

        line(particle, loc.clone().add(-0.5, -0.5, -0.5), loc.clone().add(-0.5, -0.5, 0.5), 0.2, players);
        line(particle, loc.clone().add(0.5, -0.5, -0.5), loc.clone().add(0.5, -0.5, 0.5), 0.2, players);
        line(particle, loc.clone().add(-0.5, 0.5, -0.5), loc.clone().add(-0.5, 0.5, 0.5), 0.2, players);
        line(particle, loc.clone().add(0.5, 0.5, -0.5), loc.clone().add(0.5, 0.5, 0.5), 0.2, players);
    }

    private static List<Player> asPlayerList(Object... player) {
        List<Player> players = Arrays.stream(player).filter(p -> p instanceof Player).map(p -> (Player) p).collect(Collectors.toList());
        Arrays.stream(player) .filter(p -> p instanceof List).map(p -> (List<Player>) p).forEach(players::addAll);
        return players;
    }

    private static void runRunnables(double amount, Location location, Object... runnables) {
        for (Object runnable : runnables) {
            if (runnable instanceof Runnable)
                ((Runnable) runnable).run();
            else if (runnable instanceof LineRunnable)
                ((LineRunnable) runnable).run(amount, location);
        }
    }
}
