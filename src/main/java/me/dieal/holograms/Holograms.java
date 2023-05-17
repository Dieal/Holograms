package me.dieal.holograms;

import me.dieal.holograms.commands.HologramCommand;
import me.dieal.holograms.listeners.HologramListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Holograms extends JavaPlugin {

    @Override
    public void onEnable() {

        HologramsManager manager = new HologramsManager();

        getCommand("hologram").setExecutor(new HologramCommand(manager));
        getServer().getPluginManager().registerEvents(new HologramListener(manager), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
