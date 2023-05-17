package me.dieal.holograms.listeners;

import me.dieal.holograms.HologramsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    private final HologramsManager manager;

    public MenuListener (HologramsManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMenuClick (InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        // Checks if the clicked inventory is the hologram menu
        if (e.getView().getTitle().equals(ChatColor.BLUE + "Hologram Manager")) {

            e.setCancelled(true);

            if (e.getCurrentItem() != null) {

                switch (e.getCurrentItem().getType()) {
                    case ARMOR_STAND:



                        break;
                }

            }

        }

    }



}
