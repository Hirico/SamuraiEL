package com.samurai.el.maingame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;

public class GameScreen implements Screen {
	Game game;
	private GameInstance gameInstance;
	private SpriteBatch uiBatch;
	private GameStage stage;
	private double gameOverCountDown;
	private int[][] result;
	public boolean paused;
	public Music endMusic;

	public GameScreen(Game game) {
		result = new int[6][3];
		this.game = game;
		gameInstance = GameInstance.getInstance();	
		stage = new GameStage(gameInstance, this);
		uiBatch = new SpriteBatch();		
		gameOverCountDown = 3f;
		paused = false;
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		uiBatch = new SpriteBatch();	
	}

	@Override
	public void render(float delta) {
		if(!paused) {
			Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	        Gdx.gl20.glClearColor(255, 255, 255, 1);
	        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        gameInstance.render();
	        uiBatch.begin();
	        stage.act();
	        uiBatch.end();
	        if(gameInstance.currentTime <= 0 && !gameInstance.stoped) {
	        	result = gameInstance.gameOver(); 
	        	gameInstance.stop();
	        	stage.stop();
	        	gameInstance.field.background.music.stop();
	        	if(gameInstance.winFlag == 1 || gameInstance.winFlag == -1) {
	        		endMusic = Resources.getInstance().bgm06;
	        	} else {
	        		endMusic = Resources.getInstance().bgm07;
	        	}
	        	endMusic.setLooping(true);
	        	endMusic.play();	        	
	        }
	        else if(gameInstance.currentTime <= 0 && gameInstance.stoped) { 
	        	if(gameOverCountDown >= 0) {
	        		gameOverCountDown -= Gdx.graphics.getDeltaTime();
	        	} else {
					//result[6][3] contains each samurai's score(territory+killNum), killNum, killedNum
					//winFlag: 1:win 0:lose -1:tied  
	        		game.setScreen(new OverScreen(result, gameInstance.winFlag, endMusic));        	
					dispose();				
					GameInstance.closeInstance();
					Resources.getInstance().reInit(); 
	        	}
	        }
		}
        
		
	}
	
	/**End the game and back to main menu  */
	public void exit() {
		ScreenCenter.setscreen(0);// dispose() is in this method   
		ScreenCenter.startmusic();
		GameInstance.closeInstance();
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
	}

	
}
