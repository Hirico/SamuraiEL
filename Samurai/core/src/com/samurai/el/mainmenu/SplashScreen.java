package com.samurai.el.mainmenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen, InputProcessor{
	public Sprite[] frames;
	public int count;
	public float frameLatency;
	public SpriteBatch batch;
	public Game game;
	
	public SplashScreen(Game game) {
		this.game = game;
		frames = new Sprite[76];
		for(int i = 0 ; i < 76; i++) {
			frames[i] = new Sprite(new Texture(Gdx.files.internal("img/splash/01"+i+".jpg")));
		}
		count = 0;
		batch = new SpriteBatch();
		frameLatency = 0.05f;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		if(count >= 76) {
			ScreenCenter.setscreencenter(game);
			game.setScreen(new MainMenuScreen());
			System.out.println("set");
		} else {
			frameLatency -= Gdx.graphics.getDeltaTime();
			batch.begin();
			frames[count].draw(batch);
			batch.end();
			if(frameLatency <= 0) {
				count += 1;
				frameLatency = 0.05f;
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		ScreenCenter.setscreencenter(game);
		game.setScreen(new MainMenuScreen());
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
