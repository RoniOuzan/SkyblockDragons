package me.maxiiiiii.skyblockdragons.util.objects;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig extends YamlConfiguration {
    private final File file;

    public CustomConfig(File file) {
        this.file = file;
        this.load();
    }

    public CustomConfig(String name){
        this(new File(SkyblockDragons.plugin.getDataFolder(), name + (name.contains(".yml") ? "" : ".yml")));
    }

    //Finds or generates the custom config file
    public void setup(){
        if (!file.exists()) {
            Bukkit.getPlayer("LidanTheGamer").sendMessage("non exist");
            try {
                file.createNewFile();
            } catch (IOException e) {
                SkyblockDragons.logger.warning("Could not create " + file.getName());
                e.printStackTrace();
            }
        }
        this.load();
    }

    public void save() {
        try {
            this.save(file);
        } catch (IOException e) {
            System.out.println("Couldn't save file");
            e.printStackTrace();
        }
    }

    public CustomConfig load() {
        try {
            this.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return this;
    }
}
