package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.player.Player;

public class EncounterAI extends PlayerAI {

	public EncounterAI(Player player) {
		super(player);
	}
	
	public void update() {

		//move
		if(!player.isRecovering) {	
			pursue();
		}
			
		//occupy
		if(player.occupiableForAI()) {
			occupyDelay -= Gdx.graphics.getDeltaTime();
			if(occupyDelay <= 0) {
				occupyDelay = 0;
				player.occupy();
			}
		}
	}
				
	

	@Override
	public void pursue() {
		if(target == null) {
			getRandomTarget();
		} else {
			if(target.isRecovering || target.isHidden) {
				changeTarget();
			}
			
			if(moveCooldown >= 0) {
				moveCooldown -= Gdx.graphics.getDeltaTime();
			} else {
				if(!target.isHidden) {
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
				} else {
					wander();
				}
			}
		}
	}
	
	


}
