package com.samurai.el.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.samurai.el.ai.AIProgramCenter;
import com.samurai.el.field.Field;
import com.samurai.el.field.Field0;
import com.samurai.el.field.Field1;
import com.samurai.el.field.Field2;
import com.samurai.el.field.Field3;
import com.samurai.el.field.Field4;
import com.samurai.el.field.FieldGuide;
import com.samurai.el.player.BlueAxe;
import com.samurai.el.player.BlueSpear;
import com.samurai.el.player.BlueSword;
import com.samurai.el.player.Player;
import com.samurai.el.player.RedAxe;
import com.samurai.el.player.RedSpear;
import com.samurai.el.player.RedSword;

public class GameInstance implements Disposable{
	
	public static GameInstance instance;
	public int mode; // 0 is planet mode, 1 is battle mode, -1 is guide mode
	public int guideLevel; // record which guide step is now
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
	public Array<Player> redPlayers;
	public Array<Player> bluePlayers;
	public Field field;
	public SpriteBatch batch; // draw field and player
	public int winFlag;
	public int[] teamScores;
	public AIProgramCenter aiProgram;
	public boolean stoped;
	public OrthographicCamera camera;
	public OrthographicCamera backgroundCamera;
	public float cameraLerp;
	public static int conquerDifficulty; // conquerMode difficulty
	
	private GameInstance(int mapid, int time, int mode) {

		players = new Array<Player>(16);
		redPlayers = new Array<Player>();
		bluePlayers = new Array<Player>();
		totalTime = time;
		currentTime = totalTime;
		this.mode = mode;
		winFlag = -1;
		aiProgram = new AIProgramCenter();
		stoped = false;
		teamScores = new int[2];
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		backgroundCamera = new OrthographicCamera();
		backgroundCamera.setToOrtho(false, 1280, 720);
		cameraLerp = 0.6f;
				
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
		case 4:
			field = new Field4();
			break;
		default:
			field = new Field0(mode);
			break;
		}
		
				
		batch = new SpriteBatch();				
	}
	
	/**initialize a guide map */
	private GameInstance() {
		players = new Array<Player>();
		totalTime = 10000;
		currentTime = totalTime;
		this.mode = -1;
		
		teamScores = new int[2];
		winFlag = -1;
		aiProgram = new AIProgramCenter();
		stoped = false;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		backgroundCamera = new OrthographicCamera();
		backgroundCamera.setToOrtho(false, 1280, 720);
		cameraLerp = 0.6f;
		field = new FieldGuide();
		guideLevel = 0;
		
		batch = new SpriteBatch();
		
	}
	
	/**standardly and "statically" initialize all players before start */
	public void initializePlayer(int humanid, int[][]AI) {
		
		//add player
		if(mode != 2) {
			switch(humanid) {
			case 0:
				players.add(redSpear = new RedSpear(field.redHomeCount,field.homePositions[0]));
				redSpear.isHuman = true;
				human = redSpear;
				break;
			case 1:
				players.add(redSword = new RedSword(field.redHomeCount,field.homePositions[1]));
				redSword.isHuman = true;
				human = redSword;
				break;
			case 2:
				players.add(redAxe = new RedAxe(field.redHomeCount,field.homePositions[2]));
				redAxe.isHuman = true;
				human = redAxe;
				break;
			case 3:
				players.add(blueSpear = new BlueSpear(field.blueHomeCount,field.homePositions[3]));
				blueSpear.isHuman = true;
				human = blueSpear;
				break;
			case 4:
				players.add(blueSword = new BlueSword(field.blueHomeCount,field.homePositions[4]));
				blueSword.isHuman = true;
				human = blueSword;
				break;
			case 5:
				players.add(blueAxe = new BlueAxe(field.blueHomeCount,field.homePositions[5]));
				blueAxe.isHuman = true;
				human = blueAxe;
				break;
			}
			if(AI.length > 0) {
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
			
		} else {
			switch(humanid) {
			case 0:
				players.add(redSpear = new RedSpear(field.redHomeCount,field.nextRedHome()));
				redSpear.isHuman = true;
				human = redSpear;
				redPlayers.add(human);
				break;
			case 1:
				players.add(redSword = new RedSword(field.redHomeCount,field.nextRedHome()));
				redSword.isHuman = true;
				human = redSword;
				redPlayers.add(human);
				break;
			case 2:
				players.add(redAxe = new RedAxe(field.redHomeCount,field.nextRedHome()));
				redAxe.isHuman = true;
				human = redAxe;
				redPlayers.add(human);
				break;
			case 3:
				players.add(blueSpear = new BlueSpear(field.blueHomeCount,field.nextBlueHome()));
				blueSpear.isHuman = true;
				human = blueSpear;
				bluePlayers.add(human);
				break;
			case 4:
				players.add(blueSword = new BlueSword(field.blueHomeCount,field.nextBlueHome()));
				blueSword.isHuman = true;
				human = blueSword;
				bluePlayers.add(human);
				break;
			case 5:
				players.add(blueAxe = new BlueAxe(field.blueHomeCount,field.nextBlueHome()));
				blueAxe.isHuman = true;
				human = blueAxe;
				bluePlayers.add(human);
				break;
			}
			field.homeInitialize(human);
			human.isAllied = true;
			human.isVisible = true;
			field.openVision(human.homePosition);
		}
		
				
	}
	
	/**help initialize field when dynamically add player */
	public void initializePlayer(Player p) {
		field.homeInitialize(p);	
		
		if(p.side == human.side) {
			p.isAllied = true;
			p.isVisible = true;
			field.openVision(p.homePosition);				
		}	
	}
	
	public static GameInstance getInstance() {
		return instance;
	}
	
	/**use this to start a guide level */
	public static void setInstance() {
		instance = new GameInstance();
		instance.initializePlayer(0, new int[][]{{3,-1}});

	}
	
	/**default instance set */
	public static void setInstance(int mapid, int human, int[][]AI, int time) {
		instance = new GameInstance(mapid, time, 0);
		instance.initializePlayer(human, AI);
	}
	
	/**standard instance set */
	public static void setInstance(int mapid, int human, int[][]AI, int time, int mode) {
		instance = new GameInstance(mapid, time, mode);
		instance.initializePlayer(human, AI);
	}
	
	/**conquer instance set */
	public static void setInstance(int human, int difficulty, int time) {
		instance = new GameInstance(4, time, 2);
		instance.initializePlayer(human, new int[0][0]);
		conquerDifficulty = difficulty;
	}
	
	public void render() {
		if(!stoped) {
			currentTime -= Gdx.graphics.getDeltaTime();
			aiProgram.update();
		}
		Vector3 position = camera.position;
		position.x += (human.getX()+human.getWidth()/2 - position.x) * cameraLerp * Gdx.graphics.getDeltaTime();
		position.y += 1.5 * (human.getY()+human.getHeight()/2 - position.y) * cameraLerp * Gdx.graphics.getDeltaTime();
		
		camera.position.set(position.x, position.y, 0);
		camera.update();
		//backgroundCamera.position.set(640 + human.getX()/(field.blockSize*field.blocks.length)*640 , 360 ,0);
		backgroundCamera.update();
		
		batch.setProjectionMatrix(camera.combined);
			
		field.render();	
		batch.begin();		
		for(int i = 0; i < players.size; i++) {
			players.get(i).draw(batch);
		}						
		batch.end();
						
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
		
		if((human.side == 0 && teamScores[0] > teamScores[1]) || (human.side == 1 && teamScores[1] > teamScores[0])) {
			winFlag = 1;
		} else if((human.side == 0 && teamScores[0] < teamScores[1]) || (human.side == 1 && teamScores[1] < teamScores[0])) {
			winFlag = 0;
		}
		
		//teamScores[0] = redScore;
		//teamScores[1] = blueScore;
		
		return result;
	}
	
	public void initializeGuideLevel() {
		guideLevel = 0;
		if(blueSpear != null) {
			blueSpear.hide();
			blueSpear.isInvincible = true;
		}
		human.direction = 0;
	}
	
	public void updateGuideLevel() {
		if(guideLevel == 0) {
			Timer.clearInstances();
			resetPlayerPosition(human);
			human.direction = 0;			
		}
		else if(guideLevel == 1) {
			Timer.clearInstances();
			resetPlayerPosition(human);
			human.direction = 0;
			field.clearBlocks();
			
			if(blueSpear != null) {
				field.blocks[2][2].isPlanet = false;
				blueSpear.isInvincible = false;
				blueSpear.show();
				setPlayerPosition(blueSpear, new Vector2(2f,2f));
			}
		}
		else if(guideLevel == 2) {
			Timer.clearInstances();
			resetPlayerPosition(human);
			human.direction = 0;
			field.clearBlocks();
			if(blueSpear != null) {
				resetPlayerPosition(blueSpear);
				blueSpear.hide();
				blueSpear.isInvincible = true;
			}
		}
		else if(guideLevel == 3) {
			Timer.clearInstances();
			resetPlayerPosition(human);
			human.direction = 0;
			field.clearBlocks();
			
			blueSpear.killedNum = 0;
			blueSpear.show();
			blueSpear.isInvincible = false;
			aiProgram.setAI(blueSpear, 0, 1);
			aiProgram.initializeEnemies();
			System.out.println("set");
		}			
		guideLevel += 1;	
	}
	
	public void resetPlayerPosition(Player player) {
		setPlayerPosition(player, player.homePosition);
	}
	
	public void setPlayerPosition(Player player, Vector2 position) {
		field.blocks[(int) player.position.x][(int) player.position.y].playerLeave();
		field.closeVision(player.position, player);
		
		field.blocks[(int) position.x][(int) position.y].playerArrive(player);
		field.openVision(position);
		
		player.position.set(position);
		player.drawPosition.set(position);
	}
	
	public void dispose() {
		batch.dispose();
		for(Player p: players) {
			p.dispose();
		}
		field.dispose();
	}
	
	public void addAI(int character) {
		if(players.size < 16) {
			if(redPlayers.size < 8) {
				switch(character) {
				case 0:
					RedSpear p = new RedSpear(field.redHomeCount,field.nextRedHome());
					players.add(p);
					redPlayers.add(p);
					initializePlayer(p);
					aiProgram.setAI(p, conquerDifficulty, 2);
					aiProgram.refreshList();
					break;
				case 1:
					RedSword p1 = new RedSword(field.redHomeCount,field.nextRedHome());
					players.add(p1);
					redPlayers.add(p1);
					initializePlayer(p1);
					aiProgram.setAI(p1, conquerDifficulty, 2);
					aiProgram.refreshList();
					break;
				case 2:
					RedAxe p2 = new RedAxe(field.redHomeCount,field.nextRedHome());
					players.add(p2);
					redPlayers.add(p2);
					initializePlayer(p2);
					aiProgram.setAI(p2, conquerDifficulty, 2);
					aiProgram.refreshList();
					break;
				}
			}
			if(bluePlayers.size < 8) {
				switch(character) {
				case 3:
					BlueSpear p3 = new BlueSpear(field.blueHomeCount,field.nextBlueHome());
					players.add(p3);
					bluePlayers.add(p3);
					initializePlayer(p3);
					aiProgram.setAI(p3, conquerDifficulty, 2);
					aiProgram.refreshList();
					break;
				case 4:
					BlueSword p4 = new BlueSword(field.blueHomeCount,field.nextBlueHome());
					players.add(p4);
					bluePlayers.add(p4);
					initializePlayer(p4);
					aiProgram.setAI(p4, conquerDifficulty, 2);
					aiProgram.refreshList();
					break;
				case 5:
					BlueAxe p5 = new BlueAxe(field.blueHomeCount,field.nextBlueHome());
					players.add(p5);
					bluePlayers.add(p5);
					initializePlayer(p5);
					aiProgram.setAI(p5, conquerDifficulty, 2);
					aiProgram.refreshList();
					break;
				}
			}
		}
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
