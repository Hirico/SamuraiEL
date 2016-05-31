package com.samurai.el.maingame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samurai.el.field.Field0;
import com.samurai.el.field.Field1;
import com.samurai.el.field.Field2;
import com.samurai.el.field.Field3;
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
	public boolean isConquerMode;
	
	public GameloadScreen(Game game, boolean isConquerMode) {
		this.game = game;
		this.isConquerMode = isConquerMode;
		batch = new SpriteBatch();
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		
		if(!isConquerMode) {
			if(GameInstance.getInstance().field instanceof Field0 || GameInstance.getInstance().field instanceof Field1) {
				background = Resources.getInstance().gameload;
			} 
			else if(GameInstance.getInstance().field instanceof Field2 || GameInstance.getInstance().field instanceof Field3){
				background = Resources.getInstance().gameload2;
			}
		} else {
			background = Resources.getInstance().gameload3;
		}
		
		blackFade = new Sprite();
		blackFade.set(Resources.getInstance().blackFade);
		background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
	}
	@Override
	public void show() {
		countdown = 2.5f;
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
				if(!isConquerMode) {
					game.setScreen(new GameScreen(game));
				} else {
					game.setScreen(new ConquerSetScreen(game));
				}
				dispose();
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
		batch.dispose();
		
	}
	
}
