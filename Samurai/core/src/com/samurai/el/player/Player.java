package com.samurai.el.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Player extends Sprite{
	public boolean isHuman;
	public Vector2 position;
	public int direction;
	public int side;
	public boolean isVisible;
	public boolean isHidden;
	public boolean isRecovering;
	public double recoverLeftTime;
	
	
	@Override
	public void draw(Batch batch) {
		//implements waited
		super.draw(batch);
	}
	
}
