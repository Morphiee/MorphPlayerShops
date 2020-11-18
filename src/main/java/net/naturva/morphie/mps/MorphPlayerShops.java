package net.naturva.morphie.mps;

import net.naturva.morphie.mps.util.MessageMethods;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MorphPlayerShops extends JavaPlugin {

    public void onEnable() {
        createConfig();
    }

    public void onDisable() {

    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getServer().getConsoleSender().sendMessage(MessageMethods.addColor("&bConfig&8: &aGenerating config."));
                getConfig().options().copyDefaults(true);
                saveDefaultConfig();
            } else {
                getServer().getConsoleSender().sendMessage(MessageMethods.addColor("&bConfig&8: &aLoading config."));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
