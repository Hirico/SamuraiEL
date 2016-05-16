package com.samurai.el.maingame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samurai.el.field.Field0;
import com.samurai.el.field.Field1;
import com.samurai.el.resource.Resources;


public class GameloadScreen implements Screen{

	public SpriteBatch batch;
	public SpriteBatch fadeBatch;
	public Sprite blackFade;
	public Sprite background;
	public Game game;
	public float countdown;
	public float fade;
	public boolean finished;
	
	public GameloadScreen(Game game) {
		this.game = game;
		batch = new SpriteBatch();
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		
		if(GameInstance.getInstance().field instanceof Field0 || GameInstance.getInstance().field instanceof Field1) {
			background = Resources.getInstance().gameload;
		} else {
			background = Resources.getInstance().gameload2;
		}
		blackFade = Resources.getInstance().blackFade;
		
	}
	@Override
	public void show() {
		countdown = 3f;
		fade = 1.0f;
		finished = false;
	}

	@Override
	public void render(float delta) {
		countdown -= delta;
		if(countdown <= 0) {
			finished = true;
		}
		batch.begin();
		background.draw(batch);
		batch.end();
		
		if (!finished && fade > 0) {
			fade = Math.max(fade - Gdx.graphics.getDeltaTime() / 2.f, 0);
			fadeBatch.begin();
			blackFade.setColor(blackFade.getColor().r, blackFade.getColor().g, blackFade.getColor().b, fade);
			blackFade.draw(fadeBatch);
			fadeBatch.end();
		}

		if (finished) {
			fade = Math.min(fade + Gdx.graphics.getDeltaTime() / 2.f, 1);
			fadeBatch.begin();
			blackFade.setColor(blackFade.getColor().r, blackFade.getColor().g, blackFade.getColor().b, fade);
			blackFade.draw(fadeBatch);
			fadeBatch.end();
			if (fade >= 1) {
				game.setScreen(new GameScreen(game));
				dispose();
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
		batch.dispose();
		
	}
	
}
