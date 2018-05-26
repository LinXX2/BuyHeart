package me.linxx.buyheart.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.linxx.buyheart.main.BuyHeart;
import me.linxx.buyheart.main.Messages;
import net.md_5.bungee.api.ChatColor;


public class SetHearts implements CommandExecutor {

	@Override
	public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args ) {
		if ( sender.hasPermission( BuyHeart.fm_permissions.getConfig().getString( "cmd_buyheart" ) ) ) {
			if ( args.length == 2 ) {
				if ( Bukkit.getPlayer( args[ 0 ] ) != null ) {
					Player target = Bukkit.getPlayer( args[ 0 ] );
					target.setHealthScale( Double.valueOf( args[ 1 ] ) );
					sender.sendMessage( Messages.ON_SETHEARTS.Message.replace( "%player%", target.getName() )
							.replace( "%hearts%", Double.toString( Double.valueOf( args[ 1 ] ) / 2 ) ) );
					BuyHeart.hearts.put( target.getName(), target.getHealthScale() );
				} else {
					sender.sendMessage( Messages.TARGET_IS_NO_PLAYER.Message );
				}
			} else {
				sender.sendMessage( ChatColor.RED + "/SetHearts <Player> <Amount>" );
			}
		}

		return true;
	}

}
