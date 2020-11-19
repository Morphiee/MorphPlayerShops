package net.naturva.morphie.mps.util.playerdata;

import net.naturva.morphie.mps.MorphPlayerShops;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.util.Date;
import java.util.UUID;

public class PlayerDataCleaner {
    private MorphPlayerShops plugin;

    public PlayerDataCleaner(MorphPlayerShops plugin) {
        this.plugin = plugin;
    }

    public void cleanPlayerData() {
        if (plugin.getConfig().getBoolean("Settings.AutoDeletePlayerFiles.Enabled")) {
            File data = new File(plugin.getDataFolder() + File.separator + "PlayerData");
            File path = new File(data.getPath());
            File dir = new File(path.toString());
            File[] directoryListing = dir.listFiles();

            if (directoryListing != null) {
                int filesDeleted = 0;
                for (File child : directoryListing) {
                    String[] childUUID = child.getName().split("[.]");
                    UUID uuid1 = UUID.fromString(childUUID[0]);
                    OfflinePlayer player1 = Bukkit.getOfflinePlayer(uuid1);
                    Date date = new Date(player1.getLastPlayed());
                    Date systemDate = new Date(System.currentTimeMillis());
                    long startTime = date.getTime();
                    long endTime = systemDate.getTime();
                    long diffTime = endTime - startTime;
                    long diffDays = diffTime / (1000 * 60 * 60 * 24);
                    if (diffDays >= plugin.getConfig().getInt("Settings.AutoDeletePlayerFiles.DaysTillDeletion")) {
                        if (!new PlayerDataManager(plugin).getBoolean(uuid1, "PlayerData.Shop")) {
                            child.delete();
                            filesDeleted++;
                        }
                    }
                }
                if (filesDeleted != 0) {
                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Cleaner &8>> &aSuccessfully cleared &3" + filesDeleted + " &afiles!"));
                } else {
                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Cleaner &8>> &cNo files cleared!"));
                }
            }
        }
    }
}
