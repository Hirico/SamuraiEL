package com.samurai.el.ai;

import com.badlogic.gdx.utils.Array;
import com.samurai.el.player.Player;

public class AIProgramCenter {
	
	public Array<PlayerAI> AIs;
	
	public AIProgramCenter() {
		AIs = new Array<PlayerAI>(5);
	}
	
     
    public void setAI(Player player, int difficulty) {
    	switch(difficulty) {
    	case 0:
    		AIs.add(new EasyAI(player));
    		break;
    	case 1:
    		AIs.add(new NormalAI(player));
    		break;
    	case 2:
    		AIs.add(new HardAI(player));
    		break;
    	default:
    		AIs.add(new EasyAI(player));
    	}
	}


	public void update() {
		for(PlayerAI ai: AIs) {
			ai.update();
		}
		
	}

	/**To avoid loop nested error, this is called later after ai's creating */
	public void initializeEnemies() {
		for(PlayerAI ai: AIs) {
			ai.initializeEnemy();
		}
		
	}
}
