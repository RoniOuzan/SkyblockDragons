package me.maxiiiiii.skyblockdragons;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.maxiiiiii.skyblockdragons.abilities.*;
import me.maxiiiiii.skyblockdragons.accessorybag.AccessoryBagCommand;
import me.maxiiiiii.skyblockdragons.anvil.AnvilCommand;
import me.maxiiiiii.skyblockdragons.bank.BankCommand;
import me.maxiiiiii.skyblockdragons.bits.BitsCommand;
import me.maxiiiiii.skyblockdragons.commands.*;
import me.maxiiiiii.skyblockdragons.craftingtable.commands.CraftingTable;
import me.maxiiiiii.skyblockdragons.craftingtable.commands.ViewRecipe;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.events.ClickListener;
import me.maxiiiiii.skyblockdragons.events.InventoryClickListener;
import me.maxiiiiii.skyblockdragons.itemcreator.*;
import me.maxiiiiii.skyblockdragons.events.JoinQuitListener;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.pet.*;
import me.maxiiiiii.skyblockdragons.reforge.ReforgeCommand;
import me.maxiiiiii.skyblockdragons.skill.SkillAdminCommand;
import me.maxiiiiii.skyblockdragons.skill.SkillListener;
import me.maxiiiiii.skyblockdragons.skill.SkillMenuCommand;
import me.maxiiiiii.skyblockdragons.stat.PlayerSD;
import me.maxiiiiii.skyblockdragons.stat.OnSlotChange;
import me.maxiiiiii.skyblockdragons.stat.StatCommand;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.*;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeCommand;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import static me.maxiiiiii.skyblockdragons.storage.Variables.setVariable;
import static me.maxiiiiii.skyblockdragons.util.Functions.cooldown;

public final class SkyblockDragons extends JavaPlugin implements Listener {
    public static final HashMap<UUID, PlayerSD> players = new HashMap<>();
    public static final HashMap<UUID, Double> purses = new HashMap<>();
    public static final HashMap<UUID, Long> bits = new HashMap<>();
    public static final HashMap<UUID, Long> playTime = new HashMap<>();
    public static final HashMap<UUID, ArrayList<Inventory>> playerGoBack = new HashMap<>();
    public static final HashMap<Player, Cooldown> petSoundCooldown = new HashMap<>();
    public static boolean disablePlayTime = false;

    public static Economy economy = null;

    public static Logger logger = null;
    public static SkyblockDragons plugin;
    public static final Serializer serializer = new Serializer();
    public static EntityHider entityHider = null;

    public static SkyblockDragons getInstance() {
        return plugin;
    }

    public static Serializer getSerializer() {
        return serializer;
    }

    @Override
    public void onEnable() {
        plugin = this;
        logger = this.getLogger();
        entityHider = new EntityHider(this, EntityHider.Policy.BLACKLIST);

        ItemMaterial.registerItems();
        EnchantType.registerEnchants();
        PetMaterial.registerItems();

        for (Hologram holo: HologramsAPI.getHolograms(this)) {
            holo.delete();
        }

        try {
            Variables.loadVariables();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!setupEconomy() ) {
            logger.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Listeners
        getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
        getServer().getPluginManager().registerEvents(new ClickCanceller(), this);
        getServer().getPluginManager().registerEvents(new Damage(), this);
        getServer().getPluginManager().registerEvents(new PlayerList(), this);
        getServer().getPluginManager().registerEvents(new OnSlotChange(), this);
        getServer().getPluginManager().registerEvents(new GoBack(), this);
        getServer().getPluginManager().registerEvents(new PetListener(), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);

        // Abilities
        getServer().getPluginManager().registerEvents(new Aspect_of_The_End(), this);
        getServer().getPluginManager().registerEvents(new Aspect_of_The_Void(), this);
        getServer().getPluginManager().registerEvents(new Atomsplit_Katana(), this);
        getServer().getPluginManager().registerEvents(new Leaping_Sword(), this);
        getServer().getPluginManager().registerEvents(new Bonzo_Staff(), this);
        getServer().getPluginManager().registerEvents(new Grappling_Hook(), this);
        getServer().getPluginManager().registerEvents(new Gyrokinetic_Wand(), this);
        getServer().getPluginManager().registerEvents(new Terminator(), this);
        getServer().getPluginManager().registerEvents(new Bonemerang(), this);
        getServer().getPluginManager().registerEvents(new Shadow_Fury(), this);
        getServer().getPluginManager().registerEvents(new Flower_of_Truth(), this);
        getServer().getPluginManager().registerEvents(new Tree_Capitator(), this);
        getServer().getPluginManager().registerEvents(new World_Eater(), this);
        getServer().getPluginManager().registerEvents(new World_Eater(), this);
        getServer().getPluginManager().registerEvents(new Tornado_Wand(), this);
        getServer().getPluginManager().registerEvents(new Twister_Wand(), this);
        getServer().getPluginManager().registerEvents(new Hurricane_Wand(), this);
        getServer().getPluginManager().registerEvents(new Parabola_Wand(), this);
        getServer().getPluginManager().registerEvents(new Soul_Whip(), this);
        getServer().getPluginManager().registerEvents(new Troll_Eye(), this);
        getServer().getPluginManager().registerEvents(new Wither_Impact(), this);
        getServer().getPluginManager().registerEvents(new Spirit_Sceptre(), this);
        getServer().getPluginManager().registerEvents(new Rogue_Sword(), this);
        getServer().getPluginManager().registerEvents(new Radiant_Power_Orb(), this);
        getServer().getPluginManager().registerEvents(new Mana_Flux_Power_Orb(), this);
        getServer().getPluginManager().registerEvents(new Overflux_Power_Orb(), this);
        getServer().getPluginManager().registerEvents(new Plasma_Power_Orb(), this);
        getServer().getPluginManager().registerEvents(new Builders_Wand(), this);
        getServer().getPluginManager().registerEvents(new Axe_of_The_Shredded(), this);
        getServer().getPluginManager().registerEvents(new Midas_Staff(), this);
        getServer().getPluginManager().registerEvents(new ERRORMerang_Wand(), this);

        // Command Listeners
        getServer().getPluginManager().registerEvents(new ItemCommand(), this);
        getServer().getPluginManager().registerEvents(new JavaMenu(), this);
        getServer().getPluginManager().registerEvents(new AnvilCommand(), this);
        getServer().getPluginManager().registerEvents(new ReforgeCommand(), this);
        getServer().getPluginManager().registerEvents(new AccessoryBagCommand(), this);
        getServer().getPluginManager().registerEvents(new CraftingTable(), this);
        getServer().getPluginManager().registerEvents(new ViewRecipe(), this);
        getServer().getPluginManager().registerEvents(new SkillListener(), this);
        getServer().getPluginManager().registerEvents(new WardrobeListener(), this);
        getServer().getPluginManager().registerEvents(new PetMenuCommand(), this);
        getServer().getPluginManager().registerEvents(new BankCommand(), this);

        // Commands
        getCommand("Stat").setExecutor(new StatCommand());
        getCommand("Menu").setExecutor(new JavaMenu());
        getCommand("Item").setExecutor(new ItemCommand());
        getCommand("Item").setTabCompleter(new ItemCommand());
        getCommand("SkyblockDragons").setExecutor(new JavaPluginCommand());
        getCommand("SkyblockDragons").setTabCompleter(new JavaPluginCommand());
        getCommand("Bits").setExecutor(new BitsCommand());
        getCommand("Bits").setTabCompleter(new BitsCommand());
        getCommand("Anvil").setExecutor(new AnvilCommand());
        getCommand("ReforgeMenu").setExecutor(new ReforgeCommand());
        getCommand("Book").setExecutor(new BookCommand());
        getCommand("Book").setTabCompleter(new BookCommand());
        getCommand("AccessoryBag").setExecutor(new AccessoryBagCommand());
        getCommand("ViewRecipe").setExecutor(new ViewRecipe());
        getCommand("ViewRecipe").setTabCompleter(new ViewRecipe());
        getCommand("CraftingTable").setExecutor(new CraftingTable());
        getCommand("PlayTime").setExecutor(new PlayTime());
        getCommand("SkillMenu").setExecutor(new SkillMenuCommand());
        getCommand("SkillAdmin").setExecutor(new SkillAdminCommand());
        getCommand("Wardrobe").setExecutor(new WardrobeCommand());
        getCommand("PetAdmin").setExecutor(new PetCommand());
        getCommand("PetAdmin").setTabCompleter(new PetCommand());
        getCommand("PetMenu").setExecutor(new PetMenuCommand());
        getCommand("FlyTo").setExecutor(new FlyToCommand());
        getCommand("Bank").setExecutor(new BankCommand());
        getCommand("Sound").setExecutor(new SoundCommand());
        getCommand("Sound").setTabCompleter(new SoundCommand());

        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerSD.loadPlayerData(player);
        }

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (PlayerSD player : players.values()) {
                player.updatePlayerInventory();
                player.setScoreboardScores();

                player.setFoodLevel(20);

                player.applyStats(true);

                // playtime
                playTime.put(player.getUniqueId(), playTime.getOrDefault(player.getUniqueId(), 0L) + 5L);
                setVariable(player.getUniqueId(), "PlayTime", playTime.get(player.getUniqueId()) + "");

                // pet sound
                if (player.activePet >= 0)
                    for (SoundUtil sound : player.getPetActive().petMaterial.getSounds()) {
    //                    sound.play(player.petArmorStand.armorStand.getLocation().add(0, 0.5, 0));
                        for (Player value : Functions.getPlayerShowedPets()) {
                            value.playSound(player.petArmorStand.armorStand.getLocation().add(0, 0.5, 0), sound.sound, (3f - (float) value.getEyeLocation().distance(player.petArmorStand.armorStand.getLocation().add(0, 0.5, 0))) / 5, sound.pitch);
                        }
                    }
            }
        }, 0L, 20L);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (PlayerSD player : players.values()) {
                // pets
                if (player.activePet >= 0 && (player.getPetArmorStand() == null || player.getPetArmorStand().slot != player.activePet)) {
                    player.petArmorStand = new Pet.ArmorStand(player, player.getPetActive(), Pet.spawnPet(player, player.getPetActive()), player.activePet);
                }
                if (player.activePet >= 0) {
                    if (player.getPetArmorStand().armorStand.getLocation().distance(player.getLocation()) > 3)
                        new FlyTo(player.getPetArmorStand().armorStand, player, 20, 1.5, true, player.petArmorStand.hologram, new Vector(0, 1.6, 0));
                    player.petArmorStand.armorStand.teleport(player.petArmorStand.armorStand.getLocation().add(0, ((System.currentTimeMillis() / 1000) % 2 == 0 ? 0.1 : -0.1), 0));
                    for (ParticleUtil particle : player.getPetActive().petMaterial.particles) {
                        Functions.spawnParticle(Functions.getPlayerShowedPets(), particle, player.petArmorStand.armorStand.getLocation());
                    }
                    player.petArmorStand.hologram.teleport(player.petArmorStand.armorStand.getLocation().add(0, 1.6, 0));
                }

                if (player.activePet >= 0) {
                    if (player.getPetActive().levelUp(player)) {
                        player.pets.set(player.activePet, player.getPetActive());
                    }
                }

                if (player.hidePets) {
                    for (Entity entity : player.getWorld().getEntities()) {
                        if (entity.getScoreboardTags().contains("Pet")) {
                            player.hideEntity(entity);
                        }
                    }

                    for (Hologram hologram : HologramsAPI.getHolograms(this)) {
                        if (hologram.getLine(0).toString().contains(ChatColor.GREEN + "" + ChatColor.DARK_GREEN + "" + ChatColor.GREEN + "" + ChatColor.DARK_GREEN)) {
                            hologram.getVisibilityManager().hideTo(player);
                        }
                    }
                } else {
                    for (Entity entity : player.getWorld().getEntities()) {
                        if (entity.getScoreboardTags().contains("Pet")) {
                            player.showEntity(entity);
                        }
                    }

                    for (Hologram hologram : HologramsAPI.getHolograms(this)) {
                        if (hologram.getLine(0).toString().contains(ChatColor.GREEN + "" + ChatColor.DARK_GREEN + "" + ChatColor.GREEN + "" + ChatColor.DARK_GREEN)) {
                            hologram.getVisibilityManager().showTo(player);
                        }
                    }
                }
            }
        }, 0L, 5L);

        System.out.println("Skyblock Dragons plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType() == EntityType.ARMOR_STAND && entity.getScoreboardTags().contains("Pet")) {
                    entity.remove();
                }
            }
        }

        for (PlayerSD player : players.values()) {
            for (int i = 0; i < player.pets.size(); i++) {
                Variables.setVariable(player.getUniqueId(), "Pets", SkyblockDragons.getSerializer().serialize(player.pets.get(i).getAsItem()), i);
            }
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static PlayerSD getPlayer(String name) {
        for (PlayerSD myPlayer : players.values()) {
            if (myPlayer.getName().equals(name)) {
                return myPlayer;
            }
        }
        return null;
    }

    public static PlayerSD getPlayer(UUID uuid) {
        for (PlayerSD myPlayer : players.values()) {
            if (myPlayer.getUniqueId() == uuid) {
                return myPlayer;
            }
        }
        return null;
    }

    public static PlayerSD getPlayer(Player player) {
        return players.get(player.getUniqueId());
    }

    public static SkyblockDragons getPlugin() {
        return plugin;
    }
}
