package me.dieal.holograms.commands;

import me.dieal.holograms.HologramsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class HologramCommand implements CommandExecutor {

    private HologramsManager manager;

    public HologramCommand (HologramsManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player) {

            if (strings.length == 1) {

                if (strings[0].equalsIgnoreCase("delete")) {
                    player.sendMessage("Select the hologram you want to remove");
                    manager.setRemoving(player, true);
                }

                if (strings[0].equalsIgnoreCase("list")) {

                }

            } else if (strings.length > 1) {
                if (strings[0].equalsIgnoreCase("create")) {

                    String message = "";
                    for (int i = 1; i < strings.length; i++) {
                        message += strings[i] + " ";
                    }
                    message.stripTrailing();
                    manager.addHologram(player, message);

                }
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /hologram [text]");
            }

        }

        return true;
    }



}
