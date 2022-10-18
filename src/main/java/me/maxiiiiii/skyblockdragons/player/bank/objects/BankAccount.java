package me.maxiiiiii.skyblockdragons.player.bank.objects;

import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    public static boolean DISABLE = true;

    public enum Type {
        PERSONAL, COOP;

        @Override
        public String toString() {
            return Functions.setTitleCase(this.name());
        }
    }

    public PlayerSD player;
    public double personal;
    public double coop;
    public double limit;

    public BankAccount(PlayerSD player, double limit) {
        this.player = player;
        this.personal = Variables.get(player.getUniqueId(), "BankPersonal", 0, 0d);
        this.coop = Variables.get(player.getUniqueId(), "BankCoop", 0, 0d);

        this.limit = limit;
    }

    public double getCoopLimit() {
        return this.limit;
    }

    public double getPersonalLimit() {
        return this.limit / 5;
    }

    public void withdraw(double amount, BankAccount.Type type) {
        if (type == Type.COOP) {
            this.coopWithdraw(amount);
        } else {
            this.personalWithdraw(amount);
        }
    }

    public void deposit(double amount, BankAccount.Type type) {
        if (DISABLE){
            return;
        }
        if (type == Type.COOP) {
            this.coopDeposit(amount);
        } else {
            this.personalDeposit(amount);
        }
    }

    public void personalWithdraw(double amount) {
        if (amount <= 0) {
            player.sendMessage(ChatColor.RED + "You can't withdraw that amount!");
            return;
        }
        if (this.personal < amount) {
            SkyblockDragons.economy.depositPlayer(player, this.personal);
            this.personal = 0;
        } else {
            SkyblockDragons.economy.depositPlayer(player, amount);
            this.personal -= amount;
        }
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        player.sendMessage(ChatColor.GREEN + "You have withdrawed " + amount + " from the bank.");
        this.update();
    }

    public void personalDeposit(double amount) {
        if (DISABLE){
            return;
        }
        if (amount <= 0) {
            player.sendMessage(ChatColor.RED + "You can't deposit that amount!");
            return;
        }
        if (this.personal + amount > this.getPersonalLimit()) {
            amount = this.getPersonalLimit() - this.personal;
            SkyblockDragons.economy.withdrawPlayer(player, amount);
            this.personal = this.getPersonalLimit();
        } else {
            SkyblockDragons.economy.withdrawPlayer(player, amount);
            this.personal += amount;
        }
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        player.sendMessage(ChatColor.GREEN + "You have deposited " + amount + " from the bank.");
        this.update();
    }

    public void coopWithdraw(double amount) {
        if (amount <= 0) {
            player.sendMessage(ChatColor.RED + "You can't withdraw that amount!");
            return;
        }
        if (this.coop < amount) {
            SkyblockDragons.economy.depositPlayer(player, this.coop);
            this.coop = 0;
        } else {
            SkyblockDragons.economy.depositPlayer(player, amount);
            this.coop -= amount;
        }
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        player.sendMessage(ChatColor.GREEN + "You have withdrawed " + amount + " from the bank.");
        this.update();
    }

    public void coopDeposit(double amount) {
        if (DISABLE){
            return;
        }
        if (amount <= 0) {
            player.sendMessage(ChatColor.RED + "You can't deposit that amount!");
            return;
        }
        if (this.coop + amount > this.getCoopLimit()) {
            amount = this.getPersonalLimit() - this.coop;
            SkyblockDragons.economy.withdrawPlayer(player, amount);
            this.coop = this.getPersonalLimit();
        } else {
            SkyblockDragons.economy.withdrawPlayer(player, amount);
            this.coop += amount;
        }
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        player.sendMessage(ChatColor.GREEN + "You have deposited " + amount + " from the bank.");
        this.update();
    }

    public void update() {
        SkyblockDragons.logger.info(player.getName() + "'s Bank Saved!");
    }

    public void save() {
        Variables.set(player.getUniqueId(), "BankPersonal", 0, this.personal);
        Variables.set(player.getUniqueId(), "BankCoop", 0, this.coop);
    }
}
