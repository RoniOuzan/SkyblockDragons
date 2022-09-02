package me.maxiiiiii.skyblockdragons;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.maxiiiiii.skyblockdragons.commands.*;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.damage.KnockbackListener;
import me.maxiiiiii.skyblockdragons.entity.EntityCommand;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.events.*;
import me.maxiiiiii.skyblockdragons.inventory.MenuListener;
import me.maxiiiiii.skyblockdragons.inventory.menus.ProfileMenu;
import me.maxiiiiii.skyblockdragons.inventory.menus.SkyblockMenu;
import me.maxiiiiii.skyblockdragons.item.ItemCommand;
import me.maxiiiiii.skyblockdragons.item.abilities.Terminator;
import me.maxiiiiii.skyblockdragons.item.abilities.*;
import me.maxiiiiii.skyblockdragons.item.anvil.AnvilCommand;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.CraftingTableMenu;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.RecipesMenu;
import me.maxiiiiii.skyblockdragons.item.enchants.BookCommand;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantingTableCommand;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.pet.Pet;
import me.maxiiiiii.skyblockdragons.item.pet.PetCommand;
import me.maxiiiiii.skyblockdragons.item.pet.PetListener;
import me.maxiiiiii.skyblockdragons.item.pet.PetMenu;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeCommand;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.accessorybag.AccessoryBagCommand;
import me.maxiiiiii.skyblockdragons.player.bank.BankCommand;
import me.maxiiiiii.skyblockdragons.player.coop.CoopCommand;
import me.maxiiiiii.skyblockdragons.player.skill.SkillAdminCommand;
import me.maxiiiiii.skyblockdragons.player.skill.SkillListener;
import me.maxiiiiii.skyblockdragons.player.skill.SkillMenu;
import me.maxiiiiii.skyblockdragons.player.stats.StatCommand;
import me.maxiiiiii.skyblockdragons.player.storage.EnderChestMenu;
import me.maxiiiiii.skyblockdragons.player.storage.StorageMenu;
import me.maxiiiiii.skyblockdragons.player.wardrobe.WardrobeMenu;
import me.maxiiiiii.skyblockdragons.storage.VariableCommand;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.AddonUtils;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.EntityHider;
import me.maxiiiiii.skyblockdragons.util.objects.FlyTo;
import me.maxiiiiii.skyblockdragons.util.objects.PickableItem;
import me.maxiiiiii.skyblockdragons.util.objects.SoundUtil;
import me.maxiiiiii.skyblockdragons.util.particle.ParticlePacketUtil;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.warp.PlayerWarpListener;
import me.maxiiiiii.skyblockdragons.world.warp.WarpCommand;
import me.maxiiiiii.skyblockdragons.worlds.deepermines.forge.Forge;
import me.maxiiiiii.skyblockdragons.worlds.deepermines.forge.ForgeMilestoneCommand;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class SkyblockDragons extends JavaPlugin implements Listener {
    public static final Map<UUID, PlayerSD> players = new HashMap<>();
    public static final List<Entity> entitiesToKill = new ArrayList<>();
    public static boolean disablePlayTime = false;

    public static SkyblockDragons plugin;
    public static Economy economy = null;
    public static Logger logger = null;
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

        for (World world : Bukkit.getWorlds()) {
            world.getLivingEntities().stream().filter(e -> e.getScoreboardTags().contains("EntitySD") || e.getScoreboardTags().contains("Pet") || e.getScoreboardTags().contains("PickableItem") || e.getScoreboardTags().contains("EntityHealth")).forEach(Entity::remove);
        }

        Bukkit.getScheduler().runTask(this, TheEnd::resetEyes);

        ConfigurationSerialization.registerClass(EntityMaterial.class);

        Variables.load();

        Items.registerItems();
        EnchantType.registerEnchants();
        EntityMaterial.registerEntities();
        Bukkit.getScheduler().runTaskAsynchronously(this, Recipe::registerRecipes);
        WorldSD.registerWorlds(this);

        registerAllEvents();

        // Command Listeners
        getServer().getPluginManager().registerEvents(new AnvilCommand(), this);
        getServer().getPluginManager().registerEvents(new ReforgeCommand(), this);
        getServer().getPluginManager().registerEvents(new AccessoryBagCommand(), this);
        getServer().getPluginManager().registerEvents(new SkillListener(), this);
        getServer().getPluginManager().registerEvents(new BankCommand(), this);
        getServer().getPluginManager().registerEvents(new EnchantingTableCommand(), this);

        registerAllCommands();

//        Coop.load();
        EntitySD.loadLocations();

        for (Player player : Bukkit.getOnlinePlayers()) {
            new PlayerSD(player);
        }

        autoSaveVariables();

        playerEverySecond();

        playerEvery5Ticks();

        spawnEntitiesTimer();

//        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
//            for (PlayerSD player : players.values()) {
//                for (LivingEntity entity : player.getWorld().getLivingEntities()) {
//                    if (entity instanceof Creature) {
//                        player.getWorld().strikeLightningEffect(entity.getLocation());
//                        player.makeDamage(entity, Damage.DamageType.MAGIC, 1, 50, 0.05);
//                    }
//                }
//            }
//        }, 3000L, 6000L);
        loadAddonsAfter();

        System.out.println("Skyblock Dragons plugin has been loaded!");
    }

    private void loadAddonsAfter() {
        new BukkitRunnable() {
            @Override
            public void run() {
                AddonUtils.enableAddons();
            }
        }.runTaskLater(plugin, 5);
    }

    private void spawnEntitiesTimer() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Location location : EntitySD.entitiesLocations.keySet()) {
                if (Bukkit.getOnlinePlayers().stream().map(Entity::getWorld).noneMatch(w -> w == location.getWorld())) continue;
                if (!EntitySD.entities.values().stream().filter(e -> e.type == EntitySD.entitiesLocations.get(location)).map(e -> e.location).collect(Collectors.toList()).contains(location)) {
                    new EntitySD(location, EntitySD.entitiesLocations.get(location));
                }
            }
        }, 0L, 200L);
    }

    private void playerEvery5Ticks() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (PlayerSD player : players.values()) {
                if (player.isDead()){
                    player.sendMessage("YOU DEAD VANILLA ERROR?");
                    player.setHealth(0);
                    player.spigot().respawn();
                }
                // pets
                if (player.getPlayerPet().activePet >= 0) {
                    if (player.getPlayerPet().petArmorStand != null && player.getPlayerPet().petArmorStand.armorStand.getWorld() != player.getWorld()) {
                        player.getPlayerPet().petArmorStand.armorStand.remove();
                        player.getPlayerPet().petArmorStand.hologram.delete();
                        player.getPlayerPet().petArmorStand = null;
                        return;
                    }
                    if (player.getPlayerPet().getPetArmorStand() == null || player.getPlayerPet().getPetArmorStand().slot != player.getPlayerPet().activePet)
                        player.getPlayerPet().petArmorStand = new Pet.ArmorStand(player, player.getPetActive(), Pet.spawnPet(player, player.getPetActive()), player.getPlayerPet().activePet);

                    if (player.getPlayerPet().getPetArmorStand().armorStand.getLocation().distance(player.getLocation()) > 3)
                        new FlyTo(player.getPlayerPet().getPetArmorStand().armorStand, player, 20, 1.5, true, player.getPlayerPet().petArmorStand.hologram, new Vector(0, 1.6, 0));
                    player.getPlayerPet().petArmorStand.armorStand.teleport(player.getPlayerPet().petArmorStand.armorStand.getLocation().add(0, ((System.currentTimeMillis() / 1000) % 2 == 0 ? 0.1 : -0.1), 0));
                    player.getPlayerPet().petArmorStand.hologram.teleport(player.getPlayerPet().petArmorStand.armorStand.getLocation().add(0, 1.6, 0));
                    for (ParticlePacketUtil particle : player.getPetActive().petMaterial.getParticles()) {
                        particle.spawn(player.getPlayerPet().petArmorStand.armorStand.getLocation().add(0, 0.8, 0), Functions.getPlayerShowedPets());
                    }
                }

                if (player.getPlayerPet().activePet >= 0) {
                    if (player.getPetActive().levelUp(player)) {
                        player.getPlayerPet().pets.set(player.getPlayerPet().activePet, player.getPetActive());
                    }
                }

                if (player.getPlayerPet().hidePets) {
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
    }

    private void playerEverySecond() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (PlayerSD player : players.values()) {
                // playtime
                player.addPlayTime(20);
                player.getScoreboardSD().update();

                player.setFoodLevel(20);

                player.applyStats(true);

                // pet sound
                if (player.getPlayerPet().activePet >= 0)
                    for (SoundUtil sound : player.getPetActive().petMaterial.getSounds()) {
                        for (Player showed : Functions.getPlayerShowedPets()) {
                            if (showed.getWorld() == player.getPlayerPet().petArmorStand.armorStand.getWorld())
                                showed.playSound(player.getPlayerPet().petArmorStand.armorStand.getLocation().add(0, 0.5, 0), sound.sound, (2f - (float) showed.getEyeLocation().distance(player.getPlayerPet().petArmorStand.armorStand.getLocation().add(0, 0.5, 0))) / 4, sound.pitch);
                        }
                    }
            }
        }, 0L, 20L);
    }

    private void autoSaveVariables() {
        getServer().getScheduler().runTaskTimerAsynchronously(this, Variables::save, 6000L, 6000L);
    }

    private void registerAllCommands() {
        // Commands
        getCommand("SkyblockDragons").setExecutor(new SkyblockDragonsCommand());
        getCommand("Stat").setExecutor(new StatCommand());
        getCommand("Menu").setExecutor(new SkyblockMenu.Command());
        getCommand("Item").setExecutor(new ItemCommand());
        getCommand("Item").setTabCompleter(new ItemCommand());
        getCommand("Bits").setExecutor(new BitsCommand());
        getCommand("Bits").setTabCompleter(new BitsCommand());
        getCommand("Anvil").setExecutor(new AnvilCommand());
        getCommand("ReforgeMenu").setExecutor(new ReforgeCommand());
        getCommand("Book").setExecutor(new BookCommand());
        getCommand("Book").setTabCompleter(new BookCommand());
        getCommand("AccessoryBag").setExecutor(new AccessoryBagCommand());
        registerCommand("ViewRecipe", new Recipe.ViewCommand());
        registerCommand("CraftingTable", new CraftingTableMenu.Command());
        getCommand("PlayTime").setExecutor(new PlayTime());
        getCommand("SkillMenu").setExecutor(new SkillMenu.Command());
        getCommand("SkillAdmin").setExecutor(new SkillAdminCommand());
        registerCommand("Wardrobe", new WardrobeMenu.Command());
        getCommand("PetAdmin").setExecutor(new PetCommand());
        getCommand("PetAdmin").setTabCompleter(new PetCommand());
        registerCommand("PetMenu", new PetMenu.Command());
        getCommand("FlyTo").setExecutor(new FlyToCommand());
        getCommand("Bank").setExecutor(new BankCommand());
        getCommand("Sound").setExecutor(new SoundCommand());
        getCommand("Sound").setTabCompleter(new SoundCommand());
        getCommand("EntitySD").setExecutor(new EntityCommand());
        getCommand("EntitySD").setTabCompleter(new EntityCommand());
        getCommand("Coop").setExecutor(new CoopCommand());
        getCommand("Coop").setTabCompleter(new CoopCommand());
        registerCommand("Recipes", new RecipesMenu.Command());
        registerCommand("RecipesFor", new RecipesMenu.ForMenu.Command());
        registerCommand("RecipesWith", new RecipesMenu.WithMenu.Command());
        getCommand("Variables").setExecutor(new VariableCommand());
        getCommand("Warp").setExecutor(new WarpCommand());
        getCommand("EnchantingTable").setExecutor(new EnchantingTableCommand());
        getCommand("Profile").setExecutor(new ProfileMenu.Command());
        getCommand("Storage").setExecutor(new StorageMenu.Command());
        getCommand("EnderChest").setExecutor(new EnderChestMenu.Command());
        getCommand("SkyblockDragonsTest").setExecutor(new SkyblockDragonsTestCommand());
        registerCommand("Forge", new Forge.Command());
        registerCommand("ForgeMilestone", new ForgeMilestoneCommand());
    }

    private void registerAllEvents() {
        // Listeners
        getServer().getPluginManager().registerEvents(new Damage(), this);
        getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
        getServer().getPluginManager().registerEvents(new ClickCanceller(), this);
        getServer().getPluginManager().registerEvents(new UpdateStatsListeners(), this);
        getServer().getPluginManager().registerEvents(new PetListener(), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorStandManipulateListener(), this);
        getServer().getPluginManager().registerEvents(new PlaceHeadListener(), this);
        getServer().getPluginManager().registerEvents(new PickUpListeners(), this);
        getServer().getPluginManager().registerEvents(new KnockbackListener(), this);
        getServer().getPluginManager().registerEvents(new PortalListener(), this);
        getServer().getPluginManager().registerEvents(new ProjectileHitListener(), this);
        getServer().getPluginManager().registerEvents(new EndermanTeleportListener(), this);
        getServer().getPluginManager().registerEvents(new PickableItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerPickupItemListener(), this);
        getServer().getPluginManager().registerEvents(new UpdateInventoryListeners(), this);
        getServer().getPluginManager().registerEvents(new PlayerUseAbilityListener(), this);
        getServer().getPluginManager().registerEvents(new ProfileMenu.Event(), this);

        getServer().getPluginManager().registerEvents(new PlayerWarpListener(), this);

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

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
        getServer().getPluginManager().registerEvents(new Pigman_Dagger(), this);
        getServer().getPluginManager().registerEvents(new Aspect_Of_The_Dragons(), this);
        getServer().getPluginManager().registerEvents(new Moody_Grappleshot(), this);
        getServer().getPluginManager().registerEvents(new Wither_Cloak(), this);
        getServer().getPluginManager().registerEvents(new Magma_Cloak(), this);
        getServer().getPluginManager().registerEvents(new Mythologs_Spade(), this);
        getServer().getPluginManager().registerEvents(new ERROR_SCYTHE(), this);
        new PacketListeners(); // not really an event but works like them
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

        for (Hologram holo: HologramsAPI.getHolograms(this)) {
            holo.delete();
        }

        EntitySD.entities.values().stream().filter(e -> !(e instanceof PlayerSD)).forEach(e -> e.entity.remove());

        for (Entity entity : entitiesToKill) {
            if (!entity.isDead())
                entity.remove();
        }
        NPC.despawnAllNPCS();
        Variables.save();
        AddonUtils.disableAddons();
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

    public static List<PlayerSD> getPlayers() {
        return new ArrayList<>(players.values());
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

    public void registerCommand(String name, CommandSD command) {
        getCommand(name).setExecutor(command);
        getCommand(name).setTabCompleter(command);
    }
}


//                                            slayers
//                                              /\
//                                              ||
// deep mines -> the end -> bat workers -> bear -> griffin
//                                      ||
//                                      \/
//                                   dungeons