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
		AI.AIplayers.add(player);
	}


	public static void setDifficulty(int difficulty) {
		switch(difficulty){
		  case 0:
		      AI=new EasyAI();
		  case 1:
			  AI=new DifficultAI();
		  default:
			  AI=new DifficultAI();
		}
	}
}
