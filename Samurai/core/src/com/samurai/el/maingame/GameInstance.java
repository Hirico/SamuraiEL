package com.samurai.el.maingame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.samurai.el.ai.AIProgram;
import com.samurai.el.field.Field;
import com.samurai.el.field.Field0;
import com.samurai.el.field.Field1;
import com.samurai.el.field.Field2;
import com.samurai.el.field.Field3;
import com.samurai.el.player.BlueAxe;
import com.samurai.el.player.BlueSpear;
import com.samurai.el.player.BlueSword;
import com.samurai.el.player.Player;
import com.samurai.el.player.RedAxe;
import com.samurai.el.player.RedSpear;
import com.samurai.el.player.RedSword;

public class GameInstance {
	
	public static GameInstance instance;
	public int totalTime;
	public RedSpear redSpear;
	public RedSword redSword;
	public RedAxe redAxe;
	public BlueSpear blueSpear;
	public BlueSword blueSword;
	public BlueAxe blueAxe;
	public Player human;
	public Array<Player> players;
	public Field field;
	public SpriteBatch playerBatch;
	
	private GameInstance(int mapid, int humanid, int time, int difficulty) {
		totalTime = time;
		
		//setMap
		switch(mapid) {
		case 0:
			field = new Field0();
			break;
		case 1:
			field = new Field1();
			break;
		case 2:
			field = new Field2();
			break;
		case 3:
			field = new Field3();
			break;
		default:
			field = new Field0();
			break;
		}
		
		//initial players
		players = new Array<Player>();		
		players.add(redSpear = new RedSpear());
		players.add(redSword = new RedSword());
		players.add(redAxe = new RedAxe());
		players.add(blueSpear = new BlueSpear());
		players.add(blueSword = new BlueSword());
		players.add(blueAxe = new BlueAxe());
		
		//setAI
		switch(humanid) {
		case 0:
			redSpear.isHuman = true;
			human = redSpear;
			break;
		case 1:
			redSpear.isHuman = true;
			human = redSword;
			break;
		case 2:
			redSpear.isHuman = true;
			human = redAxe;
			break;
		case 3:
			redSpear.isHuman = true;
			human = blueSpear;
			break;
		case 4:
			redSpear.isHuman = true;
			human = blueSword;
			break;
		case 5:
			redSpear.isHuman = true;
			human = blueAxe;
			break;
		}
		for(Player p : players) {
			if(p.isHuman == false) {
				AIProgram.setAI(p);
				if(p.side == human.side) {
					p.isAllied = true;
				}
			}
		}
		
		playerBatch = new SpriteBatch();
		AIProgram.setDifficulty(difficulty);
		
		
	}
	
	public static GameInstance getInstance() {
		return instance;
	}
	
	public static void setInstance(int mapid, int human, int time, int difficulty) {
		instance = new GameInstance(mapid, human, time, difficulty);
	}
	
	public void render() {
		
		field.render();
				
		playerBatch.begin();
		/*for(Player p: players) {
			p.draw(playerBatch);
		}*/
		human.draw(playerBatch);
		playerBatch.end();
	}
	
	public void dispose() {
		playerBatch.dispose();
		field.dispose();
	}
}
