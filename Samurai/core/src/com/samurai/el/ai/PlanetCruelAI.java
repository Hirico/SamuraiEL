package com.samurai.el.ai;

import com.samurai.el.field.Block;
import com.samurai.el.player.Player;

public class PlanetCruelAI extends PlanetAI {

	public PlanetCruelAI(Player player) {
		super(player);
		initialTotalMoveCooldown = 0.1f;
		// TODO Auto-generated constructor stub
	}
	
	/**hide when possible */
	@Override
	public void update() {
		if(!player.isRecovering) {	
			
			//hide in self territory
			if(player.side == gameInstance.field.getSpefBlock(player.position).side) {
				if(!player.isHidden) {
					player.hide();
				}
				if(player.isHidden && player.occupiableForAI()) {
					player.show();
					player.occupy();
				}
			}
			
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
