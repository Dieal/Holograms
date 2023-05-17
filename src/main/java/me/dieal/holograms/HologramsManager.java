package me.dieal.holograms;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class HologramsManager {

    private ArrayList<ArmorStand> holograms;
    private HashMap<Player, Boolean> removeOperation;

    public HologramsManager () {
        holograms = new ArrayList<>();
        removeOperation = new HashMap<>();
    }

    // Static methods
    public static ArmorStand createHologram (Player player, String text) {

        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setInvulnerable(true);
        stand.setCanPickupItems(false);
        stand.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
        stand.setCustomNameVisible(true);

        return stand;

    }

    // Returns true if the player has sent the "/hologram delete" command
    public boolean isRemoving (Player player) {

        boolean result = false;

        if (removeOperation.containsKey(player)) {
            result = removeOperation.get(player);
        }

        return result;
    }

    // Sets the permission to remove a hologram to the player
    public void setRemoving (Player player, boolean value) {
        if (player != null) {
            removeOperation.put(player, value);
        }
    }

    // Returns the hologram
    public ArrayList<ArmorStand> getHolograms () {
        return holograms;
    }

    // Method that destroys the hologram
    public void removeHologram (ArmorStand hologram) {

        // Destroy the entity
        hologram.remove();

        // Removes the ArmorStand object from the ArrayList
        holograms.remove(hologram);

    }

    // Creates and adds a hologram
    public void addHologram (Player player, String message) {

        ArmorStand hologram = createHologram(player, message);
        if (!holograms.contains(player)) {
            holograms.add(hologram);
        } else {
            holograms.add(hologram);
        }

    }



}
