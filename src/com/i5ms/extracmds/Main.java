package com.i5ms.extracmds;

import com.i5ms.extracmds.commands.*;
import com.i5ms.extracmds.listeners.ConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {
    private static Main instance;
    private VanishManager vanishManager;

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("clear")).setExecutor(new CommandClear());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new CommandFeed());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new CommandFly());
        Objects.requireNonNull(getCommand("god")).setExecutor(new CommandGod());
        Objects.requireNonNull(getCommand("heal")).setExecutor(new CommandHeal());
        Objects.requireNonNull(getCommand("ping")).setExecutor(new CommandPing());
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new CommandVanish());
        Objects.requireNonNull(getServer().getPluginCommand("skull")).setExecutor(new CommandSkull(this));
        instance = this;
        this.vanishManager = new VanishManager(this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[ExtraCMDS] Has Been Enabled.");
        // startup
        // reload
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED+"[ExtraCMDS] Has Been Disabled.");
        // shutdown
        // reload
    }

    public static Main getInstance() {
        return instance;
    }

    public VanishManager getVanishManager(){
        return vanishManager;
    }
}
