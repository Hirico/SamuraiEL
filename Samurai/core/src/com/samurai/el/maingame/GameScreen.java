package com.samurai.el.maingame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen, InputProcessor {
	private Game game;
	private GameInstance gameInstance;
	private SpriteBatch uiBatch;

	public GameScreen(Game game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		gameInstance = GameInstance.getInstance();
	}
	
	@Override
	public void show() {
	
	}

	@Override
	public void render(float delta) {
		//
		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameInstance.render();
        //uiBatch.begin();
        //uiBatch.end();
		
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
		uiBatch.dispose();
		gameInstance.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.UP || keycode == Input.Keys.W) {
			gameInstance.getInstance().human.moveBegin(0);
		}
		if(keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
			gameInstance.getInstance().human.moveBegin(1);
		}
		if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
			gameInstance.getInstance().human.moveBegin(2);
		}
		if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
			gameInstance.getInstance().human.moveBegin(3);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.UP || keycode == Input.Keys.W
			|| keycode == Input.Keys.DOWN || keycode == Input.Keys.S
			|| keycode == Input.Keys.LEFT || keycode == Input.Keys.A
			|| keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
			gameInstance.getInstance().human.moveEnd();
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
