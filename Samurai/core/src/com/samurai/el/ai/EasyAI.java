package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.player.Player;

public class EasyAI extends PlayerAI{

	public EasyAI(Player player) {
		super(player);
		
	}
	
	@Override
	public void pursue() {
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
