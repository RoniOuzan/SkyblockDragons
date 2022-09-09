package me.maxiiiiii.skyblockdragons.dungeon;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.dungeon.events.PlayerOpenSecretEvent;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class Dungeon implements Listener {
    private final DungeonFloor floor;
    private final DungeonWorld world;
    private final DungeonParty party;

    private long startedAt;

    private int secrets;

    public Dungeon(DungeonFloor floor, DungeonWorld world, List<PlayerSD> players) {
        this.floor = floor;
        this.world = world;
        this.party = new DungeonParty(players);

        this.startedAt = 0;

        this.secrets = 0;
        Bukkit.getServer().getPluginManager().registerEvents(this, SkyblockDragons.plugin);
    }

    public void start() {
        this.killAllEntities();
        this.resetWorld();

        this.teleportPlayers();
        AtomicBoolean start = new AtomicBoolean(false);
        Functions.Loop(60, 20L, i -> {
            if (!this.party.stream().allMatch(DungeonPlayer::isReady)) {
                if (start.get()) return;
                start.set(true);
                this.startDungeon();
            }

            int times = 60 - i;
            if (times == 60 || times == 30 || times == 15 || times <= 10) {
                this.party.send(ChatColor.YELLOW + "You have " + ChatColor.RED + times + " " + ChatColor.YELLOW + "seconds to select your classes.");
            }
            if (i == 59) {
                Functions.Wait(20L, () -> {
                    if (this.party.stream().allMatch(DungeonPlayer::isReady)) {
                        this.startDungeon();
                    } else {
                        this.kickAll();
                    }
                });
            }
        });
    }

    public void startDungeon() {
        this.floor.spawnEntities();
        // TODO open the door

        this.startedAt = System.currentTimeMillis();
    }

    public void kickAll() {
        for (DungeonPlayer dungeonPlayer : this.party) {
            Warp.HUB.warp(dungeonPlayer);
        }
        this.party.send(ChatColor.RED + "Not everyone selected ready so you got kicked out form the dungeon.");
    }

    public void teleportPlayers() {
        this.party.forEach(p -> p.teleport(this.world.getWarp().getLocation()));
    }

    public void resetWorld() { // TODO @LidanTheGamer_ whatever you want to do here

    }

    public void killAllEntities() {
        for (LivingEntity entity : this.world.getWorld().getLivingEntities()) {
            if (entity instanceof Player) continue;
            entity.remove();
        }
    }

    public DungeonPlayer getPlayer(UUID uuid) {
        for (DungeonPlayer dungeonPlayer : this.party) {
            if (dungeonPlayer.getUniqueId() == uuid) {
                return dungeonPlayer;
            }
        }
        return null;
    }

    @EventHandler
    public void onClickOnSecret(PlayerInteractEvent e) {
        if (e.getPlayer().getWorld() != this.world.getWorld()) return;
        if (e.getClickedBlock().getType() == Material.CHEST) {
            if (this.getPlayer(e.getPlayer().getUniqueId()) == null) {
                e.getPlayer().sendMessage(ChatColor.RED + "You can't open this secret because your not a part of this dungeon!");
            } else {
                this.secrets++;
                PlayerOpenSecretEvent event = new PlayerOpenSecretEvent(this, this.getPlayer(e.getPlayer().getUniqueId()), e.getClickedBlock().getLocation());
                Bukkit.getServer().getPluginManager().callEvent(event);
                this.party.send(ChatColor.GREEN + e.getPlayer().getName() + " Found Secret"); // TODO send message that someone found secret
            }
        }
    }

    public void view(PlayerSD player) {
        new View(player);
    }

    public class View extends Menu {
        public View(PlayerSD player) {
            super(player, "Dungeon Class", 6, InventoryGlassType.ALL, true);
        }

        @Override
        public void update() {
            this.setItem(49, CLOSE);

            this.setItem(11, createItem(Material.SPLASH_POTION, ChatColor.GREEN + "Healer", "CLASS_HEALER", "", ChatColor.YELLOW + "Click to select class!"));
            this.setItem(12, createItem(Material.STICK, ChatColor.GREEN + "Mage", "CLASS_MAGE", "", ChatColor.YELLOW + "Click to select class!"));
            this.setItem(13, createItem(Material.IRON_SWORD, ChatColor.GREEN + "Berserker", "CLASS_BERSERKER", "", ChatColor.YELLOW + "Click to select class!"));
            this.setItem(14, createItem(Material.BOW, ChatColor.GREEN + "Archer", "CLASS_ARCHER", "", ChatColor.YELLOW + "Click to select class!"));
            this.setItem(15, createItem(Material.IRON_CHESTPLATE, ChatColor.GREEN + "Tank", "CLASS_TANK", "", ChatColor.YELLOW + "Click to select class!"));

            if (party.size() == 1)
                setHeads(22);
            else if (party.size() == 2)
                setHeads(21, 23);
            else if (party.size() == 3)
                setHeads(21, 22, 23);
            else if (party.size() == 4)
                setHeads(20, 21, 23, 24);
            else
                setHeads(20, 21, 22, 23, 24);
        }

        private void setHeads(int... slots) {
            int i = 0;
            for (int slot : slots) {
                DungeonPlayer player = party.get(i);
                this.setItem(slot, Functions.applyHead(createItem(Material.SKULL_ITEM, 3, ChatColor.GREEN + player.getName(), "PLAYER_" + player.getName(), "", ChatColor.YELLOW + "Click to ready!"), player));
                if (player.isReady()) {
                    this.setItem(slot + 9, createItem(Material.STAINED_GLASS_PANE, 9, ChatColor.GREEN + "Ready", "READY"));
                } else {
                    this.setItem(slot + 9, createItem(Material.STAINED_GLASS_PANE, 14, ChatColor.RED + "Not Ready", "NOT_READY"));
                }
                i++;
            }
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (this.getNBT(e.getCurrentItem()).contains("PLAYER_")) {
                DungeonPlayer player = Dungeon.this.getPlayer(e.getWhoClicked().getUniqueId());
                if (player == null) return;
                if (this.getNBT(e.getCurrentItem()).split("_")[1].equals(player.getName())) {
                    player.toggleReady();
                    this.update();
                }
            } else if (this.getNBT(e.getCurrentItem()).contains("CLASS_")) {
                DungeonPlayer player = Dungeon.this.getPlayer(e.getWhoClicked().getUniqueId());
                if (player == null) return;
                player.setDungeonClass(DungeonClass.valueOf(this.getNBT(e.getCurrentItem()).split("_")[1]));
                player.setReady(true);
                this.update();
            }
        }
    }
}
