package com.samurai.el.ai;

import com.samurai.el.field.Block;
import com.samurai.el.player.Player;

public class HardAI extends PlayerAI {

	public HardAI(Player player) {
		super(player);
		totalMoveCooldown = 0.1f;
		
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