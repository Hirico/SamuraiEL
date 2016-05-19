package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.player.Player;

public class EncounterEasyAI extends EncounterAI {

	public EncounterEasyAI(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pursue() {
		if(target == null) {
			getRandomTarget();
		} 
		if(target.isRecovering) {
			changeTarget();
		}
		
		if(moveCooldown >= 0) {
			moveCooldown -= Gdx.graphics.getDeltaTime();
		} else {
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
