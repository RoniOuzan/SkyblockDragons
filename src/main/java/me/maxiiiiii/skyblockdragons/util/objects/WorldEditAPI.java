package me.maxiiiiii.skyblockdragons.util.objects;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import org.bukkit.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class WorldEditAPI {
    public static void paste(Location location, String fileName) {
        World world = new BukkitWorld(location.getWorld());
        File file = new File(fileName);

        ClipboardFormat format = ClipboardFormat.findByFile(file);
        try {
            ClipboardReader reader = Objects.requireNonNull(format).getReader(new FileInputStream(file));
            EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1);
            Clipboard clipboard = reader.read(world.getWorldData());
            Operation operation = new ClipboardHolder(clipboard, world.getWorldData())
                    .createPaste(editSession, world.getWorldData())
                    .to(new Vector(location.getX(), location.getY(), location.getZ()))
                    .ignoreAirBlocks(false)
                    .build();
            Operations.complete(operation);
        } catch (IOException | WorldEditException e) {
            e.printStackTrace();
        }
    }
}
