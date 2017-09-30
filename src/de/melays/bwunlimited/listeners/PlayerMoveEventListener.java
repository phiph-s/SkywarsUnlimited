package de.melays.bwunlimited.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.melays.bwunlimited.Main;
import de.melays.bwunlimited.game.arenas.Arena;
import de.melays.bwunlimited.game.arenas.state.ArenaState;

public class PlayerMoveEventListener implements Listener{

	Main main;
	
	public PlayerMoveEventListener (Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (main.getArenaManager().isInGame(p)) {
			Arena arena = main.getArenaManager().searchPlayer(p);
			
			//Arena relevant Event stuff
			if (arena.state == ArenaState.INGAME) {
				if (e.getTo().getY() < arena.relative.getY() - main.getConfig().getInt("death_y_offset")) {
					arena.deathManager.playerDeath(p);
				}
			}
			
		}
	}
	
}