package com.i5ms.extracmds.commands;

import com.i5ms.extracmds.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public record CommandSkull(Main main) implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (label.equalsIgnoreCase("skull")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("You cannot do this!");
                return true;
            }
            if(sender.hasPermission("extracmds.skull")) {
                if (args.length == 0) {
                    // /skull
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have been given the skull of " + player.getName()));
                    player.getInventory().addItem(getPlayerHead(player.getName()));
                    return true;
                }
                // / skull <player>
                player.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&6You have been given the skull of " + args[0]));
                player.getInventory().addItem(getPlayerHead(args[0]));
            }else{
                sender.sendMessage(ChatColor.RED + "You don't have the required permission to use this command!");
            }
            return true;
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    public ItemStack getPlayerHead(String player) {
        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        assert type != null;
        ItemStack item = new ItemStack(type, 1);
        if (!isNewVersion)
            item.setDurability((short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        assert meta != null;
        meta.setOwner(player);
        item.setItemMeta(meta);
        return item;
    }
}