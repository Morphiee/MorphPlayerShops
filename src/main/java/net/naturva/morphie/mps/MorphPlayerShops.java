package net.naturva.morphie.mps;

import jdk.nashorn.internal.objects.annotations.Getter;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.naturva.morphie.mps.commands.CommandHandler;
import net.naturva.morphie.mps.events.PlayerDataEvent;
import net.naturva.morphie.mps.files.Messages;
import net.naturva.morphie.mps.util.MessageMethods;
import net.naturva.morphie.mps.util.playerdata.PlayerDataCleaner;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class MorphPlayerShops extends JavaPlugin {

    public Messages messagescfg;
    private String version = "2.0.0";

    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    PluginManager pm = Bukkit.getServer().getPluginManager();

    public void onEnable() {
        Objects.requireNonNull(getCommand("ms")).setExecutor(new CommandHandler(this));
        pm.registerEvents(new PlayerDataEvent(this), this);
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3MorphPlayerShops &8- &a" + this.version));
        loadConfigManager();
        createConfig();
        if (!setupEconomy()) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Economy&8: &cNot Found!"));
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Permissions&8: &cNot Found!"));
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Chat&8: &cNot Found!"));
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlugin disabled due to no Vault dependecy found!"));
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3PlayerFiles&8: &aLoaded."));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Economy&8: &aVault Found."));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Permissions&8: &aVault Found."));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Chat&8: &aVault Found."));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Plugin Status&8: &aEnabled!"));
        if (this.getConfig().getBoolean("Settings.AutoDeletePlayerFiles.Enabled") == true) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3PlayerData Cleaner&8: &aChecking for old files."));
            new PlayerDataCleaner(this).cleanPlayerData();
        }
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));

    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3MorphPlayerShops &8- &a" + this.version));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Economy&8: &cDisabled."));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Permissions&8: &cDisabled."));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Chat&8: &cDisabled."));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Plugin Status&8: &cDisabled!"));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"));

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getServer().getConsoleSender().sendMessage(MessageMethods.addColor("&3Config&8: &aGenerating config."));
                getConfig().options().copyDefaults(true);
                saveDefaultConfig();
            } else {
                getServer().getConsoleSender().sendMessage(MessageMethods.addColor("&3Config&8: &aLoading config."));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadConfigManager() {
        this.messagescfg = new Messages();
        this.messagescfg.setup();
        getServer().getConsoleSender().sendMessage(MessageMethods.addColor("&3Config&8: &bLoading messages.yml"));
    }

    @Getter
    public String getVersion() {
        return version;
    }

    @Getter
    public Economy getEcon() {
        return econ;
    }
}
