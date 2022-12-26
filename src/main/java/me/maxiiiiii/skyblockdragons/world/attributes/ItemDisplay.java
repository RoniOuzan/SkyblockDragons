package me.maxiiiiii.skyblockdragons.world.attributes;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.SingleShopMenu;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Location;
import org.bukkit.Material;

@Getter
public class ItemDisplay implements WorldAttribute {
    private final Location location;
    private final Item item;
    private final double cost;

    public ItemDisplay(Location location, Item item, double cost) {
        this.cost = cost;
        this.location = location;
        this.item = item;
    }

    public void display(PlayerSD player) {
        new SingleShopMenu(player, "Item - " + this.item.getMaterial().getName(), this.item, this.cost);
    }

    @Override
    public void onCreate() {
        HologramsAPI.createHologram(SkyblockDragons.plugin, this.location.clone().add(0.5, 1, 0.5)).appendItemLine(this.item);
        this.location.getBlock().setType(Material.BARRIER);
    }
}
