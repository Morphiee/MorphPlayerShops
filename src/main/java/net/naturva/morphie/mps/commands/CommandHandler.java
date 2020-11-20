package net.naturva.morphie.mps.commands;

import net.naturva.morphie.mps.MorphPlayerShops;
import net.naturva.morphie.mps.commands.playercommands.HelpCommand;
import net.naturva.morphie.mps.util.MessageMethods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    private MorphPlayerShops plugin;

    public CommandHandler(MorphPlayerShops plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ps") || cmd.getName().equalsIgnoreCase("pshops") || cmd.getName().equalsIgnoreCase("ms") || cmd.getName().equalsIgnoreCase("msshops")) {
            if (args.length == 0) {
                // Player Only
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    new HelpCommand(plugin).runHelp(player);
                } else {
                    sender.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + " &cThis command can only be run by a player!"));
                }
                return true;
            } else {
                sender.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + new MessageMethods(plugin).getMessage("InvalidArgsMessage")));
                return true;
            }
        }
        return false;
    }

}
