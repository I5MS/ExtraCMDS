package com.i5ms.extracmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class VanishManager {
    private final Plugin plugin;
    private final java.util.List<Player> vanished;

    public VanishManager(Plugin plugin) {
        this.plugin = plugin;
        this.vanished = new ArrayList<>();
    }

    public boolean isVanished(Player player) { return vanished.contains(player); }

    public void setVanished(Player player, boolean bool) {
        if (bool) {
            this.vanished.add(player);

        } else {
            this.vanished.remove(player);
        }
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (player.equals(onlinePlayer)) continue;
            if (bool) {
                if(!onlinePlayer.hasPermission("extracmds.vanish")) {
                    onlinePlayer.hidePlayer(this.plugin, player);
                }
            } else {
                onlinePlayer.showPlayer(this.plugin, player);
            }
        }
    }

    public void hideAll(Player player) {
        vanished.forEach(player1 -> player.hidePlayer(this.plugin, player1));
    }
}

