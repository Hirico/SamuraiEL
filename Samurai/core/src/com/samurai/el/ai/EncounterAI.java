package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.field.Block;
import com.samurai.el.player.Player;

public class EncounterAI extends PlayerAI {

	public EncounterAI(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	public void update() {

		//move
		if(!player.isRecovering) {	
			pursue();
		}
			
		//occupy
		if(player.occupiableForAI()) {
			player.occupy();
		}
	}
				
	

	@Override
	public void pursue() {
		if(target == null) {
			getRandomTarget();
		} 
		if(target.isRecovering || target.isHidden) {
			changeTarget();
		}
		
		if(moveCooldown >= 0) {
			moveCooldown -= Gdx.graphics.getDeltaTime();
		} else {
			if(player.isStuck) {
				resolveStuck();
				moveCooldown = totalMoveCooldown;
			} else {
				if(Math.random() < 0.5f) {
					pursueMove1();
				} else {
					pursueMove2();
				}
				moveCooldown = totalMoveCooldown;
			}
		}
	}
	
	


}
