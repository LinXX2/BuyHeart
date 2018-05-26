package me.linxx.buyheart.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.linxx.buyheart.main.Messages;
import net.milkbowl.vault.economy.Economy;


public class BuyHeart implements CommandExecutor {

	me.linxx.buyheart.main.BuyHeart plugin;
	private Economy econ = null;

	public BuyHeart( me.linxx.buyheart.main.BuyHeart plugin ) {
		this.plugin = plugin;
		RegisteredServiceProvider< Economy > rsp = plugin.getServer().getServicesManager()
				.getRegistration( Economy.class );
		econ = rsp.getProvider();
	}

	@SuppressWarnings( "deprecation" )
	@Override
	public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args ) {

		if ( sender instanceof Player ) {
			Player p = ( Player ) sender;
			if ( p.hasPermission(
					me.linxx.buyheart.main.BuyHeart.fm_permissions.getConfig().getString( "cmd_buyheart" ) ) ) {
				if ( econ.getBalance( p.getName() ) >= me.linxx.buyheart.main.BuyHeart.fm_values.getConfig()
						.getDouble( "Heartprice" ) ) {
					p.sendMessage( Messages.ON_BUY_HEART.Message );
					p.setHealthScale( p.getHealthScale() + 2 );
					me.linxx.buyheart.main.BuyHeart.hearts.put( p.getName(), p.getHealthScale() );
				} else {
					p.sendMessage( Messages.NOT_ENOUGHT_MONEY_MESSAGE.Message );
				}
			} else {
				p.sendMessage( Messages.NO_PERMISSION_MESSAGE.Message );
			}
		}
		return true;
	}
}
