package net.naturva.morphie.mps.commands.playercommands;

import net.naturva.morphie.mps.MorphPlayerShops;
import net.naturva.morphie.mps.util.MessageMethods;
import org.bukkit.entity.Player;

public class HelpCommand {

    private MorphPlayerShops plugin;

    public HelpCommand(MorphPlayerShops plugin) {
        this.plugin = plugin;
    }

    public void runHelp(Player player) {

        if (player.hasPermission("mshops.ps")) {
            player.sendMessage("");
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.Header")));
            player.sendMessage("");
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.Title").replace("VERSION", plugin.getVersion())));
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.Alias")));
            player.sendMessage("");
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsSet")));
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsMenu")));
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsTp")));
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsPlayer")));
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsLock")));
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsRemove")));
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsAdvertise")));
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsLore")));
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsLoreDelete")));
            player.sendMessage("");
            if (player.hasPermission("mshops.admin") || player.hasPermission("mshops.reload")) {
                player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsReset")));
                player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.PsReload")));
            }
            player.sendMessage("");
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Commands.Footer")));
            player.sendMessage("");
        } else {
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + new MessageMethods(plugin).getMessage("NoPermsMessage")));
        }
    }
}
