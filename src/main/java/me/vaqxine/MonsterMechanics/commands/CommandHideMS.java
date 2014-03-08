package me.vaqxine.MonsterMechanics.commands;

import me.vaqxine.MonsterMechanics.MonsterMechanics;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandHideMS implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if(!(p.isOp())){
			return true;
		}

		if(args.length == 3){
			p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 4));
			p.setHealth(p.getMaxHealth());
			return true;
		}

		if (args.length != 1) {
			p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD
					+ "Incorrect Syntax. " + ChatColor.RED
					+ "/hidems <radius>");
			return true;
		}

		int radius = Integer.parseInt(args[0]);
		Location loc = p.getLocation();
		World w = loc.getWorld();
		int i, j, k;
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		int z = (int) loc.getZ();

		for (i = -radius; i <= radius; i++) {
			for (j = -radius; j <= radius; j++) {
				for (k = -radius; k <= radius; k++) {
					loc = w.getBlockAt(x + i, y + j, z + k).getLocation();
					if (MonsterMechanics.mob_spawns.containsKey(loc)) {
						loc.getBlock().setType(Material.AIR);
					}
				}
			}
		}

		p.sendMessage(ChatColor.YELLOW + "Hiding mob spawners in a "
				+ radius + " block radius...");
		return true;
	}
	
}