package net.naturva.morphie.mps.commands.playercommands;

import net.md_5.bungee.api.ChatColor;
import net.naturva.morphie.mps.MorphPlayerShops;
import net.naturva.morphie.mps.util.MessageMethods;
import net.naturva.morphie.mps.util.ShopMethods;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;

public class SetCommand {
    private MorphPlayerShops plugin;

    public SetCommand(MorphPlayerShops plugin) {
        this.plugin = plugin;
    }

    public HashMap<Player, Boolean> setshop = new HashMap<Player, Boolean>();

    public void setCommand(Player player, String[] args) {
        if (player.hasPermission("ms.setshop")) {
            double bal = plugin.getEcon().getBalance(player);
            Location loc = player.getLocation();
            if (new ShopMethods(plugin).checkDangers(player, loc, 3) == false) {
                if (plugin.getConfig().getBoolean("Settings.SetShopPrice.Enabled")) {
                    if (this.plugin.getConfig().getBoolean("Settings.CommandConfirmation.SetShop")) {
                        if (!(setshop.containsKey(player))) {
                            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + new MessageMethods(plugin).getMessage("SetShop.ConfirmMessage")));
                            setshop.put(player, true);
                            BukkitScheduler schedule = Bukkit.getScheduler();
                            schedule.scheduleSyncDelayedTask(plugin, new Runnable() {
                                public void run() {
                                    setshop.remove(player);
                                    player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + new MessageMethods(plugin).getMessage("SetShop.ConfirmCanceledMessage")));
                                }
                            }, 1000L);
                        } else {
                            setshop.remove(player);
                            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Prefix") + new MessageMethods(plugin).getMessage("SetShop.Message")));
                            new ShopMethods(plugin).setPlayerShopLoc(player, loc);
                        }
                    } else {
                        player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Prefix") + new MessageMethods(plugin).getMessage("SetShop.Message")));
                        new ShopMethods(plugin).setPlayerShopLoc(player, loc);
                    }
                } else {
                    if (bal >= plugin.getConfig().getDouble("setshop-price")) {
                        if (!(setshop.containsKey(player))) {
                            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + new MessageMethods(plugin).getMessage("SetShop.ConfirmPriceMessage").replace("COST", this.plugin.getConfig().getString("settings.setshop-price"))));
                            setshop.put(player, true);
                            BukkitScheduler schedule = Bukkit.getScheduler();
                            schedule.scheduleSyncDelayedTask(plugin, new Runnable() {
                                public void run() {
                                    if (setshop.containsKey(player)) {
                                        setshop.remove(player);
                                    }
                                }
                            }, 1000L);

                        } else {
                            MorphPlayerShops.getEconomy().withdrawPlayer(player, plugin.getConfig().getDouble("settings.setshop-price"));
                            setshop.remove(player);
                            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("Prefix") + new MessageMethods(plugin).getMessage("SetShop.Message")));
                            new ShopMethods(plugin).setPlayerShopLoc(player, loc);
                        }
                    } else {
                        player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + new MessageMethods(plugin).getMessage("NoMoney").replaceAll("COST", plugin.getConfig().getString("settings.setshop-price"))));
                    }
                }
            }
        } else {
            player.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + new MessageMethods(plugin).getMessage("NoPermission")));
        }
    }
}
