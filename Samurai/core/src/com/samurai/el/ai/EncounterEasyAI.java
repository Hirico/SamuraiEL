package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.player.Player;

public class EncounterEasyAI extends EncounterAI {

	public EncounterEasyAI(Player player) {
		super(player);
		occupyDelay = 0.5f;
	}
	
	@Override
	public void pursue() {
		if(target == null) {
			getRandomTarget();
		} else { 
			if(target.isRecovering) {
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
						pursueMove1();
						moveCooldown = totalMoveCooldown;
					}
				}
			}
		}
	}
	

}
