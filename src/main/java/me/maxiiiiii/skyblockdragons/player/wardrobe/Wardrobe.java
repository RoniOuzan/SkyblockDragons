package me.maxiiiiii.skyblockdragons.player.wardrobe;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import org.bukkit.Material;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.util.Functions.numberToItemSlot;

@Getter
@Setter
public class Wardrobe {
    private final PlayerSD player;
    private final ArrayList<WardrobeSlot> slots;
    private int equippedSlot;

    public Wardrobe(PlayerSD player) {
        this.player = player;
        ArrayList<WardrobeSlot> slots = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            slots.add(new WardrobeSlot(
                    i,
                    Variables.getItemStack(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 0) + (i < 9 ? 0 : 36)),
                    Variables.getItemStack(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 1) + (i < 9 ? 0 : 36)),
                    Variables.getItemStack(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 2) + (i < 9 ? 0 : 36)),
                    Variables.getItemStack(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 3) + (i < 9 ? 0 : 36))
            ));
        }
        this.slots = slots;
        this.equippedSlot = Variables.getInt(player.getUniqueId(), "EquippedSlot", 0, 0);
    }
    public WardrobeSlot getSlot(int slot) {
        return this.slots.get(slot);
    }

    public void save() {
        Variables.delete(player.getUniqueId(), "Wardrobe");
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (getSlot(i).getPeace(j).getType() == Material.STAINED_GLASS_PANE)
                    continue;

                Variables.set(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, j) + (i < 9 ? 0 : 36), getSlot(i).getPeace(j));
            }
        }
        Variables.set(player.getUniqueId(), "EquippedSlot", 0, equippedSlot);
    }
}
