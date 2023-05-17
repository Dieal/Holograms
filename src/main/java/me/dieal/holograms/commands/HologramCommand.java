package me.dieal.holograms.commands;

import me.dieal.holograms.HologramsManager;
import me.dieal.holograms.gui.HologramMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HologramCommand implements CommandExecutor {

    private final HologramsManager manager;

    public HologramCommand (HologramsManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player) {

            if (strings.length == 1) {

                if (strings[0].equalsIgnoreCase("create")) {
                    player.sendMessage(ChatColor.RED + "Usage: /hologram create [text]");
                }

                if (strings[0].equalsIgnoreCase("delete")) {
                    player.sendMessage(ChatColor.YELLOW + "Select the hologram you want to remove");
                    manager.setRemoving(player, true);
                }

                if (strings[0].equalsIgnoreCase("menu")) {
                    player.openInventory(HologramMenu.createMenu(player, manager));
                }

            } else if (strings.length > 1) {
                if (strings[0].equalsIgnoreCase("create")) {

                    String message = "";
                    for (int i = 1; i < strings.length; i++) {
                        message += strings[i] + " ";
                    }
                    message = message.stripTrailing();
                    manager.addHologram(player, message);

                }
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /hologram [create|delete|list]");
            }

        }

        return true;
    }



}
