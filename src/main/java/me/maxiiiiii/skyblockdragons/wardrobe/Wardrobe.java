package me.maxiiiiii.skyblockdragons.wardrobe;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class Wardrobe {
    private final ArrayList<WardrobeSlot> slots;

    public Wardrobe(ArrayList<WardrobeSlot> slots) {
        this.slots = slots;
    }

    public Wardrobe(WardrobeSlot slot1, WardrobeSlot slot2, WardrobeSlot slot3, WardrobeSlot slot4, WardrobeSlot slot5, WardrobeSlot slot6, WardrobeSlot slot7, WardrobeSlot slot8, WardrobeSlot slot9, WardrobeSlot slot10, WardrobeSlot slot11, WardrobeSlot slot12, WardrobeSlot slot13, WardrobeSlot slot14, WardrobeSlot slot15, WardrobeSlot slot16, WardrobeSlot slot17, WardrobeSlot slot18) {
        this.slots = new ArrayList<>(Arrays.asList(slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15, slot16, slot17, slot18));
    }

    public WardrobeSlot getSlot(int slot) {
        return this.slots.get(slot);
    }
}
