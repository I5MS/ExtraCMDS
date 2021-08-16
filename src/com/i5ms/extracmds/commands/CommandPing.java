package com.i5ms.extracmds.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPing implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                if(sender.hasPermission("extracmds.ping")) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        Ping(player, target);
                    }else {
                        player.sendMessage(ChatColor.RED + "I cannot find the player: " + args[0]);
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
                }
            } else {
                Ping(player, player);
            }
        }
        return true;
    }

    public void Ping(Player sender, Player player) {
        if(sender.hasPermission("extracmds.ping")) {
            sender.sendMessage(ChatColor.GOLD + player.getName() + " Ping is: " + player.getPing() + ".");
        }else{
            sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
        }
    }
}

//package com.i5ms.extracmds.commands;
//
//import org.bukkit.ChatColor;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//
//public class CommandPing implements CommandExecutor {
//    @Override
//    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        sender.sendMessage(ChatColor.GREEN+"Pong!");
//        return true;
//    }
//}
///