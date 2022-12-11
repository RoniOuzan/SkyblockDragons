package me.maxiiiiii.skyblockdragons.world.attributes;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;

import java.util.function.Consumer;

@Getter
public class ClickableBlock implements WorldAttribute {
    public enum ClickType {
        LEFT(Action.LEFT_CLICK_BLOCK),
        RIGHT(Action.RIGHT_CLICK_BLOCK);

        private final Action action;

        ClickType(Action action) {
            this.action = action;
        }

        public boolean isClicked(Action action) {
            return this.action == action;
        }
    }

    private final Block block;
    private final ClickType clickType;
    private final Consumer<PlayerSD> onInteract;

    public ClickableBlock(Block block, ClickType clickType, Consumer<PlayerSD> onInteract) {
        this.block = block;
        this.clickType = clickType;
        this.onInteract = onInteract;
    }

    public boolean isClicked(Action action) {
        return this.clickType.isClicked(action);
    }

    public void interact(PlayerSD player) {
        this.onInteract.accept(player);
    }
}
