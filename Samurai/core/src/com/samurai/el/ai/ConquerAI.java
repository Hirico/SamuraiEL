package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.player.Player;

public class ConquerAI extends PlayerAI {
	public ConquerAI(Player player) {
		super(player);
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
					}
				}
			}
		}
		
		


}
