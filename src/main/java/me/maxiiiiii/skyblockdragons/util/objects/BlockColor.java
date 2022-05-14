package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Getter;

@Getter
public enum BlockColor {
    WHITE(0),
    ORANGE(1),
    MAGENTA(2),
    LIGHT_BLUE(3),
    YELLOW(4),
    LIME(5),
    PINK(6),
    DARK_GRAY(7),
    GRAY(8),
    CYAN(9),
    PURPLE(10),
    BLUE(11),
    BROWN(12),
    GREEN(13),
    RED(14),
    BLACK(15)
    ;

    private final short data;

    BlockColor(int data) {
        this.data = (short) data;
    }
}
