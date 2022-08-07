package me.maxiiiiii.skyblockdragons.dungeon;

import lombok.Getter;

@Getter
public enum DungeonType {
    NORMAL(""),
    MASTER_MODE("Master Mode")
    ;

    private final String name;

    DungeonType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
