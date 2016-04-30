package com.samurai.el.field;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.field.Background;

public abstract class Field{
	protected Background background;
	protected SpriteBatch fieldBatch;
	
	
	public void render() {
		background.render();
		
		//fieldBatch.begin();
		//fieldBatch.end();
	}
	
	public Vector2 getBottomLeftCorner() {
		// to be overridden
		return null;
	}
	
	public void dispose() {
		background.dispose();
		fieldBatch.dispose();
	}
	
}