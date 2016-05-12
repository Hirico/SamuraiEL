package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.player.Player;

public class EasyAI extends PlayerAI{

	public EasyAI(Player player) {
		super(player);
		
	}
	
	// only chase linearly
	@Override
	public void pursue() {
		
		//find target
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
				pursueMove1();
				moveCooldown = totalMoveCooldown;
			}
		}
	}
	

}
