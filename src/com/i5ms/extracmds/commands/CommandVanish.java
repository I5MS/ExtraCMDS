package com.i5ms.extracmds.commands;

import com.i5ms.extracmds.Main;
import com.i5ms.extracmds.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVanish implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        VanishManager vanishManager = Main.getInstance().getVanishManager();
        Player player;
        if (args.length == 1) {
            if (sender.hasPermission("extracmds.vanish.others")) {
                player = Bukkit.getPlayer(args[0]);
                if (player == null) {
                sender.sendMessage(ChatColor.RED + "The player is not online");
                return true;
                }
                if (vanishManager.isVanished(player)) {
                vanishManager.setVanished(player, false);
                sender.sendMessage(ChatColor.GOLD + "The player " + player.getName() + " no longer in vanish.");
                System.console().writer().println("[ExtraCMDS] Vanish mode disabled for "+player.getName()+".");
                }else {
                vanishManager.setVanished(player, true);
                sender.sendMessage(ChatColor.GOLD + "The player " + player.getName() + " in vanish.");
                System.console().writer().println("[ExtraCMDS] Vanish mode enabled for "+player.getName()+".");
                }
            }else {
                sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
            }

            return true;

        } else if (sender instanceof Player) {
            player = (Player)sender;
            if (sender.hasPermission("extracmds.vanish")) {
                if (vanishManager.isVanished(player)) {
                    vanishManager.setVanished(player, false);
                    sender.sendMessage(ChatColor.GOLD + "You're no longer in Vanish.");
                    System.console().writer().println("[ExtraCMDS] Vanish mode disabled for "+player.getName()+".");
                }else {
                    vanishManager.setVanished(player, true);
                    sender.sendMessage(ChatColor.GOLD + "You're in Vanish now.");
                    System.console().writer().println("[ExtraCMDS] Vanish mode enabled for "+player.getName()+".");
                }
            }else {
                sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
            }
            return true;
        }
        return false;
    }
}
