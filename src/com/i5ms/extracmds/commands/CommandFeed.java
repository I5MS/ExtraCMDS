package com.i5ms.extracmds.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFeed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                if(sender.hasPermission("extracmds.feed.others")) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        Feed(player, target);
                }else {
                        player.sendMessage(ChatColor.RED + "I cannot find the player: " + args[0]);
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
                }
            } else {
                Feed(player, player);
            }
        }
        return true;
    }

    public void Feed(Player sender, Player player) {
        if(sender.hasPermission("extracmds.feed")) {
            player.setFoodLevel(20);
            System.console().writer().println("[ExtraCMDS] The player " + player.getName() + " has been feed.");
            player.sendMessage(ChatColor.GOLD + "You have been feed.");
            sender.sendMessage(ChatColor.GOLD + "You feed " + player.getName() + ".");
        }else{
            sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
        }
    }
}