package net.naturva.morphie.mps.util.playerdata;

import net.naturva.morphie.mps.MorphPlayerShops;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PlayerDataManager {
    private MorphPlayerShops plugin;

    public PlayerDataManager(MorphPlayerShops plugin) {
        this.plugin = plugin;
    }

    public void setBoolean(UUID uuid, String string, Boolean b) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        fc.set(string, Boolean.valueOf(b));
        try
        {
            fc.save(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setString(UUID uuid, String string, String string2) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        fc.set(string, String.valueOf(string2));
        try
        {
            fc.save(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void removeShop(UUID uuid) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        fc.set("PlayerData.location", null);;
        try
        {
            fc.save(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addStringList(UUID uuid, String string, String lore) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        List<String> list2 = getStringList(uuid, string);
        list2.add(lore);
        fc.set(string, list2);
        try
        {
            fc.save(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setStringList(Player player, UUID uuid, String string, List<String> lore) {
        File file = getPlayerFile(player.getUniqueId());
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        fc.set(string, lore);
        try
        {
            fc.save(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setDouble(UUID uuid, String string, Double i) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        fc.set(string, Double.valueOf(i));
        try
        {
            fc.save(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setFloat(UUID uuid, String string, Float i) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        fc.set(string, Float.valueOf(i));
        try
        {
            fc.save(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean getBoolean(UUID uuid, String string) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        return fc.getBoolean(string);
    }

    public String getString(UUID uuid, String string) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        return fc.getString(string);
    }

    public List<String> getStringList(UUID uuid, String string) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        return fc.getStringList(string);
    }

    public double getDouble(UUID uuid, String string) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        return fc.getDouble(string);
    }

    public float getFloat(UUID uuid, String string) {
        File file = getPlayerFile(uuid);
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        return fc.getInt(string);
    }

    public File getPlayerFile(UUID uuid) {
        File playerFile = new File(this.plugin.getDataFolder() + File.separator + "PlayerData", uuid + ".yml");
        FileConfiguration playerCFG = YamlConfiguration.loadConfiguration(playerFile);
        if (!playerFile.exists()) {
            try {
                playerCFG.save(playerFile);
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return playerFile;
    }
}
