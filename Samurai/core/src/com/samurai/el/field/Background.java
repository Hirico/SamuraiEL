package com.samurai.el.field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class Background implements Disposable{
	public Sprite background;
	public SpriteBatch backBatch;	
	public Music music;
	public double beatInterval;
	
	public Background() {
		backBatch = new SpriteBatch();
	}
		
	public void render() {
		backBatch.begin();
		background.draw(backBatch);
		backBatch.end();
	}
	
	public void dispose() {
		backBatch.dispose();
		music.stop();
		music.dispose();
	}

	public void changeVolume() {
		music.setVolume(Gdx.app.getPreferences("volumePref").getFloat("musicVolume"));
		
	}
}
