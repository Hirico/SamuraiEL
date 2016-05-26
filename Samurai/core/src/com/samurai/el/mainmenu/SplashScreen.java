package com.samurai.el.mainmenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen, InputProcessor{
	public Texture[] frames;
	public int count;
	public float frameLatency;
	public SpriteBatch batch;
	public Game game;
	public Music bgm;
	
	public SplashScreen(Game game) {
		this.game = game;
		frames = new Texture[264];
		bgm = Gdx.audio.newMusic(Gdx.files.internal("music/bgm0.mp3"));
		
		count = 0;
		batch = new SpriteBatch();
		frameLatency = 0.001f;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		if(count >= 264) {
			bgm.stop();
			ScreenCenter.setscreencenter(game);
			game.setScreen(new MainMenuScreen());
			dispose();
		} else {
			if(count < 10) {
				frames[count] = new Texture(Gdx.files.internal("img/splash/00"+count+".jpg"));
			}
			else if(count < 100) {
				frames[count] = new Texture(Gdx.files.internal("img/splash/0"+count+".jpg"));
			}
			else {
				frames[count] = new Texture(Gdx.files.internal("img/splash/"+count+".jpg"));
			}
			frameLatency -= Gdx.graphics.getDeltaTime();
			batch.begin();
			batch.draw(frames[count], 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.end();
			if(count == 12) {
				bgm.play();
			}
			if(frameLatency <= 0) {
				count += 1;
				frameLatency = 0.001f;
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		for(int i = 0; i < 264; i++) {
			if(frames[i] == null) break;
			frames[i].dispose();
		}
		bgm.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		ScreenCenter.setscreencenter(game);
		game.setScreen(new MainMenuScreen());
		dispose();
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		ScreenCenter.setscreencenter(game);
		game.setScreen(new MainMenuScreen());
		dispose();
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	
	
}
