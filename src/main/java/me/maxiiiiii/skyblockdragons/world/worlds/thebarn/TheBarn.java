package me.maxiiiiii.skyblockdragons.world.worlds.thebarn;

import com.google.common.collect.Lists;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.drops.Drop;
import me.maxiiiiii.skyblockdragons.item.material.types.FarmingMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
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

import java.util.List;
import java.util.Map;

public class TheBarn extends WorldSD {

    public static final World world = Bukkit.getWorld("thebarn");

    public static Cooldown<PlayerSD> cooldown = new Cooldown<>();

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
                boolean isCrop = false;
                if (data instanceof Crops) {
                    isCrop = true;
                    player.sendMessage("CROP BROKEN");

                    Crops crop = (Crops) data;
                    if (crop.getState() != CropState.RIPE) {
                        e.setCancelled(true);
                        return;
                    }

                    replantCrop(block, type, crop);
                }


                if (isCrop){
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

                    if (cooldown.getCurrentCooldown(player) > 1000){
                        cooldown.setCooldown(player, System.currentTimeMillis());
                        List<String> items = Lists.newArrayList("ENCHANTED_WHEAT", "ENCHANTED_CARROT_ITEM", "ENCHANTED_POTATO_ITEM", "ENCHANTED_NETHER_WART");
                        for (String item : items) {
                            Recipe.craftItem(player, item);
                        }
                    }
                }
            }
            else if (player.getGameMode() != GameMode.CREATIVE){
                e.setCancelled(true);
            }
        }
    }

    private void replantCrop(Block block, Material type, Crops crop) {
        CropState cropState = crop.getState();
        if (block.getType() == Material.BEETROOT_BLOCK){
            cropState = CropState.values()[3];
        }
        CropState finalCropState = cropState;
        Functions.Wait(1L, () -> {
            block.setType(type);
            setCropState(block, CropState.values()[finalCropState.ordinal() - 1]);
        });
        Functions.Wait(100L, () -> {
            setCropState(block, CropState.values()[finalCropState.ordinal()]);
        });
    }

    public void setCropState(Block block, CropState cropState){
        BlockState state = block.getState();
        state.setData(new Crops(cropState));
        state.update();
    }
}
