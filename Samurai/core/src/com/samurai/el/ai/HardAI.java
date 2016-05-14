package com.samurai.el.ai;

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
	
}