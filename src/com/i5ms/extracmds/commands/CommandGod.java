package com.i5ms.extracmds.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGod implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                if(sender.hasPermission("extracmds.god.others")) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        setGod(player, target);
                    }else {
                        player.sendMessage(ChatColor.RED + "I cannot find the player: " + args[0]);
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
                }
            } else {
                setGod(player, player);
            }
        }
        return true;
    }

    public void setGod(Player sender, Player player) {
        if (sender.hasPermission("extracmds.god")) {
            if (player.isInvulnerable()) {
                player.setInvulnerable(false);
                player.sendMessage(ChatColor.GOLD + "God mode " + ChatColor.RED + "disabled"+ChatColor.GOLD+".");
                sender.sendMessage(ChatColor.GOLD + "God mode " + ChatColor.RED + "disabled " + ChatColor.GOLD + "for " + player.getName() + ".");
                System.console().writer().println("[ExtraCMDS] God mode disabled for " + player.getName() + ".");
            }else{
                player.setInvulnerable(true);
                player.sendMessage(ChatColor.GOLD + "God mode " + ChatColor.GREEN + "enabled"+ChatColor.GOLD+".");
                sender.sendMessage(ChatColor.GOLD + "God mode " + ChatColor.GREEN + "enabled " + ChatColor.GOLD + "for " + player.getName() + ".");
                System.console().writer().println("[ExtraCMDS] God mode enabled for " + player.getName() + ".");
            }
            }else{
            sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
        }
    }
}

