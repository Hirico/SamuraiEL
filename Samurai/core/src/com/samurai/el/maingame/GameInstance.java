package com.samurai.el.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.samurai.el.ai.AIProgramCenter;
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

public class GameInstance implements Disposable{
	
	public static GameInstance instance;
	public int totalTime;
	public double currentTime;
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
	public int winFlag;
	public AIProgramCenter aiProgram;
	public MessageDispatcher messageDispatcher;
	public boolean gameOver;
	
	private GameInstance(int mapid, int time) {
		totalTime = time;
		currentTime = totalTime;
		winFlag = -1;
		gameOver = false;
		aiProgram = new AIProgramCenter();
		//messageDispatcher = new MessageDispatcher();
		
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
		
				
		playerBatch = new SpriteBatch();
				
	}
	
	public void initializePlayer(int humanid,  int difficulty) {
		//initialize players
		players = new Array<Player>();		
		players.add(redSpear = new RedSpear(field.homePositions[0]));
		players.add(redSword = new RedSword(field.homePositions[1]));
		players.add(redAxe = new RedAxe(field.homePositions[2]));
		players.add(blueSpear = new BlueSpear(field.homePositions[3]));
		players.add(blueSword = new BlueSword(field.homePositions[4]));
		players.add(blueAxe = new BlueAxe(field.homePositions[5]));
		
		//setAI
		aiProgram.setDifficulty(difficulty);		
		switch(humanid) {
		case 0:
			redSpear.isHuman = true;
			human = redSpear;
			break;
		case 1:
			redSword.isHuman = true;
			human = redSword;
			break;
		case 2:
			redAxe.isHuman = true;
			human = redAxe;
			break;
		case 3:
			blueSpear.isHuman = true;
			human = blueSpear;
			break;
		case 4:
			blueSword.isHuman = true;
			human = blueSword;
			break;
		case 5:
			blueAxe.isHuman = true;
			human = blueAxe;
			break;
		}
		
		for(Player p : players) {
			if(p.isHuman == false) {
				aiProgram.setAI(p);
			}
			if(p.side == human.side) {
				p.isAllied = true;
				p.isVisible = true;
				field.openVision(p.position);
				
			}
			for(int i = 0; i < 6; i++) {
				field.blocks[(int) field.homePositions[i].x][(int) field.homePositions[i].y].playerArrive(i);
				field.homeInitialize(i);
			}
		}
		
		
		
	}
	
	public static GameInstance getInstance() {
		return instance;
	}
	
	public static void setInstance(int mapid, int human, int time, int difficulty) {
			instance = new GameInstance(mapid, time);
			instance.initializePlayer(human, difficulty);
	}
	
	public void render() {
		
		currentTime -= Gdx.graphics.getDeltaTime();
		field.render();
				
		playerBatch.begin();
		for(Player p: players) {
			p.draw(playerBatch);
		}
		
		
		playerBatch.end();
		
		
		//update AI
		GdxAI.getTimepiece().update(Gdx.graphics.getDeltaTime());
		//messageDispatcher.update();
		aiProgram.update();
	}
	
	public int[][] gameOver() {
		int[][] result = new int[6][4];
		int[] scores = new int[6];
		for(Player p: players) {
			result[p.id][0] = scores[p.id] = p.score;
			result[p.id][1] = p.killNum;
			result[p.id][2] = p.killedNum;
		}
		int redScore = scores[0] + scores[1] + scores[2];
		int blueScore = scores[3] + scores[4] + scores[5];
		
		if((human.side == 0 && redScore > blueScore) || (human.side == 1 && blueScore > redScore)) {
			winFlag = 1;
		} else if((human.side == 0 && redScore < blueScore) || (human.side == 1 && blueScore < redScore)) {
			winFlag = 0;
		}
		
		gameOver = true;
		return result;
	}
	
	public void dispose() {
		playerBatch.dispose();
		for(Player p: players) {
			p.dispose();
		}
		field.dispose();
	}

	/** Set the instance to null without dispose */
	public static void closeInstance() {
		instance = null;
		
	}
}
