package me.dieal.holograms.gui;

import me.dieal.holograms.HologramsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HologramMenu {

    public static Inventory createMenu (Player player, HologramsManager manager) {

        Inventory menu = Bukkit.createInventory(player, 54, ChatColor.BLUE + "Hologram Manager");
        ArrayList<ArmorStand> holograms = manager.getHolograms();
        ItemStack armorstand = new ItemStack(Material.ARMOR_STAND);

        ArrayList<String> lore = new ArrayList<>();
        ItemMeta standMeta = armorstand.getItemMeta();
        for (ArmorStand hologram : holograms) {

            Location hologramLocation = hologram.getLocation();

            standMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', hologram.getCustomName()));
            lore.add(ChatColor.BLUE + "Coordinates: " + ChatColor.WHITE + hologramLocation.getX() + ", " + hologramLocation.getY() + ", " + hologramLocation.getZ());
            lore.add("");
            lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "LEFT-CLICK TO TELEPORT");
            lore.add(ChatColor.RED + "" + ChatColor.BOLD + "RIGHT-CLICK TO DELETE");
            standMeta.setLore(lore);
            armorstand.setItemMeta(standMeta);

            menu.addItem(armorstand);

        }

        return menu;

    }

}
