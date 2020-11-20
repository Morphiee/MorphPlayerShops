package net.naturva.morphie.mps.util;

import net.naturva.morphie.mps.MorphPlayerShops;
import org.bukkit.ChatColor;

import java.util.List;

public class MessageMethods {

    private MorphPlayerShops plugin;

    public MessageMethods(MorphPlayerShops plugin) {
        this.plugin = plugin;
    }

    public static String addColor(String message) {
        if (message == null) {
            return null;
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getMessage(String string) {
        return this.plugin.messagescfg.getMessagesCgf().getString(string);
    }

    public List<String> getMessageList(String string) {
        return this.plugin.messagescfg.getMessagesCgf().getStringList(string);
    }
}
