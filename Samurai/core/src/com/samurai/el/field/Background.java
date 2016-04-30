package com.samurai.el.field;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Background {
	protected Sprite background;
	protected SpriteBatch backBatch;	
	protected Music music;
		
	public void render() {
		//backBatch.begin();
		//backBatch.end();
	}
	
	public void dispose() {
		backBatch.dispose();
		music.dispose();
	}
}
