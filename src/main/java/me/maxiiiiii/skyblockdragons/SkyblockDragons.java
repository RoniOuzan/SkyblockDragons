package me.maxiiiiii.skyblockdragons;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.maxiiiiii.skyblockdragons.damage.KnockbackListener;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.abilities.*;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.craftingtable.commands.RecipesCommand;
import me.maxiiiiii.skyblockdragons.material.Items;
import me.maxiiiiii.skyblockdragons.player.accessorybag.AccessoryBagCommand;
import me.maxiiiiii.skyblockdragons.item.anvil.AnvilCommand;
import me.maxiiiiii.skyblockdragons.player.bank.BankCommand;
import me.maxiiiiii.skyblockdragons.commands.BitsCommand;
import me.maxiiiiii.skyblockdragons.commands.*;
import me.maxiiiiii.skyblockdragons.item.craftingtable.commands.CraftingTable;
import me.maxiiiiii.skyblockdragons.item.craftingtable.commands.ViewRecipe;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.entity.EntityCommand;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.events.*;
import me.maxiiiiii.skyblockdragons.item.*;
import me.maxiiiiii.skyblockdragons.item.enchants.BookCommand;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.player.coop.CoopCommand;
import me.maxiiiiii.skyblockdragons.player.pet.*;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeCommand;
import me.maxiiiiii.skyblockdragons.player.skill.SkillAdminCommand;
import me.maxiiiiii.skyblockdragons.player.skill.SkillListener;
import me.maxiiiiii.skyblockdragons.player.skill.SkillMenuCommand;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.events.UpdateStatsListeners;
import me.maxiiiiii.skyblockdragons.player.StatCommand;
import me.maxiiiiii.skyblockdragons.storage.VariableCommand;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.*;
import me.maxiiiiii.skyblockdragons.player.wardrobe.WardrobeCommand;
import me.maxiiiiii.skyblockdragons.player.wardrobe.WardrobeListener;
import me.maxiiiiii.skyblockdragons.util.objects.FlyTo;
import me.maxiiiiii.skyblockdragons.util.objects.ParticlePacketUtil;
import me.maxiiiiii.skyblockdragons.util.objects.Serializer;
import me.maxiiiiii.skyblockdragons.util.objects.SoundUtil;
import me.maxiiiiii.skyblockdragons.worlds.mining.Mining;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class SkyblockDragons extends JavaPlugin implements Listener {
    public static final HashMap<UUID, PlayerSD> players = new HashMap<>();
    public static final HashMap<UUID, ArrayList<Inventory>> playerGoBack = new HashMap<>();
    public static final ArrayList<Entity> entitiesToKill = new ArrayList<>();
    public static boolean disablePlayTime = false;

    public static SkyblockDragons plugin;
    public static Economy economy = null;
    public static Logger logger = null;
    public static Serializer serializer = new Serializer();
    public static EntityHider entityHider = null;

    @Override
    public void onEnable() {
        plugin = this;
        logger = this.getLogger();
        entityHider = new EntityHider(this, EntityHider.Policy.BLACKLIST);

        if (!setupEconomy() ) {
            logger.severe("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        for (Hologram holo: HologramsAPI.getHolograms(this)) {
            holo.delete();
        }

        Items.registerItems();
        EnchantType.registerEnchants();
        PetMaterial.registerItems();
        EntityMaterial.registerItems();
        Bukkit.getScheduler().runTaskAsynchronously(this, Recipe::registerRecipes);

        Variables.load();

        // Listeners
        getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
        getServer().getPluginManager().registerEvents(new ClickCanceller(), this);
        getServer().getPluginManager().registerEvents(new Damage(), this);
        getServer().getPluginManager().registerEvents(new UpdateStatsListeners(), this);
        getServer().getPluginManager().registerEvents(new GoBack(), this);
        getServer().getPluginManager().registerEvents(new PetListener(), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new EntityHealth(), this);
        getServer().getPluginManager().registerEvents(new PlayerClickOnPlayerListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorStandManipulateListener(), this);
        getServer().getPluginManager().registerEvents(new PlaceHeadListener(), this);
        getServer().getPluginManager().registerEvents(new PickUpListeners(), this);
        getServer().getPluginManager().registerEvents(new KnockbackListener(), this);
        getServer().getPluginManager().registerEvents(new PortalListener(), this);

        // World Listeners
        new Mining(this);

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
        getServer().getPluginManager().registerEvents(new Power_Orb(), this);
        getServer().getPluginManager().registerEvents(new Builders_Wand(), this);
        getServer().getPluginManager().registerEvents(new Axe_of_The_Shredded(), this);
        getServer().getPluginManager().registerEvents(new Midas_Staff(), this);
        getServer().getPluginManager().registerEvents(new ERRORMerang_Wand(), this);
        getServer().getPluginManager().registerEvents(new Pigman_Dagger(), this);

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
        getServer().getPluginManager().registerEvents(new SellCommand(), this);
        getServer().getPluginManager().registerEvents(new RecipesCommand(), this);

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
        getCommand("EntitySD").setExecutor(new EntityCommand());
        getCommand("EntitySD").setTabCompleter(new EntityCommand());
        getCommand("Coop").setExecutor(new CoopCommand());
        getCommand("Coop").setTabCompleter(new CoopCommand());
        getCommand("Variables").setExecutor(new VariableCommand());
        getCommand("Sell").setExecutor(new SellCommand());
        getCommand("Recipes").setExecutor(new RecipesCommand());

//        Coop.load();
        EntitySD.loadLocations();

        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerSD.loadPlayerData(player);
        }

        getServer().getScheduler().scheduleSyncRepeatingTask(this, Variables::save, 0L, 6000L); // 5 minutes

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (PlayerSD player : players.values()) {
                // playtime
                player.addPlayTime(20);
                player.setScoreboardScores();

                player.setFoodLevel(20);

                player.applyStats(true);

                // pet sound
                if (player.activePet >= 0)
                    for (SoundUtil sound : player.getPetActive().petMaterial.getSounds()) {
                        for (Player showed : Functions.getPlayerShowedPets()) {
                            if (showed.getWorld() == player.petArmorStand.armorStand.getWorld())
                                showed.playSound(player.petArmorStand.armorStand.getLocation().add(0, 0.5, 0), sound.sound, (2f - (float) showed.getEyeLocation().distance(player.petArmorStand.armorStand.getLocation().add(0, 0.5, 0))) / 4, sound.pitch);
                        }
                    }
            }
        }, 0L, 20L);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (PlayerSD player : players.values()) {
                // pets
                if (player.activePet >= 0) {
                    if (player.petArmorStand != null && player.petArmorStand.armorStand.getWorld() != player.getWorld()) {
                        player.petArmorStand.armorStand.remove();
                        player.petArmorStand.hologram.delete();
                        player.petArmorStand = null;
                        return;
                    }
                    if (player.getPetArmorStand() == null || player.getPetArmorStand().slot != player.activePet)
                        player.petArmorStand = new Pet.ArmorStand(player, player.getPetActive(), Pet.spawnPet(player, player.getPetActive()), player.activePet);

                    if (player.getPetArmorStand().armorStand.getLocation().distance(player.getLocation()) > 3)
                        new FlyTo(player.getPetArmorStand().armorStand, player, 20, 1.5, true, player.petArmorStand.hologram, new Vector(0, 1.6, 0));
                    player.petArmorStand.armorStand.teleport(player.petArmorStand.armorStand.getLocation().add(0, ((System.currentTimeMillis() / 1000) % 2 == 0 ? 0.1 : -0.1), 0));
                    for (ParticlePacketUtil particle : player.getPetActive().petMaterial.particles) {
                        Functions.spawnParticle(Functions.getPlayerShowedPets(), particle, player.petArmorStand.armorStand.getLocation().add(0, 0.8, 0));
                    }
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

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Location location : EntitySD.entitiesLocations.keySet()) {
                if (!EntitySD.entities.values().stream().filter(e -> e.type == EntitySD.entitiesLocations.get(location)).map(e -> e.location).collect(Collectors.toList()).contains(location)) {
                    new EntitySD(location, EntitySD.entitiesLocations.get(location));
                }
            }
        }, 0L, 200L);

        System.out.println("Skyblock Dragons plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType() == EntityType.ARMOR_STAND && entity.getScoreboardTags().contains("Pet")) {
                    entity.remove();
                }
            }
        }

        for (PlayerSD player : players.values()) {
            for (int i = 0; i < player.pets.size(); i++) {
                Variables.set(player.getUniqueId(), "Pets", i, player.pets.get(i).getAsItem());
            }
        }

        for (Hologram holo: HologramsAPI.getHolograms(this)) {
            holo.delete();
        }

        EntitySD.entities.values().stream().filter(e -> !(e instanceof PlayerSD)).forEach(e -> e.entity.remove());

        for (Entity entity : entitiesToKill) {
            if (!entity.isDead())
                entity.remove();
        }
        Variables.save();
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
        return getPlayer(Bukkit.getPlayer(name).getUniqueId());
    }

    public static PlayerSD getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public static PlayerSD getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }
}
