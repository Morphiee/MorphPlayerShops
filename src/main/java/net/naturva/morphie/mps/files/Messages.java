package net.naturva.morphie.mps.files;

import jdk.nashorn.internal.objects.annotations.Getter;
import net.naturva.morphie.mps.MorphPlayerShops;
import net.naturva.morphie.mps.util.MessageMethods;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Messages {

    private MorphPlayerShops plugin = (MorphPlayerShops) MorphPlayerShops.getPlugin(MorphPlayerShops.class);
    private FileConfiguration messagesCFG;
    private File messagesFile;

    public void setup() {
        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }
        this.messagesFile = new File(this.plugin.getDataFolder(), "messages.yml");
        if (!this.messagesFile.exists()) {
            try {
                this.messagesFile.createNewFile();

                this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);

                this.messagesCFG.addDefault("Prefix", "&8[&a&l✔&8] &3&lMorphPShops &8&l➙ ");
                this.messagesCFG.addDefault("ErrorPrefix", "&8[&c&l✕&8] &3&lMorphPShops &8&l➙ ");

                this.messagesCFG.addDefault("NoPermission", "&7Invalid permissions!");
                this.messagesCFG.addDefault("NoPlayer", "&7Player does not exist!");
                this.messagesCFG.addDefault("NoSetShop", "&7You haven't set a shop yet! &8(&a/ps set&8)");
                this.messagesCFG.addDefault("NoPlayerSetShop", "&7This player hasn't set a shop yet!");
                this.messagesCFG.addDefault("NoMoney", "&7Invalid funds! &8(&3Cost&8: &aCOST&8)");
                this.messagesCFG.addDefault("NoArgs", "&7Invalid args. Use &a/ps &7for commands!");
                this.messagesCFG.addDefault("PlayerNameChange", "&7Player shop cannot be found! Name change?");

                this.messagesCFG.addDefault("Advert.Message", "&7Advert successfully purchased for &bCOST&7! &7Be sure to check out &b/ps advertise lore &7to add a advert message!");
                this.messagesCFG.addDefault("Advert.AlreadyPurchased", "&7You already have an advert! Use &b/ps advertise lore &7to add a personal message to your advert!");
                this.messagesCFG.addDefault("Advert.ListFull", "&7Advert list is currently full! Wait until there is a advert reset to try agian.");
                this.messagesCFG.addDefault("Advert.ResetBroadcast", "&7Adverts have been reset! Use &b/ps advertise &7 to buy a new spot!");
                this.messagesCFG.addDefault("Advert.NoAdverts", "&7There are currently no adverts!");
                this.messagesCFG.addDefault("Advert.Usage", "&7Use &b/ps advertise &7to buy one!");
                this.messagesCFG.addDefault("Advert.LoreAdded", "&7Added lore line!");
                this.messagesCFG.addDefault("Advert.LoreFull", "&7You have 3 lore lines set! &8(&b/ps advertise lore delete &7to reset them!&8)");
                this.messagesCFG.addDefault("Advert.LoreDeleted", "&7Lore successfully deleted!");
                this.messagesCFG.addDefault("Advert.ConfirmMessage", "&7Type &b/ps advertise &7again to confirm! This will cost&8: &bCOST");

                this.messagesCFG.addDefault("Commands.Header", "&7&m--&8&m-&7&m----------------------------------------------&8&m-&7&m--");
                this.messagesCFG.addDefault("Commands.Title", "&3&lMorphPlayerShops &8» &aVERSION");
                this.messagesCFG.addDefault("Commands.Alias", "&3&lAliases&8: &aps, pshop, ms, mshop");
                this.messagesCFG.addDefault("Commands.Ps", "&8» &a/ps &8- &7Shows this text menu.");
                this.messagesCFG.addDefault("Commands.PsSet", "&8» &a/ps set &8- &7Sets your player shop at your current location.");
                this.messagesCFG.addDefault("Commands.PsMenu", "&8» &a/ps menu &8 - &7Opens the shop advertisement menu.");
                this.messagesCFG.addDefault("Commands.PsTp", "&8» &a/ps tp &8- &7Teleports you to your shop. &8(&7If one is set.&8)");
                this.messagesCFG.addDefault("Commands.PsPlayer", "&8» &a/ps <player> &8- &7Teleports you to another player's shop. &8(&7If one is set.&8)");
                this.messagesCFG.addDefault("Commands.PsLock", "&8» &a/ps lock &8- &7Locks OR unlocks your shop. &8(&7Toggle&8)");
                this.messagesCFG.addDefault("Commands.PsRemove", "&8» &a/ps remove &8- &7Deletes your shop &bAND &7advertisement!");
                this.messagesCFG.addDefault("Commands.PsAdvertise", "&8» &a/ps advertise &8- &7Buy an advert spot for a set cost.");
                this.messagesCFG.addDefault("Commands.PsLore", "&8» &a/ps lore <loreline> <lore> &8- &7Adds a custom message to your advert. &8(&7Only up to 3 lines!&8)");
                this.messagesCFG.addDefault("Commands.PsLoreDelete", "&8» &a/ps lore delete &8- &7Reset all lore lines!");
                this.messagesCFG.addDefault("Commands.PsReset", "&8» &3/ps reset &8- &7Resets the advert list!");
                this.messagesCFG.addDefault("Commands.PsReload", "&8» &3/ps reload &8- &7Reloads all plugin files!");
                this.messagesCFG.addDefault("Commands.Footer", "&7&m--&8&m-&7&m----------------------------------------------&8&m-&7&m--");

                this.messagesCFG.addDefault("LockedShopMessage", "&7This user's shop is currently locked!");
                this.messagesCFG.addDefault("LockedMessage", "&7Shop has been locked, use &a/ps lock &7to unlock!");
                this.messagesCFG.addDefault("UnLockedMessage", "&7Shop has been unlocked, use &a/ps lock &7to lock!");

                List<String> list = new ArrayList<String>();
                list.add("&9&lVersion&8: &7VERSION");
                list.add("&bCode Contributors&8:");
                list.add("&8- &7Morphie");
                list.add("&8- &7Teleports");
                list.add("&b&oClick for spigot link!");

                List<String> list2 = new ArrayList<String>();
                list2.add(" ");
                list2.add("&7Below you can click on a");
                list2.add("&7player shop to be teleported");
                list2.add("&7to the shops location. You can");
                list2.add("&7also use the arrows below to");
                list2.add("&7navigate the shops GUI.");
                list2.add(" ");

                List<String> list3 = new ArrayList<String>();
                list3.add(" ");
                list3.add("&8« &7PREV_TAG &8| &fCURRENT_TAG &8| &7NEXT_TAG &8»");
                list3.add(" ");
                list3.add("&7Click to cycle through availible tags!");
                list3.add(" ");

                List<String> list4 = new ArrayList<String>();
                list4.add(" ");
                list4.add("&7Click to go back one page!");
                list4.add(" ");

                List<String> list5 = new ArrayList<String>();
                list5.add(" ");
                list5.add("&7Click to go to the next page!");
                list5.add(" ");

                this.messagesCFG.addDefault("Menu.PlayerShops.Title", "&8» &3&lMorphPShops&8: &aPlayer Shops &8«");
                this.messagesCFG.addDefault("Menu.PlayerShops.ShopTitle", "&3&lPLAYER's &ashop.");
                this.messagesCFG.addDefault("Menu.PlayerShops.InfoBook", "&3&lInformation&8:");
                this.messagesCFG.addDefault("Menu.PlayerShops.InfoBookLore", list2);
                this.messagesCFG.addDefault("Menu.PlayerShops.TagFilterTitle", "&3&lTag Filter&8:");
                this.messagesCFG.addDefault("Menu.PlayerShops.TagFilterLore", list3);
                this.messagesCFG.addDefault("Menu.PlayerShops.PageBackTitle", "&3&lPrevious Page&8:");
                this.messagesCFG.addDefault("Menu.PlayerShops.PageBackLore", list4);
                this.messagesCFG.addDefault("Menu.PlayerShops.PageTitle", "&3&lCurrent Page&8: &aPAGE");
                this.messagesCFG.addDefault("Menu.PlayerShops.PageNextTitle", "&3&lNext Page&8:");
                this.messagesCFG.addDefault("Menu.PlayerShops.PageNextLore", list5);
                this.messagesCFG.addDefault("Menu.PlayerShops.DefaultShopLore", "&7Come to my shop I've got much product!");
                this.messagesCFG.addDefault("Menu.PlayerShops.LockedShop", "&8[&c&l✕&8] &7Shop currently locked.");
                this.messagesCFG.addDefault("Menu.CreditsTitle", "&9&lCredits&8:");
                this.messagesCFG.addDefault("Menu.CreditsLore", list);

                this.messagesCFG.addDefault("ReloadMessage", "&7Plugin files successfully reloaded.");
                this.messagesCFG.addDefault("ReloadArgs", "&7Invalid arguments! &8(&a/ps reload&8)");

                this.messagesCFG.addDefault("RemoveConfirmMessage", "&7Type &a/ps remove &7again to confirm! This will &adelete &7your advertisement &aAND &7playershop.");
                this.messagesCFG.addDefault("RemoveMessage", "&7Your shop has been successfully removed!");

                this.messagesCFG.addDefault("SetShop.Message", "&7Your shop has been successfully created!");
                this.messagesCFG.addDefault("SetShop.ConfirmMessage", "&7Type &a/ps set &7again to confirm!");
                this.messagesCFG.addDefault("SetShop.ConfirmMessage", "&7Set shop confirmation timed out! Type &a/ps set &7to set your shop.");
                this.messagesCFG.addDefault("SetShop.ConfirmPriceMessage", "&7Type &a/ps set &7again to confirm! This will cost&8: &aCOST");

                this.messagesCFG.addDefault("ToShopMessage", "&7Teleporting to &aPLAYER's &7shop.");
                this.messagesCFG.addDefault("ToOwnShopMessage", "&7Teleporting to &ayour &7shop.");

                this.messagesCFG.addDefault("UnsafeShop.LavaNearMessage", "&7Unsafe shop location! &8(&aTo close to lava.&8)");
                this.messagesCFG.addDefault("UnsafeShop.FloatingBlockMessage", "&7Unsafe shop location! &8(&aOn a floating block.&8)");

                this.messagesCFG.options().copyDefaults(true);
                saveMessages();
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(MessageMethods.addColor("&cCould not create the messages.yml file"));
            }
        }
        this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);
    }

    public void saveMessages() {
        try {
            this.messagesCFG.save(this.messagesFile);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(MessageMethods.addColor("&cCould not save the messages.yml file"));
        }
    }

    @Getter
    public FileConfiguration getMessagesCgf() {
        return messagesCFG;
    }
}
