package com.i5ms.extracmds.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                if(sender.hasPermission("extracmds.fly.others")) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        assert target != null;
                        setFly(player, target);
                    }else {
                        player.sendMessage(ChatColor.RED + "I cannot find the player: " + args[0]);
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
                }
            } else {
                setFly(player, player);
            }
        }
        return true;
    }

    public void setFly (Player sender, Player player) {
        if (sender.hasPermission("extracmds.fly")) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.GOLD+ "Fly mode "+ChatColor.RED+"disabled"+ChatColor.GOLD+".");
                sender.sendMessage(ChatColor.GOLD+ "Fly mode "+ChatColor.RED+"disabled "+ChatColor.GOLD +"for "+player.getName()+".");
                System.console().writer().println("[ExtraCMDS] Fly mode disabled for "+player.getName()+".");
            }else{
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.GOLD+"Fly mode "+ChatColor.GREEN+"enabled"+ChatColor.GOLD+".");
                sender.sendMessage(ChatColor.GOLD+"Fly mode "+ChatColor.GREEN+"enabled "+ChatColor.GOLD+"for "+player.getName()+".");
                System.console().writer().println("[ExtraCMDS] Fly mode enabled for "+player.getName()+".");
            }
        }else{
            sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
        }
    }
}

