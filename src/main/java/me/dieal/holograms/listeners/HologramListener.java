package me.dieal.holograms.listeners;

import me.dieal.holograms.HologramsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

import static org.bukkit.Material.NAME_TAG;

public class HologramListener implements Listener {

    private HologramsManager manager;

    /* HashMap that saves the Holograms to be removed. It allows the holograms to be
    removed from the onHologramClick event, which cannot have it as a parameter */
    private HashMap<Player, ArmorStand> removableStands;

    public HologramListener(HologramsManager manager) {
        this.manager = manager;
        this.removableStands = new HashMap<>();
    }

    @EventHandler
    public void onHologramClick (PlayerInteractAtEntityEvent e) {

        Player player = e.getPlayer();

        // Checks if the clicked entity is an armor stand
        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {

            // Checks if the armor stand is an hologram and if the player sent the remove command
            if (manager.getHolograms().contains((ArmorStand) e.getRightClicked()) && manager.isRemoving(player)) {
                openConfirmMenu(player, (ArmorStand) e.getRightClicked());
            }

        }

    }

    // Listener method to interact with the Confirm Menu
    @EventHandler
    public void onConfirmMenuClick (InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        // Checks if the clicked inventory is the confirm hologram
        if (e.getView().getTitle().equals(ChatColor.YELLOW + "Delete Hologram?")) {

            e.setCancelled(true);

            if (e.getCurrentItem() != null) {

                switch (e.getCurrentItem().getType()) {
                    // Confirm Button
                    case GREEN_WOOL:
                        manager.removeHologram(removableStands.get(player));
                        manager.setRemoving(player, false);
                        player.closeInventory();
                        break;

                    // Cancel Button
                    case RED_WOOL:
                        removableStands.remove(player);
                        manager.setRemoving(player, false);
                        player.closeInventory();
                        break;
                }

            }

        }

    }

    private static Inventory createConfirmMenu(Player player, ArmorStand hologram) {

        Inventory menu = Bukkit.createInventory(player, 36, ChatColor.YELLOW + "Delete Hologram?");
        ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
        ItemStack cancel = new ItemStack(Material.RED_WOOL);
        ItemStack nametag = new ItemStack (NAME_TAG);

        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN + "CONFIRM");
        confirm.setItemMeta(confirmMeta);

        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.RED + "CANCEL");
        cancel.setItemMeta(cancelMeta);

        ItemMeta nametagMeta = nametag.getItemMeta();
        nametagMeta.setDisplayName("Delete Hologram?");
        nametag.setItemMeta(nametagMeta);

        menu.setItem(13, nametag);
        menu.setItem(21, confirm);
        menu.setItem(23, cancel);

        return menu;

    }

    // Methods that opens the confirm menu
    public void openConfirmMenu (Player player, ArmorStand hologram) {

        // Saves the hologram into the hashmap, so that it can be used in the onHologramClick listener
        removableStands.put(player, hologram);

        // Opens the inventory
        player.openInventory(createConfirmMenu(player, hologram));

    }

}
