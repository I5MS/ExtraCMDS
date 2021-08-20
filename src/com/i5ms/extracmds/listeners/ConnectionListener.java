package com.i5ms.extracmds.listeners;

import com.i5ms.extracmds.Main;
import com.i5ms.extracmds.VanishManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        VanishManager manager = Main.getInstance().getVanishManager();
        manager.hideAll(event.getPlayer());
    }
}
