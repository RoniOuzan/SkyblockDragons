package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class Electro_Magnet {
    public static void electro_Magnet(Player p) {
        ItemStack item = p.getEquipment().getItemInMainHand();

        if (!Functions.getId(item).equals("ELECTRO_MAGNET")) return;

        List<Entity> entities1 = p.getNearbyEntities(10, 10, 10);
        for (Entity e : entities1) {
            if (e instanceof Monster || e instanceof Projectile) {
                Vector v = new Vector((e.getLocation().getX() - p.getLocation().getX()) / 5, (e.getLocation().getY() - p.getLocation().getY()) / 5 + 0.2, (e.getLocation().getZ() - p.getLocation().getZ()) / 5);
                e.setVelocity(v);
            }
        }
    }
}
