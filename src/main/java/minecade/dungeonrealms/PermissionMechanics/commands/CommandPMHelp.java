package minecade.dungeonrealms.PermissionMechanics.commands;

import minecade.dungeonrealms.PermissionMechanics.PermissionMechanics;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPMHelp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(!PermissionMechanics.getRank(p.getName()).equalsIgnoreCase("pmod") && !(p.isOp())) { return true; }
		
		if(args.length != 0) {
			p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Incorrect Syntax");
			p.sendMessage(ChatColor.RED + "/pmhelp");
			p.sendMessage(ChatColor.GRAY + "Displays a list of Player Moderator commands.");
			return true;
		}
		
		p.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "              " + " *** Player Moderator Commands ***");
		p.sendMessage(ChatColor.WHITE + "/mute <PLAYER> <TIME(minutes)>" + ChatColor.GRAY + " Mutes PLAYER for TIME minutes from local and global chat.");
		p.sendMessage(ChatColor.WHITE + "/kick <PLAYER> <REASON>" + ChatColor.GRAY + " Kicks PLAYER and displays REASON to them.");
		p.sendMessage(ChatColor.WHITE + "/ban <PLAYER> <TIME(hours)>" + ChatColor.GRAY + " Bans PLAYER for TIME minutes from all servers.");
		p.sendMessage(ChatColor.WHITE + "/flag <PLAYER> (REASON)" + ChatColor.GRAY + " Flags PLAYER for REASON for other mods to see. Leave REASON blank to view flags on player.");
		return true;
	}
	
}