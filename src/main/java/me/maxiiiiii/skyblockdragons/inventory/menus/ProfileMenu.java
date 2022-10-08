package me.maxiiiiii.skyblockdragons.inventory.menus;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.stream.Collectors;

public class ProfileMenu extends Menu {
    protected final PlayerSD target;

    public ProfileMenu(PlayerSD player, PlayerSD target) {
        super(player, target.getName() + "'s Profile", 6, InventoryGlassType.ALL, true);

        this.target = target;
    }

    @Override
    public void update() {
        this.setItem(13, Functions.applyHead(Functions.createItem(Material.SKULL_ITEM, 1, 3, ChatColor.WHITE + target.getName() + "'s Profile", target.getStats().toList().stream().map(s -> s.type.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(s.amount)).collect(Collectors.toList())), target));

        this.setItem(1, Functions.isNotAir(target.getEquipment().getItemInMainHand()) ? target.getEquipment().getItemInMainHand() : Functions.createItem(Material.STAINED_GLASS_PANE, 1, 0, ChatColor.YELLOW + "Player Hand"));
        this.setItem(10, Functions.isNotAir(target.getEquipment().getHelmet()) ? target.getEquipment().getHelmet() : Functions.createItem(Material.STAINED_GLASS_PANE, 1, 0, ChatColor.YELLOW + "Player Helmet"));
        this.setItem(19, Functions.isNotAir(target.getEquipment().getChestplate()) ? target.getEquipment().getChestplate() : Functions.createItem(Material.STAINED_GLASS_PANE, 1, 0, ChatColor.YELLOW + "Player Chestplate"));
        this.setItem(28, Functions.isNotAir(target.getEquipment().getLeggings()) ? target.getEquipment().getLeggings() : Functions.createItem(Material.STAINED_GLASS_PANE, 1, 0, ChatColor.YELLOW + "Player Leggings"));
        this.setItem(37, Functions.isNotAir(target.getEquipment().getBoots()) ? target.getEquipment().getBoots() : Functions.createItem(Material.STAINED_GLASS_PANE, 1, 0, ChatColor.YELLOW + "Player Boots"));
        this.setItem(46, target.getPlayerPet().getActivePet() >= 0 ? target.getPetActive() : Functions.createItem(Material.STAINED_GLASS_PANE, 1, 0, ChatColor.YELLOW + "Player Pet"));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {

    }

    public static class Command implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
            if (sender instanceof Player) {
                PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
                if (args.length > 0) {
                    new ProfileMenu(player, SkyblockDragons.getPlayer(args[0]));
                } else {
                    new ProfileMenu(player, player);
                }
            }
            return true;
        }
    }

    public static class Event implements Listener {
        @EventHandler
        public void onClickOnPlayer(PlayerInteractEntityEvent e) {
            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
            if (e.getRightClicked() instanceof Player && !e.getRightClicked().hasMetadata("NPC") && player.getItems().getTool() == null) {
                new ProfileMenu(player, SkyblockDragons.getPlayer((Player) e.getRightClicked()));
            }
        }
    }
}
