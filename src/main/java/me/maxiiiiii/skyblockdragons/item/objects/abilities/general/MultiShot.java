package me.maxiiiiii.skyblockdragons.item.objects.abilities.general;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.util.Functions.getVector;

public class MultiShot extends ItemAbility implements Listener {
    private static final double DEFAULT_MAX_POWER_TIME = 1;
    private static final double POW = 3;
    private static final double MIN = 0.05;
    private static final double POWER_MULTIPLIER = 3;

    private final String itemID;
    private final int amountOfArrows;
    private final double spread;
    private final double power;
    private final double maxPowerTime;

    public MultiShot(String itemID, String description, int amountOfArrows, double power, double spread, double maxPowerTime) {
        super(AbilityAction.SHOOT,
                "MultiShot",
                "Shoots " + ChatColor.AQUA + amountOfArrows + ChatColor.GRAY + " arrows at once!" + (description.isEmpty() ? "" : " NEW_LINE " + description)
        );
        this.itemID = itemID;
        this.amountOfArrows = amountOfArrows;
        this.spread = Math.abs(spread);
        this.power = power;
        this.maxPowerTime = Math.pow(maxPowerTime, 1 / POW);
    }

    public MultiShot(String itemID, int amountOfArrows, double power, double maxPowerTime) {
        this(itemID, "", amountOfArrows, power, calculateSpread(amountOfArrows), maxPowerTime);
    }

    public MultiShot(String itemID, String description, int amountOfArrows, double power, double maxPowerTime) {
        this(itemID, description, amountOfArrows, power, calculateSpread(amountOfArrows), maxPowerTime);
    }

    public MultiShot(String itemID, int amountOfArrows, double power) {
        this(itemID, amountOfArrows, power, DEFAULT_MAX_POWER_TIME);
    }

    public MultiShot(String itemID, int amountOfArrows) {
        this(itemID, "", amountOfArrows, 1, calculateSpread(amountOfArrows), DEFAULT_MAX_POWER_TIME);
    }

    public double getAngleDifference() {
        if (amountOfArrows <= 1) return 0;
        return (spread * 2) / (amountOfArrows - 1);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (!Functions.getId(e.getItem()).equals(itemID)) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

        ((AbilityRunnable) getAbilityOfPlayer(player).getRunnable()).lastTimeUsed = SkyblockDragons.getCurrentTimeInSeconds();
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return new AbilityRunnable();
    }

    private class AbilityRunnable implements PlayerAbilityRunnable {

        private double lastTimeUsed;

        public AbilityRunnable() {
            this.lastTimeUsed = 0;
        }

        @Override
        public void run(PlayerAbilityUsage e) {
            PlayerSD player = e.getPlayer();
            if (maxPowerTime == 0) {
                ((EntityShootBowEvent) e.getEvent()).getProjectile().remove();
                shoot(player, power * POWER_MULTIPLIER);
                return;
            }

            double diff = Math.min(SkyblockDragons.getCurrentTimeInSeconds() - lastTimeUsed, maxPowerTime);
            if (diff >= MIN) {
                lastTimeUsed = SkyblockDragons.getCurrentTimeInSeconds();
                diff = Math.pow(diff, POW);
                ((EntityShootBowEvent) e.getEvent()).getProjectile().remove();
                double multiplier = Math.max(diff / Math.pow(maxPowerTime, POW) * power * POWER_MULTIPLIER, 0.5);
                shoot(player, multiplier);
            }
        }

        private void shoot(PlayerSD player, double multiplier) {
            if (amountOfArrows == 1) {
                Vector vector = player.getLocation().getDirection().multiply(multiplier);
                Arrow arrow = player.launchProjectile(Arrow.class, vector);
                arrow.addScoreboardTag("UNEVENTABLE");
            } else {
                double angleDifference = getAngleDifference();
                for (double i = -spread; i <= spread; i += angleDifference) {
                    Vector vector = getVector(player, i, 0, multiplier);
                    Arrow arrow = player.launchProjectile(Arrow.class, vector);
                    arrow.addScoreboardTag("UNEVENTABLE");
                }
            }
        }
    }

    private static double calculateSpread(double amountOfArrows) {
        return Math.sqrt(15 * (amountOfArrows - 1));
    }
}
