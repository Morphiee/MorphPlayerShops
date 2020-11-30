package net.naturva.morphie.mps.util;

import net.naturva.morphie.mps.MorphPlayerShops;
import net.naturva.morphie.mps.util.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ShopMethods {
    private MorphPlayerShops plugin;

    public ShopMethods(MorphPlayerShops plugin) {
        this.plugin = plugin;
    }

    public static List<Material> getNearbyBlocks(Location location, int radius) {
        List<Material> blocks = new ArrayList<Material>();
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z).getType());
                }
            }
        }
        return blocks;
    }

    public boolean checkDangers(Player p, Location loc, int radius) {
        boolean bool = true;
        List<Material> blocks = getNearbyBlocks(loc, radius);
        if (blocks.contains(Material.LAVA)) {
            p.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + new MessageMethods(plugin).getMessage("UnsafeShop.LavaNearMessage")));
        } else if (loc.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR)) {
            p.sendMessage(MessageMethods.addColor(new MessageMethods(plugin).getMessage("ErrorPrefix") + new MessageMethods(plugin).getMessage("UnsafeShop.FloatingBlockMessage")));
        } else {
            bool = false;
        }
        return bool;
    }

    public Location getPlayerShopLoc(Player p) {
        double x = new PlayerDataManager(plugin).getDouble(p.getUniqueId(), "PlayerData.location.x");
        double y = new PlayerDataManager(plugin).getDouble(p.getUniqueId(), "PlayerData.location.y");
        double z = new PlayerDataManager(plugin).getDouble(p.getUniqueId(), "PlayerData.location.z");
        String w = new PlayerDataManager(plugin).getString(p.getUniqueId(), "PlayerData.location.world");
        float yaw = new PlayerDataManager(plugin).getFloat(p.getUniqueId(), "PlayerData.location.yaw");
        float pitch = new PlayerDataManager(plugin).getFloat(p.getUniqueId(), "PlayerData.location.pitch");

        Location loc = new Location(Bukkit.getServer().getWorld(w), x, y, z, yaw, pitch);

        return loc;
    }

    public void setPlayerShopLoc(Player p, Location loc) {
        new PlayerDataManager(plugin).setBoolean(p.getUniqueId(), "PlayerData.Shop", true);
        new PlayerDataManager(plugin).setDouble(p.getUniqueId(), "PlayerData.location.x", p.getLocation().getX());
        new PlayerDataManager(plugin).setDouble(p.getUniqueId(), "PlayerData.location.y", p.getLocation().getY());
        new PlayerDataManager(plugin).setDouble(p.getUniqueId(), "PlayerData.location.z", p.getLocation().getZ());
        new PlayerDataManager(plugin).setFloat(p.getUniqueId(), "PlayerData.location.yaw", p.getLocation().getYaw());
        new PlayerDataManager(plugin).setFloat(p.getUniqueId(), "PlayerData.location.pitch", p.getLocation().getPitch());
        new PlayerDataManager(plugin).setString(p.getUniqueId(), "PlayerData.location.world", p.getLocation().getWorld().getName());
    }
}
