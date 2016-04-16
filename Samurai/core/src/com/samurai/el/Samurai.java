package com.samurai.el;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Samurai extends Game {
	public SpriteBatch batch;
	
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));
	}

	public void render () {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
	}
}
