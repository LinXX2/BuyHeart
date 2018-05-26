package me.linxx.buyheart.main;


import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import me.linxx.buyheart.Join;
import me.linxx.util.file.FileManager;


public class BuyHeart extends JavaPlugin {

	public static HashMap< String, Double > hearts = new HashMap<>();
	private FileManager fm_hearts = new FileManager( "PlayersHearts.yml" );
	public static FileManager fm_values = new FileManager( "Values.yml" );
	public static FileManager fm_permissions = new FileManager( "Permissions.yml" );

	@Override
	public void onEnable() {
		for ( String key : fm_hearts.getConfig().getConfigurationSection( "hearts" ).getKeys( false ) ) {
			hearts.put( key, fm_hearts.getConfig().getDouble( "hearts." + key ) );
		}
		this.getCommand( "buyheart" ).setExecutor( new me.linxx.buyheart.commands.BuyHeart( this ) );
		this.getCommand( "sethearts" ).setExecutor( new me.linxx.buyheart.commands.SetHearts() );
		this.getServer().getPluginManager().registerEvents( new Join(), this );

	}

	@Override
	public void onDisable() {
		fm_hearts.getConfig().set( "hearts", hearts );
		fm_hearts.save();
	}

}
