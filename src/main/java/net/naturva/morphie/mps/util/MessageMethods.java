package net.naturva.morphie.mps.util;

import org.bukkit.ChatColor;

public class MessageMethods {

    public static String addColor(String message) {
        if (message == null) {
            return null;
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
