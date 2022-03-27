package me.maxiiiiii.skyblockdragons.itemcreator.abilities;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;

import static me.maxiiiiii.skyblockdragons.util.Functions.cooldown;
import static me.maxiiiiii.skyblockdragons.util.Functions.getId;
import static me.maxiiiiii.skyblockdragons.util.Functions.getSkull;
import static me.maxiiiiii.skyblockdragons.util.Functions.randomInt;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.*;

public class Bonzo_Staff implements Listener {
    private final Cooldown cooldown = new Cooldown();
    private final ArrayList<ItemStack> balloons = new ArrayList<>(Arrays.asList(
            getSkull(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), "b7685f9f-c378-41d8-a636-a07320b6c9ae", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJkZDExZGEwNDI1MmY3NmI2OTM0YmMyNjYxMmY1NGYyNjRmMzBlZWQ3NGRmODk5NDEyMDllMTkxYmViYzBhMiJ9fX0="),
            getSkull(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), "934a0bf5-884d-417a-bbb7-dcea0f933b0c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWVmMTYyZGVmODQ1YWEzZGM3ZDQ2Y2QwOGE3YmY5NWJiZGZkMzJkMzgxMjE1YWE0MWJmZmFkNTIyNDI5ODcyOCJ9fX0="),
            getSkull(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), "574beff9-8720-4157-a5c8-7482d5654432", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTdmMzgxYTIwYTljNjQwNDI4MDc3MDcwY2M3YmQ5NWQ2ODg1OTJkMTEwNGNjYmNkNzEzNjQ5YTQ5ZTQxZWJmYiJ9fX0="),
            getSkull(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), "20229109-922a-4330-962e-5d88c1be1b15", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2EyZGYzMTViNDM1ODNiMTg5NjIzMWI3N2JhZTFhNTA3ZGJkN2U0M2FkODZjMWNmYmUzYjJiOGVmMzQzMGU5ZSJ9fX0="),
            getSkull(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), "2b5ef2cd-deae-482b-8e1f-09bd4200088d", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA1MmJlMWMwNmE0YTMyNTEyOWQ2ZjQxYmI4NGYwZWExY2E2ZjlmNjllYmRmZmY0MzE2ZTc0MjQ1MWM3OWMyMSJ9fX0="),
            getSkull(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), "8cf45dd8-3421-4841-97b9-71d986a77b25", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg2OGU2YTVjNGE0NDVkNjBhMzA1MGI1YmVjMWQzN2FmMWIyNTk0Mzc0NWQyZDQ3OTgwMGM4NDM2NDg4MDY1YSJ9fX0="),
            getSkull(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), "88c464c2-79ef-4eb1-9a78-038b0ee83216", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYzODdmYzI0Njg5M2Q5MmE2ZGQ5ZWExYjUyZGNkNTgxZTk5MWVlZWUyZTI2M2IyN2ZmZjFiY2YxYjE1NGViNyJ9fX0="),
            getSkull(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), "97dfaf0e-5496-4b59-83e5-54a9dce3e61e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGY4NTUyMmVlODE1ZDExMDU4N2ZmZmM3NDExM2Y0MTlkOTI5NTk4ZTI0NjNiOGNlOWQzOWNhYTlmYjZmZjVhYiJ9fX0=")
    ));

    private final ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.RED, Color.ORANGE, Color.YELLOW, Color.LIME, Color.AQUA, Color.BLUE, Color.PURPLE, Color.FUCHSIA));
    public static final ArrayList<Entity> entities = new ArrayList<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("BONZO_STAFF")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        if (players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 0)) return;
        if (cooldown(player, cooldown, 100, false)) return;

        Location location = player.getLocation().clone().subtract(0, 0.5, 0);
        location.add(location.getDirection().multiply(1));

        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        int randomInt = randomInt(1, balloons.size()) - 1;
        stand.setHelmet(balloons.get(randomInt));
        stand.setGravity(false);
        stand.setVisible(false);
        stand.setMarker(true);
        stand.addScoreboardTag("Bonzo_Staff");
        entities.add(stand);

        new BukkitRunnable() {
            float i = 0;
            @Override
            public void run() {
                if (stand.isDead()) cancel();
                i++;

                Location newLocation = location.clone().add(location.getDirection().multiply(i));
                newLocation.setYaw(newLocation.getYaw() + (20f * i));
                stand.teleport(newLocation);

                if (stand.getLocation().clone().add(0, 1.5, 0).getBlock().getType().isSolid()) {
                    if (player.getLocation().distance(stand.getLocation()) <= 6) {
                        double vectorX = (player.getLocation().getX() - stand.getLocation().getX()) * 2;
                        if (vectorX > 2.5) vectorX = 2.5;
                        if (vectorX < -2.5) vectorX = -2.5;

                        double vectorZ = (player.getLocation().getZ() - stand.getLocation().getZ()) * 2;
                        if (vectorZ > 2.5) vectorZ = 2.5;
                        if (vectorZ < -2.5) vectorZ = -2.5;

                        Vector vector = new Vector(
                                vectorX,
                                0.5,
                                vectorZ
                        );
                        player.setVelocity(vector);
                    }
                    fireworkParticle(stand.getLocation().clone().add(0, 1.5, 0), colors);
                    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_BLAST, 1f, 5f);
                    stand.remove();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private static void fireworkParticle(Location location, ArrayList<Color> colors) {
        Firework fw = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(1);
        fwm.addEffect(FireworkEffect.builder().withColor(colors.get(randomInt(0, colors.size() - 1))).flicker(true).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

        NBTEntity nbt = new NBTEntity(fw);
        nbt.setInteger("LifeTime", 1);
    }
}
