package com.samurai.el.ai;

import com.badlogic.gdx.utils.Array;
import com.samurai.el.player.Player;

public class AIProgramCenter {
	
	public int difficulty;
	public Array<PlayerAI> AIs;
	
	public AIProgramCenter() {
		AIs = new Array<PlayerAI>(5);
	}
	
     
    public void setAI(Player player) {
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


	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	


	public void update() {
		for(PlayerAI ai: AIs) {
			ai.update();
		}
		
	}
}
