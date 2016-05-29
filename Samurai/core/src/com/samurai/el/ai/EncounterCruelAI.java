package com.samurai.el.ai;

import com.samurai.el.player.Player;

public class EncounterCruelAI extends EncounterAI {

	public EncounterCruelAI(Player player) {
		super(player);
		initialTotalMoveCooldown = 0.1f;
		totalMoveCooldown = initialTotalMoveCooldown;
	}
	
	@Override
	public void update() {
		
		//hide when possible
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
		if(!player.isRecovering) {	
			pursue();
		}
			
		//occupy
		if(player.occupiableForAI()) {
			player.occupy();
		}
	}

}
