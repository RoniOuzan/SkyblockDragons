package me.maxiiiiii.skyblockdragons.util.objects;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Time implements ConfigurationSerializable {
    public enum Unit {
        YEAR(1d / 31_536_000_000d), MONTH(1d / 2_592_000), DAY(1d / 86_400), HOUR(1d / 3_600), MINUTE(1d / 60), SECOND(1d), TICK(50), MILLISECOND(1000), NANOSECOND(1_000_000);

        private final double MULTIPLIER;

        Unit(double multiplier) {
            this.MULTIPLIER = multiplier;
        }

        public double from(double amount) {
            return amount / this.MULTIPLIER;
        }

        public String toString(double time) {
            return this + (time > 1 ? "s" : "");
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public double now;

    public Time(double now) {
        this.now = now;
    }

    public Time() {
        this(System.currentTimeMillis());
    }

    public double now() {
        return this.now(Unit.MILLISECOND);
    }

    public double now(Unit unit) {
        double time = SkyblockDragons.getCurrentTimeInSeconds() - this.now;
        return this.convert(time, unit);
    }

    public double getAs(Unit unit) {
        return this.convert(this.now, unit);
    }

    public double convert(double time, Unit unit) {
        return time * unit.MULTIPLIER;
    }

    public void reset() {
        this.now = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return Time.toString(this);
    }

    public static String toString(Time time) {
        StringBuilder builder = new StringBuilder();
        double now = time.now();
        for (Unit unit : Arrays.stream(Unit.values()).filter(u -> u.MULTIPLIER < 1 / 100d).collect(Collectors.toList())) {
            int duration = (int) Math.floor(now * unit.MULTIPLIER);
            if (duration != 0) {
                now -= duration / unit.MULTIPLIER;
                builder.append(duration).append(" ").append(unit.toString(duration)).append(" ");
            }
        }
        return builder.toString();
    }

    public static String toString(double time) {
        StringBuilder builder = new StringBuilder();
        double now = time;
        for (Unit unit : Arrays.stream(Unit.values()).filter(u -> u.MULTIPLIER < 1 / 100d).collect(Collectors.toList())) {
            double duration = Math.floor(now * unit.MULTIPLIER);
            if (duration != 0) {
                now -= duration / unit.MULTIPLIER;
                builder.append(Functions.getInt(duration + "")).append(" ").append(unit.toString(duration)).append(" ");
            }
        }
        return builder.toString();
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> args = new HashMap<>();
        args.put("now", now);
        return args;
    }

    public static Time deserialize(Map<String, Object> args) {
        return new Time((double) args.get("now"));
    }
}
