package com.samurai.el.ai;

import com.badlogic.gdx.utils.Array;
import com.samurai.el.player.Player;

public class AIProgramCenter {
	
	public Array<PlayerAI> AIs;
	
	public AIProgramCenter() {
		AIs = new Array<PlayerAI>();
	}
	
     
    public void setAI(Player player, int difficulty, int mode) {
    	if(mode == 0) {
	    	switch(difficulty) {
	    	case 0:
	    		AIs.add(player.ai = new PlanetEasyAI(player));
	    		break;
	    	case 1:
	    		AIs.add(player.ai = new PlanetNormalAI(player));
	    		break;
	    	case 2:
	    		AIs.add(player.ai = new PlanetHardAI(player));
	    		break;
	    	case 3:
	    		AIs.add(player.ai = new PlanetCruelAI(player));
	    		break;
	    	default:
	    		AIs.add(player.ai = new PlanetEasyAI(player));
	    	}
    	} 
    	
    	else if(mode == 1) {
    		switch(difficulty) {
	    	case 0:
	    		AIs.add(player.ai = new EncounterEasyAI(player));
	    		break;
	    	case 1:
	    		AIs.add(player.ai = new EncounterNormalAI(player));
	    		break;
	    	case 2:
	    		AIs.add(player.ai = new EncounterHardAI(player));
	    		break;
	    	case 3:
	    		AIs.add(player.ai = new EncounterCruelAI(player));
	    		break;
	    	default:
	    		AIs.add(player.ai = new EncounterEasyAI(player));
	    	}
    	}
    	else if(mode == 2) {
    		switch(difficulty) {
	    	case 0:
	    		AIs.add(player.ai = new PlanetEasyAI(player));
	    		break;
	    	case 1:
	    		AIs.add(player.ai = new PlanetNormalAI(player));
	    		break;
	    	case 2:
	    		AIs.add(player.ai = new PlanetHardAI(player));
	    		break;
	    	case 3:
	    		AIs.add(player.ai = new PlanetCruelAI(player));
	    		break;
	    	default:
	    		AIs.add(player.ai = new PlanetEasyAI(player));
	    	}
    	}
    	
    	else if(mode == -1) {
    		AIs.add(player.ai = new GuideAI(player));
    	}
	}


	public void update() {
		for(PlayerAI ai: AIs) {
			ai.update();
		}		
	}

	/**To avoid loop nested error, this is called later after ai's creating */
	public void initializeEnemies() {
		if(AIs.size > 0) {
			for(PlayerAI ai: AIs) {
				ai.initializeEnemy();
			}	
		}
	}
	
	public void refreshList() {
		for(PlayerAI ai: AIs) {
			ai.initializeEnemy();
		}
	}
}
