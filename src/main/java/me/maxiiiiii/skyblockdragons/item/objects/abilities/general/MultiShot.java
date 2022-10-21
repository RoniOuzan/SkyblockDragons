package me.maxiiiiii.skyblockdragons.item.objects.abilities.general;

import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.entity.Arrow;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class MultiShot extends ItemAbility implements Listener {
    private static final double DEFAULT_MAX_POWER_TIME = 1;

    private final int amountOfArrows;
    private final double spread;
    private final double power;
    private final double maxPowerTime;

    private double poweredAt = 0; // TODO: make it work with the power of the bow

    public MultiShot(int amountOfArrows, double power, double spread, double maxPowerTime) {
        super(AbilityAction.SHOOT,
                "MultiShot",
                "Shoots " + amountOfArrows + " arrows at a time!"
        );
        this.amountOfArrows = amountOfArrows;
        this.spread = Math.abs(spread);
        this.power = power;
        this.maxPowerTime = maxPowerTime;
    }

    public MultiShot(int amountOfArrows, double power, double maxPowerTime) {
        this(amountOfArrows, power, calculateSpread(amountOfArrows), maxPowerTime);
    }

    public MultiShot(int amountOfArrows, double power) {
        this(amountOfArrows, power, DEFAULT_MAX_POWER_TIME);
    }

    public MultiShot(int amountOfArrows) {
        this(amountOfArrows, 1, calculateSpread(amountOfArrows), DEFAULT_MAX_POWER_TIME);
    }

    public double getAngleDifference() {
        if (amountOfArrows <= 1) return 0;
        return (spread * 2) / (amountOfArrows - 1);
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return e -> {
            PlayerSD player = e.getPlayer();
            for (double i = -this.spread; i <= this.spread; i += this.getAngleDifference()) {
                Vector vector = Functions.rotateVector(i, player.getLocation().getDirection()).multiply(power);
                player.launchProjectile(Arrow.class, vector);
            }
        };
    }

    private static double calculateSpread(double amountOfArrows) {
        return Math.sqrt(3 * (amountOfArrows - 1));
    }
}
