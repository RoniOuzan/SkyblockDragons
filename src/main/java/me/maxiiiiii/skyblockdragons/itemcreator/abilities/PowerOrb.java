package me.maxiiiiii.skyblockdragons.itemcreator.abilities;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class PowerOrb {
    public enum Type {
        RADIANT(600), MANA_FLUX(600), OVERFLUX(1200), PLASMA(1200);

        private final long standFor;

        Type(long standFor) {
            this.standFor = standFor;
        }

        @Override
        public String toString() {
            return Functions.setTitleCase(this.name());
        }
    }

    private Type type;

    public PowerOrb(Type type, Location location) {
        this.type = type;

        ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
//        ItemStack head = new Item(Functions.getItemMaterial(item));
//        stand.setHelmet(head);
//        stand.setGravity(false);
//        stand.setInvulnerable(true);
//        stand.setVisible(false);
//        stand.setMarker(true);
        stand.addScoreboardTag("PowerOrb");
    }
}
