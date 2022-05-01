package me.maxiiiiii.skyblockdragons.util.objects;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Time {
    public enum Unit {
        YEAR(1d / 31_536_000_000d), MONTH(1d / 2_592_000_000d), DAY(1d / 86_400_000), HOUR(1d / 3_600_000), MINUTE(1d / 60_000), SECOND(1d / 1_000), TICK(1d / 50), MILLISECOND(1), NANOSECOND(1_000);

        private final double MULTIPLAYER;

        Unit(double multiplier) {
            this.MULTIPLAYER = multiplier;
        }

        public String toString(double time) {
            return this + (time > 1 ? "" : "s");
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    private long now;

    public Time() {
        this.now = System.currentTimeMillis();
    }

    public double now() {
        return this.now(Unit.MILLISECOND);
    }

    public double now(Unit unit) {
        long time = System.currentTimeMillis() - this.now;
        return this.convert(time, unit);
    }

    public double convert(Unit unit) {
        return this.convert(this.now, unit);
    }

    public double convert(long time, Unit unit) {
        return time * unit.MULTIPLAYER;
    }

    public void setNow() {
        this.now = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        double now = this.now;
        for (Unit unit : Arrays.stream(Unit.values()).filter(u -> u.MULTIPLAYER < 1 / 100d).collect(Collectors.toList())) {
            double time = Math.floor(now * unit.MULTIPLAYER);
            if (time != 0) {
                now -= time / unit.MULTIPLAYER;
                builder.append(time).append(" ").append(unit.toString(time)).append(" ");
            }
        }
        return builder.toString();
    }
}
