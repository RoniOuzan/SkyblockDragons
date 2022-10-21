package me.maxiiiiii.skyblockdragons.events.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.HandlerList;

@Getter
public class ProjectileHitEvent extends org.bukkit.event.entity.ProjectileHitEvent {
    public static final HandlerList handlers = new HandlerList();

    protected final PlayerSD shooter;
    protected final ItemMaterial material;

    public ProjectileHitEvent(PlayerSD shooter, Projectile projectile, ItemMaterial material, Entity hitEntity, Block hitBlock) {
        super(projectile, hitEntity, hitBlock);
        this.shooter = shooter;
        this.material = material;
    }

    public ProjectileHitEvent(PlayerSD shooter, Projectile projectile, ItemMaterial material, Entity hitEntity) {
        this(shooter, projectile, material, hitEntity, null);
    }

    public ProjectileHitEvent(PlayerSD shooter, Projectile projectile, ItemMaterial material, Block hitBlock) {
        this(shooter, projectile, material, null, hitBlock);
    }

    public ProjectileHitEvent(PlayerSD shooter, Projectile projectile, ItemMaterial material) {
        this(shooter, projectile, material, null, null);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
