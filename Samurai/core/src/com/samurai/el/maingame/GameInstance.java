package com.samurai.el.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.samurai.el.ai.AIProgramCenter;
import com.samurai.el.field.Block;
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
	public int mode; // 0 is planet mode, 1 is battle mode 
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
	public int[] teamScores;
	public AIProgramCenter aiProgram;
	public MessageDispatcher messageDispatcher;
	public boolean stoped;
	
	private GameInstance(int mapid, int time, int mode) {
		totalTime = time;
		currentTime = totalTime;
		this.mode = mode;
		winFlag = -1;
		aiProgram = new AIProgramCenter();
		stoped = false;
		teamScores = new int[2];
		
		//setMap
		switch(mapid) {
		case 0:
			field = new Field0(mode);
			break;
		case 1:
			field = new Field1(mode);
			break;
		case 2:
			field = new Field2(mode);
			break;
		case 3:
			field = new Field3(mode);
			break;
		default:
			field = new Field0(mode);
			break;
		}
		
				
		playerBatch = new SpriteBatch();
				
	}
	
	public void initializePlayer(int humanid, int[][]AI) {
		//initialize players
		players = new Array<Player>();
		
		//add player	
		switch(humanid) {
		case 0:
			players.add(redSpear = new RedSpear(field.homePositions[0]));
			redSpear.isHuman = true;
			human = redSpear;
			break;
		case 1:
			players.add(redSword = new RedSword(field.homePositions[1]));
			redSword.isHuman = true;
			human = redSword;
			break;
		case 2:
			players.add(redAxe = new RedAxe(field.homePositions[2]));
			redAxe.isHuman = true;
			human = redAxe;
			break;
		case 3:
			players.add(blueSpear = new BlueSpear(field.homePositions[3]));
			blueSpear.isHuman = true;
			human = blueSpear;
			break;
		case 4:
			players.add(blueSword = new BlueSword(field.homePositions[4]));
			blueSword.isHuman = true;
			human = blueSword;
			break;
		case 5:
			players.add(blueAxe = new BlueAxe(field.homePositions[5]));
			blueAxe.isHuman = true;
			human = blueAxe;
			break;
		}
		
		for(int i = 0; i < AI.length; i++) {
			switch(AI[i][0]) {
			case 0:
				players.add(redSpear = new RedSpear(field.homePositions[0]));
				redSpear.isHuman = false;
				break;
			case 1:
				players.add(redSword = new RedSword(field.homePositions[1]));
				redSword.isHuman = false;
				break;
			case 2:
				players.add(redAxe = new RedAxe(field.homePositions[2]));
				redAxe.isHuman = false;
				break;
			case 3:
				players.add(blueSpear = new BlueSpear(field.homePositions[3]));
				blueSpear.isHuman = false;
				break;
			case 4:
				players.add(blueSword = new BlueSword(field.homePositions[4]));
				blueSword.isHuman = false;
				break;
			case 5:
				players.add(blueAxe = new BlueAxe(field.homePositions[5]));
				blueAxe.isHuman = false;
				break;
			}
			aiProgram.setAI(players.get(players.size-1), AI[i][1], this.mode);
		}
		
		for(Player p : players) {
			field.homeInitialize(p.id);	
			
			if(p.side == human.side) {
				p.isAllied = true;
				p.isVisible = true;
				field.openVision(p.homePosition);				
			}	
		}
		
		aiProgram.initializeEnemies();
		
		
		
	}
	
	public static GameInstance getInstance() {
		return instance;
	}
	
	public static void setInstance(int mapid, int human, int[][]AI, int time) {
			instance = new GameInstance(mapid, time, 0);
			instance.initializePlayer(human, AI);
	}
	
	public static void setInstance(int mapid, int human, int[][]AI, int time, int mode) {
		instance = new GameInstance(mapid, time, mode);
		instance.initializePlayer(human, AI);
	}
	
	public void render() {
		if(!stoped) {
			currentTime -= Gdx.graphics.getDeltaTime();
			aiProgram.update();
		}
		
			field.render();
			
			playerBatch.begin();
			for(Player p: players) {
				p.draw(playerBatch);
			}						
			playerBatch.end();
			
			
	}
	
	/**only return the game data to overScreen, no influence in the current game */
	public int[][] gameOver() {
		int[][] result = new int[6][4];
		int[] scores = new int[6];
		for(Player p: players) {
			result[p.id][0] = scores[p.id] = p.score;
			result[p.id][1] = p.killNum;
			result[p.id][2] = p.killedNum;
			result[p.id][3] = p.planetOccupyNum;
		}
		int redScore = scores[0] + scores[1] + scores[2];
		int blueScore = scores[3] + scores[4] + scores[5];
		
		if(field.planets != null) {
			for(Block b: field.planets) {
				if(b.side == 0) {
					redScore += 30;
				}
				else if(b.side == 1) {
					blueScore += 30;
				}
			}
		}
		
		if((human.side == 0 && redScore > blueScore) || (human.side == 1 && blueScore > redScore)) {
			winFlag = 1;
		} else if((human.side == 0 && redScore < blueScore) || (human.side == 1 && blueScore < redScore)) {
			winFlag = 0;
		}
		
		teamScores[0] = redScore;
		teamScores[1] = blueScore;
		
		return result;
	}
	
	public void dispose() {
		playerBatch.dispose();
		for(Player p: players) {
			p.dispose();
		}
		field.dispose();
	}
	
	/**stop the AI update and show the whole map */
	public void stop() {
		stoped = true;
		for(int i = 0; i < field.blocks.length; i++) {
			for(int j = 0; j < field.blocks[0].length; j++) {
				field.blocks[i][j].show();
			}
		}
		
	}

	/** Set the instance to null without dispose */
	public static void closeInstance() {
		instance = null;
		
	}
}
