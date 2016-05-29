package com.samurai.el.ai;

import com.samurai.el.field.Block;
import com.samurai.el.player.Player;

public class PlanetHardAI extends PlanetAI {

	public PlanetHardAI(Player player) {
		super(player);
		initialTotalMoveCooldown = 0.1f;
		totalMoveCooldown = initialTotalMoveCooldown;
		
	}
	
	/* chase fiercely, ignore friend
	@Override
	public void update() {
		if(!player.isRecovering) {	
		//move
			pursue();
		//occupy
			if(player.occupiable()) {
				player.occupy();
			}
		}
				
	}
	*/
	
	/**hide when possible */
	@Override
	public void update() {
		if(!player.isRecovering) {	
			
			//move
			target = gameInstance.field.checkVision(player, player.position);
			if(target == null) {
				Block targetPlanet = Targeting.getNearestPlanet(player, planets);
				
				if(targetPlanet != null) {
					targetPlanetPosition.set(targetPlanet.planetPosition);
					occupyPlanet();
				} else {
					for(Player p: allies) {
						if(!p.isHuman) {
							if(p.ai.target != null) {
								target = p.ai.target;
								break;
							}
						} else {
							target = gameInstance.field.checkVision(p, p.position);
							if(target != null) {
								break;
							}
						}
					}
					if(target != null) {
						pursue();
					}
				}
			} else {
				pursue();
			}
			
			//occupy
			if(player.occupiableForAI()) {
				player.occupy();
			}
		}
				
	}
	
}