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
import me.maxiiiiii.skyblockdragons.pet.Pet;
import me.maxiiiiii.skyblockdragons.pet.PetCommand;
import me.maxiiiiii.skyblockdragons.pet.PetListener;
import me.maxiiiiii.skyblockdragons.pet.PetTabComplete;
import me.maxiiiiii.skyblockdragons.purse.PurseCommand;
import me.maxiiiiii.skyblockdragons.purse.PurseTabCompletion;
import me.maxiiiiii.skyblockdragons.reforge.ReforgeCommand;
import me.maxiiiiii.skyblockdragons.skill.Skill;
import me.maxiiiiii.skyblockdragons.skill.SkillAdminCommand;
import me.maxiiiiii.skyblockdragons.skill.SkillListener;
import me.maxiiiiii.skyblockdragons.skill.SkillMenuCommand;
import me.maxiiiiii.skyblockdragons.skill.Skills.*;
import me.maxiiiiii.skyblockdragons.stat.MyPlayer;
import me.maxiiiiii.skyblockdragons.stat.OnSlotChange;
import me.maxiiiiii.skyblockdragons.stat.PlayerData;
import me.maxiiiiii.skyblockdragons.stat.StatCommand;
import me.maxiiiiii.skyblockdragons.storage.StorageUtil;
import me.maxiiiiii.skyblockdragons.util.*;
import me.maxiiiiii.skyblockdragons.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeCommand;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeListener;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeSlot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import static me.maxiiiiii.skyblockdragons.Functions.loadPlayerData;
import static me.maxiiiiii.skyblockdragons.Functions.numberToItemSlot;
import static me.maxiiiiii.skyblockdragons.Functions.sendActionBar;
import static me.maxiiiiii.skyblockdragons.Functions.setScoreboardScores;
import static me.maxiiiiii.skyblockdragons.storage.StorageUtil.setVariable;

public final class SkyblockDragons extends JavaPlugin implements Listener {
    public static final HashMap<UUID, PlayerData> players = new HashMap<>();
    private static final ArrayList<MyPlayer> myPlayers = new ArrayList<>();
    public static final HashMap<UUID, Double> purses = new HashMap<>();
    public static final HashMap<UUID, Long> bits = new HashMap<>();
    public static final HashMap<UUID, Long> playTime = new HashMap<>();
    public static final HashMap<UUID, ArrayList<Inventory>> playerGoBack = new HashMap<>();
    public static boolean disablePlayTime = false;

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

        for (Hologram holo: HologramsAPI.getHolograms(this)) {
            holo.delete();
        }

        try {
            StorageUtil.loadVariables();
        } catch (IOException e) {
            e.printStackTrace();
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
        getCommand("JavaMenu").setExecutor(new JavaMenu());
        getCommand("JavaItem").setExecutor(new ItemCommand());
        getCommand("JavaItem").setTabCompleter(new ItemCommandTabComplete());
        getCommand("JavaPlugin").setExecutor(new JavaPluginCommand());
        getCommand("JavaPlugin").setTabCompleter(new JavaPluginTabCompletion());
        getCommand("JavaPurse").setExecutor(new PurseCommand());
        getCommand("JavaPurse").setTabCompleter(new PurseTabCompletion());
        getCommand("JavaBits").setExecutor(new BitsCommand());
        getCommand("JavaBits").setTabCompleter(new BitsTabCompletion());
        getCommand("JavaAnvil").setExecutor(new AnvilCommand());
        getCommand("ReforgeMenu").setExecutor(new ReforgeCommand());
        getCommand("JavaBook").setExecutor(new BookCommand());
        getCommand("JavaBook").setTabCompleter(new BookCommandTabCompletion());
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

            myPlayers.add(new MyPlayer(
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

        final double HEALTH_REGEN = 1.02;

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (PlayerData playerData : players.values()) {
                if (!playerData.getPlayer().getWorld().getName().equalsIgnoreCase("work")) continue;

                setScoreboardScores(playerData.getPlayer());

                playerData.getPlayer().setFoodLevel(20);
                playerData.applyStats(true);

                if (playerData.getPlayer().getMaxHealth() != playerData.getHealth()) {
                    playerData.getPlayer().setMaxHealth(playerData.getHealth());
                }

                if (playerData.getPlayer().getHealth() * HEALTH_REGEN < playerData.getHealth()) {
                    playerData.getPlayer().setHealth(playerData.getPlayer().getHealth() * HEALTH_REGEN);
                } else if (playerData.getPlayer().getHealth() * HEALTH_REGEN > playerData.getHealth()) {
                    playerData.getPlayer().setHealth(playerData.getHealth());
                }

                playerData.getPlayer().setWalkSpeed((float) (playerData.getSpeed() / 500));

                sendActionBar(playerData.getPlayer());
                playTime.put(playerData.getPlayer().getUniqueId(), playTime.getOrDefault(playerData.getPlayer().getUniqueId(), 0L) + 5L);
                setVariable(playerData.getPlayer().getUniqueId(), "PlayTime", playTime.get(playerData.getPlayer().getUniqueId()) + "");

                if (playerData.getActivePet() != null && playerData.getActivePet().getArmorStand() == null) {
                    Pet.spawnPet(playerData.getPlayer(), playerData.getActivePet());
                }
                if (playerData.getActivePet() != null && playerData.getActivePet().getArmorStand().getLocation().distance(playerData.getPlayer().getLocation()) > 3)
                    new aifly(playerData.getActivePet().getArmorStand(), playerData.getPlayer(), 1000).runTaskTimer(plugin, 0L, 1L);
            }
        }, 0L, 5L);

        System.out.println("Hypixel Items plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Collection<? extends Player> getOnlinePlayers() {
        return Bukkit.getOnlinePlayers();
    }

    public static MyPlayer getPlayer(String name) {
        for (MyPlayer myPlayer : myPlayers) {
            if (myPlayer.getName().equals(name)) {
                return myPlayer;
            }
        }
        return null;
    }

    public static MyPlayer getPlayer(UUID uuid) {
        for (MyPlayer myPlayer : myPlayers) {
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

                SkyblockDragons.myPlayers.add(new MyPlayer(
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
        Player player = e.getPlayer();
        myPlayers.removeIf(myPlayer -> myPlayer.getPlayer().getUniqueId() == player.getUniqueId());

        players.remove(e.getPlayer().getUniqueId());
    }
}
