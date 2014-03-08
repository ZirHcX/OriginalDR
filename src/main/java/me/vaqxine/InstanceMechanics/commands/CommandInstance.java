package me.vaqxine.InstanceMechanics.commands;

import me.vaqxine.InstanceMechanics.InstanceMechanics;
import me.vaqxine.PartyMechanics.PartyMechanics;

import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInstance implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;

		if(sender instanceof Player){
			p = (Player)sender;
		}
		
		if(p != null && !(p.isOp())){
			return true;
		}

		if(args.length == 3){
			InstanceMechanics.setPlayerEnvironment(p, Environment.NETHER);
		}

		if(args.length == 0){
			p.sendMessage(ChatColor.WHITE + "To designate the entrance to an instance, define a worldguard region and name it prefix it with 'instance_' followed by the name of the instance template. For ex: instance_MapName");
			p.sendMessage(ChatColor.YELLOW + "To designate the exits of an instance, define a worldguard region inside the instance and prefix it with 'exit_instance'. You can have multiple exits, exit_instance1, exit_instance2, etc.");
			p.sendMessage(" ");
			p.sendMessage(ChatColor.YELLOW + "/instance load <instance template> -- Loads a test instance of given template and teleports you there.");
			p.sendMessage(ChatColor.WHITE + "/instance unload <instance> -- Unloads the instance specified.");
			p.sendMessage(ChatColor.YELLOW + "/instance edit <instance template> -- Loads the TEMPLATE of a given instance for editing mobs, loot, or blocks.");

			return true;
		}

		String sub_cmd = args[0];
		if(sub_cmd.equalsIgnoreCase("load")){
			String instance_name = args[1];
			String new_instance = InstanceMechanics.linkInstanceToParty(instance_name, PartyMechanics.getPartyMembers(p.getName()), false); // Define new instance.
			if(!(InstanceMechanics.teleport_on_load.contains(p.getName()))){
				InstanceMechanics.teleport_on_load.add(p.getName());
			}
			InstanceMechanics.asyncLoadNewInstance(new_instance, false, false); // Load new instance.
			p.sendMessage(ChatColor.GRAY + "Loading: " + new_instance + " . . .");
		}
		if(sub_cmd.equalsIgnoreCase("unload")){
			String instance_name = args[1];
			InstanceMechanics.asyncUnloadWorld(instance_name);
		}
		if(sub_cmd.equalsIgnoreCase("edit")){
			String instance_name = args[1];
			String new_instance = InstanceMechanics.linkInstanceToParty(instance_name, PartyMechanics.getPartyMembers(p.getName()), true); // Define new instance.
			if(!(InstanceMechanics.teleport_on_load.contains(p.getName()))){
				InstanceMechanics.teleport_on_load.add(p.getName());
			}
			InstanceMechanics.asyncLoadNewInstance(new_instance, true, false); // Load new instance.
			p.sendMessage(ChatColor.GRAY + "Loading Template: " + new_instance + " . . .");
		}
		if(sub_cmd.equalsIgnoreCase("shards")){
			InstanceMechanics.subtractShards(p.getName(), 1, -50000);
			InstanceMechanics.subtractShards(p.getName(), 2, -50000);
			InstanceMechanics.subtractShards(p.getName(), 3, -50000);
			InstanceMechanics.subtractShards(p.getName(), 4, -50000);
			InstanceMechanics.subtractShards(p.getName(), 5, -50000);
		}
		return true;
	}
	
}