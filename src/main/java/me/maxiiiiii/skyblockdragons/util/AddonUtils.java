package me.maxiiiiii.skyblockdragons.util;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class AddonUtils {
    public static List<JavaPlugin> getAddons(){
        List<JavaPlugin> addons = new ArrayList<>();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (plugin.getDescription().getDepend().contains("SkyblockDragons")) {
                addons.add((JavaPlugin) plugin);
            }
        }
        SkyblockDragons.logger.info("Addons: " + addons);
        return addons;
    }

    public static JavaPlugin getAddon(String name){
        for (JavaPlugin plugin : getAddons()) {
            if (plugin.getName().equalsIgnoreCase(name)) {
                return plugin;
            }
        }
        return null;
    }

    public static void disableAddons(){
        for (JavaPlugin plugin : getAddons()) {
            if (plugin.isEnabled()) {
                SkyblockDragons.logger.info("Disabling " + plugin.getName());
                plugin.getPluginLoader().disablePlugin(plugin);
            }
        }
    }

    public static void enableAddons(){
        for (JavaPlugin plugin : getAddons()) {
            if (!plugin.isEnabled()) {
                SkyblockDragons.logger.info("Enabling " + plugin.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plugman reload " + plugin.getName());
            }
        }
    }
}
