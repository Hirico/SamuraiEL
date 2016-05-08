package com.samurai.el.ai;

import com.badlogic.gdx.utils.Array;
import com.samurai.el.field.Block;
import com.samurai.el.player.Player;

public class AIProgram {
    float timeCell;
    float fieldx;
    float fieldy;
    static Array<Player> AIplayers;
     static AIProgram AI;
     static Block[][] field;
    public static void setAI(Player player) {
    	AIplayers = new Array<Player>();
		AIplayers.add(player);
	}


	public static void setDifficulty(int difficulty) {
		switch(difficulty){
		  case 0:
		      AI=new EasyAI();
		      break;
		  case 1:
			  AI=new DifficultAI();
			  break;
		  default:
			  AI=new DifficultAI();
		}
	}
	
	public AIProgram() {
		
	}




	public void update() {
		// TODO Auto-generated method stub
		
	}
}
