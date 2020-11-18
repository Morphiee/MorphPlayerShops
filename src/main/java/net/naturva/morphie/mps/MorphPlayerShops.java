package net.naturva.morphie.mps;

import jdk.nashorn.internal.objects.annotations.Getter;
import net.naturva.morphie.mps.files.Messages;
import net.naturva.morphie.mps.util.MessageMethods;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MorphPlayerShops extends JavaPlugin {

    public Messages messagescfg;
    private String version = "2.0.0";

    public void onEnable() {
        loadConfigManager();
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
}
