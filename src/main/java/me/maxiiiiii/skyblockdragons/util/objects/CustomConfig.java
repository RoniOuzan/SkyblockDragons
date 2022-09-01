package me.maxiiiiii.skyblockdragons.util.objects;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {

    private final File file;
    private FileConfiguration customFile;

    public CustomConfig(String name){
        if (!name.endsWith(".yml"))
            name = String.format("%s.yml", name);
        file = new File(SkyblockDragons.plugin.getDataFolder(), name);
        setup();
    }

    public CustomConfig(File file) {
        this.file = file;
        setup();
    }

    //Finds or generates the custom config file
    public void setup(){
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                SkyblockDragons.logger.warning("Could not create bazaarData.yml");
                e.printStackTrace();
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration get(){
        return customFile;
    }

    public void save(){
        try{
            customFile.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save file");
        }
    }

    public void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}
