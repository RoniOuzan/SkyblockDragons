package me.maxiiiiii.skyblockdragons;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.maxiiiiii.skyblockdragons.abilities.*;
import me.maxiiiiii.skyblockdragons.accessorybag.AccessoryBagCommand;
import me.maxiiiiii.skyblockdragons.anvil.AnvilCommand;
import me.maxiiiiii.skyblockdragons.bits.BitsCommand;
import me.maxiiiiii.skyblockdragons.bits.BitsTabCompletion;
import me.maxiiiiii.skyblockdragons.commands.JavaMenu;
import me.maxiiiiii.skyblockdragons.commands.JavaPluginCommand;
import me.maxiiiiii.skyblockdragons.commands.JavaPluginTabCompletion;
import me.maxiiiiii.skyblockdragons.commands.PlayTime;
import me.maxiiiiii.skyblockdragons.craftingtable.Commands.CraftingTable;
import me.maxiiiiii.skyblockdragons.craftingtable.Commands.ViewRecipe;
import me.maxiiiiii.skyblockdragons.craftingtable.Commands.ViewRecipeTabComplete;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.itemcreator.*;
import me.maxiiiiii.skyblockdragons.listeners.EntityHealth;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.pet.*;
import me.maxiiiiii.skyblockdragons.reforge.ReforgeCommand;
import me.maxiiiiii.skyblockdragons.skill.Skill;
import me.maxiiiiii.skyblockdragons.skill.SkillAdminCommand;
import me.maxiiiiii.skyblockdragons.skill.SkillListener;
import me.maxiiiiii.skyblockdragons.skill.SkillMenuCommand;
import me.maxiiiiii.skyblockdragons.skill.Skills.*;
import me.maxiiiiii.skyblockdragons.stat.PlayerSD;
import me.maxiiiiii.skyblockdragons.stat.OnSlotChange;
import me.maxiiiiii.skyblockdragons.stat.StatCommand;
import me.maxiiiiii.skyblockdragons.storage.StorageUtil;
import me.maxiiiiii.skyblockdragons.util.*;
import me.maxiiiiii.skyblockdragons.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeCommand;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeListener;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeSlot;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import static me.maxiiiiii.skyblockdragons.Functions.loadPlayerData;
import static me.maxiiiiii.skyblockdragons.Functions.numberToItemSlot;
import static me.maxiiiiii.skyblockdragons.Functions.sendActionBar;
import static me.maxiiiiii.skyblockdragons.Functions.setScoreboardScores;
import static me.maxiiiiii.skyblockdragons.storage.StorageUtil.setVariable;

public final class SkyblockDragons extends JavaPlugin implements Listener {
    public static final HashMap<UUID, PlayerSD> players = new HashMap<>();
    public static final HashMap<UUID, Double> purses = new HashMap<>();
    public static final HashMap<UUID, Long> bits = new HashMap<>();
    public static final HashMap<UUID, Long> playTime = new HashMap<>();
    public static final HashMap<UUID, ArrayList<Inventory>> playerGoBack = new HashMap<>();
    public static boolean disablePlayTime = false;

    public static Economy economy = null;
    public static Permission perms = null;
    public static Chat chat = null;

    public static final Logger log = Logger.getLogger("SkyblockDragons");
    public static SkyblockDragons plugin;
    private static final Serializer serializerInstance = new Serializer();
    public SkyblockDragons() {
        plugin = this;
    }

    public static SkyblockDragons getInstance() {
        return plugin;
    }

    private SignMenu signMenu;

    public static Serializer getSerializer() {
        return serializerInstance;
    }

    @Override
    public void onEnable() {
        plugin = this;

        ItemMaterial.registerItems();
        EnchantType.registerEnchants();
        PetMaterial.registerItems();

        for (Hologram holo: HologramsAPI.getHolograms(this)) {
            holo.delete();
        }

        try {
            StorageUtil.loadVariables();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Listeners
        getServer().getPluginManager().registerEvents(new EntityHealth(), this);
        getServer().getPluginManager().registerEvents(new ClickCanceller(), this);
        getServer().getPluginManager().registerEvents(new Damage(), this);
        getServer().getPluginManager().registerEvents(new PlayerList(), this);
        getServer().getPluginManager().registerEvents(new OnSlotChange(), this);
        getServer().getPluginManager().registerEvents(new GoBack(), this);
        getServer().getPluginManager().registerEvents(new PetListener(), this);

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

        // Commands
        getCommand("Stat").setExecutor(new StatCommand());
        getCommand("Menu").setExecutor(new JavaMenu());
        getCommand("Item").setExecutor(new ItemCommand());
        getCommand("Item").setTabCompleter(new ItemCommandTabComplete());
        getCommand("SkyblockDragons").setExecutor(new JavaPluginCommand());
        getCommand("SkyblockDragons").setTabCompleter(new JavaPluginTabCompletion());
        getCommand("Bits").setExecutor(new BitsCommand());
        getCommand("Bits").setTabCompleter(new BitsTabCompletion());
        getCommand("Anvil").setExecutor(new AnvilCommand());
        getCommand("ReforgeMenu").setExecutor(new ReforgeCommand());
        getCommand("Book").setExecutor(new BookCommand());
        getCommand("Book").setTabCompleter(new BookCommandTabCompletion());
        getCommand("AccessoryBag").setExecutor(new AccessoryBagCommand());
        getCommand("ViewRecipe").setExecutor(new ViewRecipe());
        getCommand("CraftingTable").setExecutor(new CraftingTable());
        getCommand("CraftingTable").setTabCompleter(new ViewRecipeTabComplete());
        getCommand("PlayTime").setExecutor(new PlayTime());
        getCommand("SkillMenu").setExecutor(new SkillMenuCommand());
        getCommand("SkillAdmin").setExecutor(new SkillAdminCommand());
        getCommand("Wardrobe").setExecutor(new WardrobeCommand());
        getCommand("PetAdmin").setExecutor(new PetCommand());
        getCommand("PetAdmin").setTabCompleter(new PetTabComplete());

        this.signMenu = new SignMenu(this);

        for (Player player : Bukkit.getOnlinePlayers()) {
            loadPlayerData(player);
        }

        final double HEALTH_REGEN = 1.02;

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (PlayerSD player : players.values()) {
                setScoreboardScores(player);

                player.setFoodLevel(20);
                player.applyStats(true);

                if (player.getMaxHealth() != player.getHealth()) {
                    player.setMaxHealth(player.getHealth());
                }

                if (player.getHealth() * HEALTH_REGEN < player.getHealth()) {
                    player.setHealth(player.getHealth() * HEALTH_REGEN);
                } else if (player.getHealth() * HEALTH_REGEN > player.getHealth()) {
                    player.setHealth(player.getHealth());
                }

                player.setWalkSpeed((float) (player.speed / 500));

                sendActionBar(player);
                playTime.put(player.getUniqueId(), playTime.getOrDefault(player.getUniqueId(), 0L) + 5L);
                setVariable(player.getUniqueId(), "PlayTime", playTime.get(player.getUniqueId()) + "");

                if (player.activePet != null && player.activePet.getArmorStand() == null) {
                    Pet.spawnPet(player, player.activePet);
                }
                if (player.activePet != null && player.activePet.getArmorStand().getLocation().distance(player.getLocation()) > 3)
                    new aifly(player.activePet.getArmorStand(), player, 1000).runTaskTimer(plugin, 0L, 1L);

//                if (player.getActivePet() != null && player.getActivePet().levelUp(player)) {
//
//                }
            }
        }, 0L, 5L);

        System.out.println("Skyblock Dragons plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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

//    private void setupChat() {
//        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
//        chat = rsp.getProvider();
//    }
//
//    private void setupPermissions() {
//        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
//        perms = rsp.getProvider();
//    }

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

    public static SkyblockDragons getPlugin() {
        return plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        new BukkitRunnable() {
            final Player player = e.getPlayer();
            @Override
            public void run() {
                ArrayList<WardrobeSlot> wardrobeSlots = new ArrayList<>();
                for (int i = 0; i < 18; i++) {
                    wardrobeSlots.add(new WardrobeSlot(
                            i,
                            (ItemStack) SkyblockDragons.getSerializer().deserialize(StorageUtil.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 0), "null"), null),
                            (ItemStack) SkyblockDragons.getSerializer().deserialize(StorageUtil.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 1), "null"), null),
                            (ItemStack) SkyblockDragons.getSerializer().deserialize(StorageUtil.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 2), "null"), null),
                            (ItemStack) SkyblockDragons.getSerializer().deserialize(StorageUtil.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 3), "null"), null)
                    ));
                }

                SkyblockDragons.players.put(player.getUniqueId(), new PlayerSD(
                        player,
                        new Skill(
                                new FarmingSkill(Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "Farming", 1, "0")), Double.parseDouble(StorageUtil.getVariableValue(player.getUniqueId(), "Farming", 2, "0"))),
                                new MiningSkill(Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "Mining", 1, "0")), Double.parseDouble(StorageUtil.getVariableValue(player.getUniqueId(), "Mining", 2, "0"))),
                                new CombatSkill(Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "Combat", 1, "0")), Double.parseDouble(StorageUtil.getVariableValue(player.getUniqueId(), "Combat", 2, "0"))),
                                new ForagingSkill(Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "Foraging", 1, "0")), Double.parseDouble(StorageUtil.getVariableValue(player.getUniqueId(), "Foraging", 2, "0"))),
                                new FishingSkill(Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "Fishing", 1, "0")), Double.parseDouble(StorageUtil.getVariableValue(player.getUniqueId(), "Fishing", 2, "0"))),
                                new EnchantingSkill(Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "Enchanting", 1, "0")), Double.parseDouble(StorageUtil.getVariableValue(player.getUniqueId(), "Enchanting", 2, "0"))),
                                new AlchemySkill(Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "Alchemy", 1, "0")), Double.parseDouble(StorageUtil.getVariableValue(player.getUniqueId(), "Alchemy", 2, "0"))),
                                new TamingSkill(Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "Taming", 1, "0")), Double.parseDouble(StorageUtil.getVariableValue(player.getUniqueId(), "Taming", 2, "0"))),
                                new DungeoneeringSkill(Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "Dungeoneering", 1, "0")), Double.parseDouble(StorageUtil.getVariableValue(player.getUniqueId(), "Dungeoneering", 2, "0")))
                        ), new Wardrobe(wardrobeSlots),
                        null
                ));

                loadPlayerData(player);
            }
        }.runTaskLater(plugin, 1L);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        players.remove(e.getPlayer().getUniqueId());
    }
}
