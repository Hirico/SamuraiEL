package com.samurai.el.maingame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;

public class GameScreen implements Screen {
	Game game;
	private GameInstance gameInstance;
	private SpriteBatch uiBatch;
	public Sprite[] timeBlocks;
	public int[] timeChecks;
	
	private GameStage stage;
	private double gameOverCountDown;
	private int[][] result;
	public boolean paused;
	public Music endMusic;
	
	public Sprite attackIcon;
	public Sprite attackFade;
	
	public Sprite redScore;
	public Sprite blueScore;
	public Sprite middleScore;
	
	public int UILayerNum;
	
	Sprite blackFade;
	SpriteBatch fadeBatch;
	public float fade;
	public boolean finished;
	
	public Sprite endBackground;
	public SpriteBatch endBatch;
	
	public Sprite guideInfo;
	public SpriteBatch guideBatch;
	

	public GameScreen(Game game) {
		result = new int[6][4];
		this.game = game;
		
		redScore = Resources.getInstance().redScore;
		blueScore = Resources.getInstance().blueScore;
		middleScore = Resources.getInstance().middleScore;
		
		redScore.setPosition(140f, 699f);
		blueScore.setPosition(640f, 699f);
		middleScore.setPosition(616f, 677f);
		
		gameInstance = GameInstance.getInstance();	
		stage = new GameStage(gameInstance, this);
		uiBatch = new SpriteBatch();
		timeBlocks = new Sprite[18];
		for(int i = 0; i < timeBlocks.length; i++) {
			timeBlocks[i] = new Sprite();
			if(gameInstance.human.side == 0) {
				timeBlocks[i].set(Resources.getInstance().time0);
			} else {
				timeBlocks[i].set(Resources.getInstance().time1);
			}
			timeBlocks[i].setPosition(163 + 53*i, 671.5f);
		}
		
		timeChecks = new int[18];		
		int timeStep = gameInstance.totalTime / 18;
		for(int i = 0; i < timeChecks.length; i++) {
			timeChecks[i] = timeStep * (i + 1);
		}
		
		attackIcon = new Sprite();
		attackFade = new Sprite();
		attackFade.set(Resources.getInstance().attackFade);
		
		UILayerNum = 1;
		
		gameOverCountDown = 3f;
		paused = false;
		
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		blackFade = new Sprite();
		blackFade.set(Resources.getInstance().blackFade);
		finished = false;
		
		endBatch = new SpriteBatch();
	}
	
	@Override
	public void show() {
		fade = 0f;
		finished = false;
		Gdx.input.setInputProcessor(stage);
		uiBatch = new SpriteBatch();
		if(gameInstance.human.side == 0) {
			attackIcon.set(Resources.getInstance().attack0Icon);
		} else {
			attackIcon.set(Resources.getInstance().attack1Icon);
		}
		if(gameInstance.mode == -1) {
			gameInstance.initializeGuideLevel();
			guideBatch = new SpriteBatch();
		}
		attackIcon.setPosition(640 - 24, 20);
		attackFade.setPosition(640 - 24, 20);
	}

	@Override
	public void render(float delta) {
		if(!paused) {
			Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	        Gdx.gl20.glClearColor(255, 255, 255, 1);
	        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        
	        gameInstance.render();
	        stage.act();
	        
	        if(gameInstance.mode != -1) {
	        	if(UILayerNum >= 1) {
			        uiBatch.begin();
			        
				    for(int i = 0; i < timeBlocks.length; i++) {
				        if(gameInstance.currentTime > timeChecks[i]) {
				        	timeBlocks[i].draw(uiBatch);
				        }
				    }
				    
				    redScore.setSize(1000 * (float)gameInstance.teamScores[0]/(gameInstance.teamScores[1]+gameInstance.teamScores[0]), redScore.getHeight());
				    redScore.draw(uiBatch);
				    blueScore.setSize(1000 * (float)gameInstance.teamScores[1]/(gameInstance.teamScores[1]+gameInstance.teamScores[0]), blueScore.getHeight());
				    blueScore.setPosition(140+redScore.getWidth(), 699f);
					blueScore.draw(uiBatch);
					middleScore.draw(uiBatch);
			     
			        
			        float attackPercent = (float)(gameInstance.human.cooldownTime/gameInstance.human.totalCooldownTime);
			        if(attackPercent != 0) {
			        	attackIcon.setAlpha(0.6f);
			        } else {
			        	attackIcon.setAlpha(1f);	        	
			        }
			        attackIcon.draw(uiBatch);
			        attackFade.setSize(48, 48 * attackPercent);
			        attackFade.draw(uiBatch);
			        
			        uiBatch.end();
	        	}
	        } else {
	        	switch(gameInstance.guideLevel) {
	        	case 0:
	        		guideInfo = Resources.getInstance().guideL0;
	        		break;
	        	case 1:
	        		guideInfo = Resources.getInstance().guideL1;
	        		break;
	        	case 2:
	        		guideInfo = Resources.getInstance().guideL2;
	        		break;
	        	case 3:
	        		guideInfo = Resources.getInstance().guideL3;
	        		break;
	        	case 4:
	        		guideInfo = Resources.getInstance().guideL4;
	        		break;
	        	}
	        	guideBatch.begin();
	        	guideInfo.draw(guideBatch);
	        	guideBatch.end();
	        }
	        
	        
	        if(gameInstance.currentTime <= 0 && !gameInstance.stoped) {
	        	result = gameInstance.gameOver(); 
	        	gameInstance.stop();
	        	stage.stop();
	        	gameInstance.field.background.music.stop();
	        	if(gameInstance.winFlag == 1 || gameInstance.winFlag == -1) {
	        		endMusic = Resources.getInstance().bgm06;
	        		
	        		if(gameInstance.human.side == 0 && gameInstance.winFlag != -1) {
	        			endBackground = Resources.getInstance().redWin;
	        		} 
	        		else if(gameInstance.human.side == 1 && gameInstance.winFlag != -1) {
	        			endBackground = Resources.getInstance().blueWin;
	        		}
	        		else {
	        			endBackground = Resources.getInstance().draw;
	        		}
	        		
	        	} else {
	        		endMusic = Resources.getInstance().bgm07;
	        		
	        		if(gameInstance.human.side == 0) {
	        			endBackground = Resources.getInstance().redLose;
	        		} 
	        		else if(gameInstance.human.side == 1) {
	        			endBackground = Resources.getInstance().blueLose;
	        		}
	        	}
	        	endMusic.setLooping(true);
	        	endMusic.setVolume(Gdx.app.getPreferences("volumePref").getFloat("musicVolume",0.5f));
	        	endMusic.play();	        	
	        }
	        else if(gameInstance.currentTime <= 0 && gameInstance.stoped) { 
	        	if(gameOverCountDown >= 0) {
	        		gameOverCountDown -= Gdx.graphics.getDeltaTime();
	        	} else {
					//result[6][4] contains each samurai's score(territory+killNum), killNum, killedNum
					//winFlag: 1:win 0:lose -1:tied
	        		finished = true;
	        	}
	        }
	        
	        if (finished) {
				fade = Math.min(fade + Gdx.graphics.getDeltaTime() / 2.f, 1);
				fadeBatch.begin();
				blackFade.setColor(blackFade.getColor().r, blackFade.getColor().g, blackFade.getColor().b, fade);
				blackFade.draw(fadeBatch);
				fadeBatch.end();
				if (fade >= 1 && gameInstance.mode != -1) {
					game.setScreen(new OverScreen(result, gameInstance.winFlag, endMusic, gameInstance.teamScores));        	
					dispose();				
					GameInstance.closeInstance();
					Resources.getInstance().reInit(); 
				}
				else if(fade >= 1 && gameInstance.mode == -1) {
					exit();
				}
			}
	        
	        if(endBackground != null && fade < 1 && gameInstance.mode != -1) {
		        endBatch.begin();
	        	endBackground.draw(endBatch);
	        	endBatch.end();
	        }
		}
        
		
	}
	
	/**open/close HUD */
	public void changeUILayer() {
		if(UILayerNum == 0) {
			UILayerNum = 1;
		}
		else if(UILayerNum == 1) {
			UILayerNum = 0;
		}
	}
	
	/**End the game and back to main menu  */
	public void exit() {
		if(endMusic != null) {
			endMusic.stop();
		}
		ScreenCenter.setscreen(0);// dispose() is in this method   
		ScreenCenter.startmusic();
		GameInstance.closeInstance();
		Timer.clearInstances();
		Resources.getInstance().reInit();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		uiBatch.dispose();
		gameInstance.dispose();
		stage.dispose();
		fadeBatch.dispose();
		endBatch.dispose();
	}

	
}
