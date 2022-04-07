package me.maxiiiiii.skyblockdragons.commands.menu;

import me.maxiiiiii.skyblockdragons.item.objects.Stat;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class ProfileMenu {
    static public void openProfileMenu(PlayerSD player, PlayerSD target) {
        Inventory inventory = Bukkit.createInventory(player, 54, target.getName() + "'s Profile");

        Functions.putGlasses(inventory);

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        skullMeta.setDisplayName(ChatColor.GREEN + "Your Skyblock Profile");
        if (!skullMeta.hasOwner()) {
            skullMeta.setOwner(player.getName());
        }
        head.setItemMeta(skullMeta);
        ArrayList<String> lores = new ArrayList<>();
        for (Stat stat : player.getStats()) {
            lores.add(stat.type.getIconAndText() + " " + ChatColor.WHITE + Functions.getNumberFormat(stat.amount));
        }
        Functions.setLore(head, lores);
        inventory.setItem(22, head);

        ItemStack tool = target.getEquipment().getItemInMainHand();
        inventory.setItem(1, Functions.isNotAir(tool) ? tool : Functions.createItem(Material.STAINED_GLASS_PANE, 0, ChatColor.YELLOW + "Player Hand"));
        ItemStack helmet = target.getEquipment().getHelmet();
        inventory.setItem(10, Functions.isNotAir(helmet) ? helmet : Functions.createItem(Material.STAINED_GLASS_PANE, 0, ChatColor.YELLOW + "Player Helmet"));
        ItemStack chestplate = target.getEquipment().getChestplate();
        inventory.setItem(19, Functions.isNotAir(chestplate) ? chestplate : Functions.createItem(Material.STAINED_GLASS_PANE, 0, ChatColor.YELLOW + "Player Chestplate"));
        ItemStack leggings = target.getEquipment().getLeggings();
        inventory.setItem(28, Functions.isNotAir(leggings) ? leggings : Functions.createItem(Material.STAINED_GLASS_PANE, 0, ChatColor.YELLOW + "Player Leggings"));
        ItemStack boots = target.getEquipment().getBoots();
        inventory.setItem(37, Functions.isNotAir(boots) ? boots : Functions.createItem(Material.STAINED_GLASS_PANE, 0, ChatColor.YELLOW + "Player Boots"));
        ItemStack pet = target.getPlayerPet().activePet < 0 ? Functions.createItem(Material.STAINED_GLASS_PANE, 0, ChatColor.YELLOW + "Player Pet") : target.getPetActive();
        inventory.setItem(46, pet);

        ItemStack close = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", ChatColor.YELLOW + "Click to close!");
        inventory.setItem(49, close);

        player.openInventory(inventory);
    }
}
