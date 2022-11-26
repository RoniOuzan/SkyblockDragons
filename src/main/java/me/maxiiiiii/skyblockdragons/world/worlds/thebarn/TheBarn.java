package me.maxiiiiii.skyblockdragons.world.worlds.thebarn;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.types.FarmingMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.material.NetherWarts;
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
                Map<Material, Integer> cropAdder = farmingMaterial.getCropAdder();
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
                    Functions.Wait(1L, () -> {
                        block.setType(type);
                        setCropState(block, CropState.VERY_TALL);
                    });
                    Functions.Wait(100L, () -> {
                        setCropState(block, CropState.RIPE);
                    });
                }
            }
        }
    }

    public void setCropState(Block block, CropState cropState){
        BlockState state = block.getState();
        state.setData(new Crops(cropState));
        state.update();
    }
}
