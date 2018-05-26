package me.linxx.buyheart;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.linxx.buyheart.main.BuyHeart;


public class Join implements Listener {

	@EventHandler
	public void onJoin( PlayerJoinEvent e ) {
		if ( BuyHeart.hearts.containsKey( e.getPlayer().getName() ) ) {
			e.getPlayer().setHealthScale( BuyHeart.hearts.get( e.getPlayer().getName() ) );
		} else {
			BuyHeart.hearts.put( e.getPlayer().getName(), 20.0 );
		}
	}

}
