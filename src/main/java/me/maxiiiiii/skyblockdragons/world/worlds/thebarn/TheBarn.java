package me.maxiiiiii.skyblockdragons.world.worlds.thebarn;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.drops.Drop;
import me.maxiiiiii.skyblockdragons.item.material.types.FarmingMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class TheBarn extends WorldSD {

    public static final World world = Bukkit.getWorld("thebarn");

    public TheBarn(JavaPlugin plugin) {
        super(world, "The Barn", Warp.THE_BARN, WorldType.COMBAT, WorldType.FARMING);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        ItemMaterial toolMaterial = player.getItems().getToolMaterial();
        if (player.getWorld() == world) {
            if (toolMaterial instanceof FarmingMaterial) {

                FarmingMaterial farmingMaterial = (FarmingMaterial) toolMaterial;
                Map<Material, Drop> cropAdder = farmingMaterial.getCropAdder();
                player.sendMessage(cropAdder);

                Block block = e.getBlock();
                Material type = block.getType();
                MaterialData data = block.getState().getData().clone();
                if (data instanceof Crops) {
                    Crops crop = (Crops) data;
                    if (crop.getState() != CropState.RIPE) {
                        e.setCancelled(true);
                        return;
                    }
                    e.setDropItems(false);
                    boolean dropped = false;
                    for (Material material : cropAdder.keySet()) {
                        if (material == block.getType()) {
                            Drop drop = cropAdder.get(material);
                            drop.drop(player, block);
                            dropped = true;
                        }
                    }
                    if (!dropped){
                        player.sendMessage(ChatColor.RED + "You need a better hoe to break this!");
                        e.setCancelled(true);
                        return;
                    }

                    CropState cropState = crop.getState();
                    if (block.getType() == Material.BEETROOT_BLOCK){
                        cropState = CropState.values()[3];
                    }
                    player.sendMessage(cropState);
                    CropState finalCropState = cropState;
                    Functions.Wait(1L, () -> {
                        block.setType(type);
                        setCropState(block, CropState.values()[finalCropState.ordinal() - 1]);
                    });
                    Functions.Wait(100L, () -> {
                        setCropState(block, CropState.values()[finalCropState.ordinal()]);
                    });
                }
            }
            else if (player.getGameMode() != GameMode.CREATIVE){
                e.setCancelled(true);
            }
        }
    }

    public void setCropState(Block block, CropState cropState){
        BlockState state = block.getState();
        state.setData(new Crops(cropState));
        state.update();
    }
}
