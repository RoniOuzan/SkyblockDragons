package me.maxiiiiii.skyblockdragons;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.maxiiiiii.skyblockdragons.commands.*;
import me.maxiiiiii.skyblockdragons.damage.listeners.DamageListener;
import me.maxiiiiii.skyblockdragons.damage.listeners.EntityDeathListener;
import me.maxiiiiii.skyblockdragons.damage.listeners.OtherDamageListeners;
import me.maxiiiiii.skyblockdragons.damage.listeners.VanillaDamageListener;
import me.maxiiiiii.skyblockdragons.entity.EntityCommand;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.EntitySpawn;
import me.maxiiiiii.skyblockdragons.events.listeners.*;
import me.maxiiiiii.skyblockdragons.inventory.MenuListener;
import me.maxiiiiii.skyblockdragons.inventory.menus.ProfileMenu;
import me.maxiiiiii.skyblockdragons.inventory.menus.SkyblockMenu;
import me.maxiiiiii.skyblockdragons.item.ItemCommand;
import me.maxiiiiii.skyblockdragons.item.abilities.Wither_Impact;
import me.maxiiiiii.skyblockdragons.item.anvil.AnvilCommand;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.CraftingTableMenu;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.RecipesMenu;
import me.maxiiiiii.skyblockdragons.item.crystals.CrystalGrinderMenu;
import me.maxiiiiii.skyblockdragons.item.drops.PlayerGetDropListener;
import me.maxiiiiii.skyblockdragons.item.enchants.BookCommand;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantListeners;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantingTableCommand;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.modifiers.CrystalModifier;
import me.maxiiiiii.skyblockdragons.item.modifiers.HotPotatoModifier;
import me.maxiiiiii.skyblockdragons.item.modifiers.ReforgeModifier;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.pet.PetCommand;
import me.maxiiiiii.skyblockdragons.item.pet.PetListener;
import me.maxiiiiii.skyblockdragons.item.pet.PetMenu;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeCommand;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.accessorybag.AccessoryBagCommand;
import me.maxiiiiii.skyblockdragons.player.bank.BankCommand;
import me.maxiiiiii.skyblockdragons.player.chat.ChatCommand;
import me.maxiiiiii.skyblockdragons.player.chat.listeners.ChatListener;
import me.maxiiiiii.skyblockdragons.player.chat.listeners.PlayerGetMessageListener;
import me.maxiiiiii.skyblockdragons.player.chat.listeners.PlayerSendMessageListener;
import me.maxiiiiii.skyblockdragons.player.coop.CoopCommand;
import me.maxiiiiii.skyblockdragons.player.listeners.PlayerDeathListener;
import me.maxiiiiii.skyblockdragons.player.party.PartyChatCommand;
import me.maxiiiiii.skyblockdragons.player.party.PartyCommand;
import me.maxiiiiii.skyblockdragons.player.party.PartyListCommand;
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
import me.maxiiiiii.skyblockdragons.world.worlds.deepermines.forge.Forge;
import me.maxiiiiii.skyblockdragons.world.worlds.deepermines.forge.ForgeMilestoneCommand;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
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

    private static long pluginStartedAt = 0;

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
        ConfigurationSerialization.registerClass(EntitySpawn.class);

        Variables.load();

        ItemFullSetBonus.registerFullSets();
        Items.registerItems();
        EnchantType.registerEnchants();
        EntityMaterial.registerEntities();
        Bukkit.getScheduler().runTaskAsynchronously(this, Recipe::registerRecipes);
        WorldSD.registerWorlds(this);

        registerAllEvents();

        // Command Listeners
        getServer().getPluginManager().registerEvents(new AnvilCommand(), this);
        getServer().getPluginManager().registerEvents(new ReforgeCommand(), this);
        getServer().getPluginManager().registerEvents(new SkillListener(), this);
        getServer().getPluginManager().registerEvents(new BankCommand(), this);
        getServer().getPluginManager().registerEvents(new EnchantingTableCommand(), this);

        registerAllCommands();

        EntitySD.loadLocations();

        for (Player player : Bukkit.getOnlinePlayers()) {
            new PlayerSD(player);
        }

        autoSaveVariables();

        playerEverySecond();

        playerEvery5Ticks();

        spawnEntitiesTimer();

        loadAddonsAfter();

        pluginStartedAt = System.currentTimeMillis();
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
                EntityMaterial entityMaterial = EntitySD.entitiesLocations.get(location);
                if (!EntitySD.entities.values().stream().filter(e -> !e.isDead() && e.material == entityMaterial).map(e -> e.location).collect(Collectors.toList()).contains(location)) {
                    new EntitySD(location, entityMaterial);
                }
            }
        }, 0L, 200L);
    }

    private void playerEvery5Ticks() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (PlayerSD player : players.values()) {
                player.sendActionBar();
                if (player.isDead()){
                    player.sendMessage("YOU DEAD VANILLA ERROR?");
                    player.setHealth(0);
                    player.spigot().respawn();
                }
                // pets
                if (player.getPlayerPet().getActivePetSlot() >= 0) {
                    if (player.getPlayerPet().getVisual() != null && player.getPlayerPet().getVisual().getArmorStand().getWorld() != player.getWorld()) {
                        player.getPlayerPet().getVisual().getArmorStand().remove();
                        player.getPlayerPet().getVisual().getHologram().delete();
                        player.getPlayerPet().setVisual(null);
                        return;
                    }

                    if (player.getPlayerPet().getVisual() == null || player.getPlayerPet().getVisual().getSlot() != player.getPlayerPet().getActivePetSlot())
                        player.getPlayerPet().updateVisual();
                    if (player.getPlayerPet().getVisual().getArmorStand().getLocation().distance(player.getLocation()) > 3)
                        new FlyTo(player.getPlayerPet().getVisual().getArmorStand(), player, 20, 1.5, true, player.getPlayerPet().getVisual().getHologram(), new Vector(0, 1.6, 0));
                    player.getPlayerPet().getVisual().getArmorStand().teleport(player.getPlayerPet().getVisual().getArmorStand().getLocation().add(0, ((System.currentTimeMillis() / 1000) % 2 == 0 ? 0.1 : -0.1), 0));
                    Hologram hologram = player.getPlayerPet().getVisual().getHologram();
                    if (!hologram.isDeleted())
                        hologram.teleport(player.getPlayerPet().getVisual().getArmorStand().getLocation().add(0, 1.6, 0));
                    for (ParticlePacketUtil particle : player.getActivePetMaterial().getParticles()) {
                        particle.spawn(player.getPlayerPet().getVisual().getArmorStand().getLocation().add(0, 0.8, 0), Functions.getPlayerShowedPets());
                    }
                }

                if (player.getPlayerPet().getActivePetSlot() >= 0) {
                    player.getPlayerPet().levelUp();
                }

                if (player.getPlayerPet().isHidePets()) {
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
                if (player.getPlayerPet().getActivePetSlot() >= 0)
                    for (SoundUtil sound : player.getActivePetMaterial().getSounds()) {
                        for (Player showed : Functions.getPlayerShowedPets()) {
                            if (showed.getWorld() == player.getPlayerPet().getVisual().getArmorStand().getWorld())
                                showed.playSound(player.getPlayerPet().getVisual().getArmorStand().getLocation().add(0, 0.5, 0), sound.sound, (2f - (float) showed.getEyeLocation().distance(player.getPlayerPet().getVisual().getArmorStand().getLocation().add(0, 0.5, 0))) / 4, sound.pitch);
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
        getCommand("Warp").setTabCompleter(new WarpCommand());
        getCommand("EnchantingTable").setExecutor(new EnchantingTableCommand());
        getCommand("Profile").setExecutor(new ProfileMenu.Command());
        getCommand("Storage").setExecutor(new StorageMenu.Command());
        getCommand("EnderChest").setExecutor(new EnderChestMenu.Command());
        getCommand("SkyblockDragonsTest").setExecutor(new SkyblockDragonsTestCommand());
        getCommand("Alpha").setExecutor(new SkyblockDragonsAlphaCommand());
        registerCommand("Forge", new Forge.Command());
        registerCommand("ForgeMilestone", new ForgeMilestoneCommand());
        registerCommand("Party", new PartyCommand());
        registerCommand("Chat", new ChatCommand());
        registerCommand("PartyChat", new PartyChatCommand());
        registerCommand("PartyList", new PartyListCommand());
        registerCommand("Kill", new KillCommand());
        registerCommand("CrystalGrinder", new CrystalGrinderMenu.Command());
    }

    private void registerAllEvents() {
        // Listeners
        registerEvents(new DamageListener(), this);
        registerEvents(new VanillaDamageListener(), this);
        registerEvents(new EntityDeathListener(), this);
        registerEvents(new OtherDamageListeners(), this);
        registerEvents(new JoinQuitListener(), this);
        registerEvents(new ClickCanceller(), this);
        registerEvents(new UpdateStatsListeners(), this);
        registerEvents(new PetListener(), this);
        registerEvents(new ClickListener(), this);
        registerEvents(new InventoryClickListener(), this);
        registerEvents(new DropListener(), this);
        registerEvents(new ArmorStandManipulateListener(), this);
        registerEvents(new PlaceHeadListener(), this);
        registerEvents(new PickUpListeners(), this);
        registerEvents(new PortalListener(), this);
        registerEvents(new ProjectileHitListener(), this);
        registerEvents(new EndermanTeleportListener(), this);
        registerEvents(new PickableItem(), this);
        registerEvents(new PlayerPickupItemListener(), this);
        registerEvents(new UpdateInventoryListeners(), this);
        registerEvents(new PlayerUseAbilityListener(), this);
        registerEvents(new ProfileMenu.Event(), this);
        registerEvents(new ArrowHitCancellation(), this);
        registerEvents(new PlayerGetDropListener(), this);
        registerEvents(new PlayerRegenCanceller(), this);
        registerEvents(new HotPotatoModifier.Listener(), this);
        registerEvents(new ReforgeModifier.Listener(), this);
        registerEvents(new CrystalModifier.Listener(), this);
        registerEvents(new EnchantListeners(), this);

        registerEvents(new PlayerWarpListener(), this);
        registerEvents(new ChatListener(), this);
        registerEvents(new PlayerSendMessageListener(), this);
        registerEvents(new PlayerGetMessageListener(), this);
        registerEvents(new PlayerDeathListener(), this);

        registerEvents(new MenuListener(), this);

        // Abilities
        registerEvents(new Wither_Impact(), this);
//        registerEvents(new CommandListener(), this);
//        registerEvents(new TeleportListener(), this);
        registerEvents(new BlockListener(), this);
//        registerEvents(new PlayerAbilityLogger(), this);
        registerEvents(new ProjectileShootListener(), this);
        new PacketListeners(); // not really an event but works like them
    }
    
    public void registerEvents(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void registerEvents(Listener listener, JavaPlugin plugin){
        getServer().getPluginManager().registerEvents(listener, plugin);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);

        for (PlayerSD playerSD : players.values()) {
            playerSD.closeInventory();
            playerSD.save();
        }

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType() == EntityType.ARMOR_STAND && (entity.getScoreboardTags().contains("Pet") || entity.getScoreboardTags().contains("EntityHealth"))) {
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

    public static List<PlayerSD> getOnlinePlayers() {
        return players.values().stream().filter(Player::isOnline).collect(Collectors.toList());
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

    public static double getCurrentTimeInSeconds() {
        return (System.currentTimeMillis() - pluginStartedAt) / 1000d;
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