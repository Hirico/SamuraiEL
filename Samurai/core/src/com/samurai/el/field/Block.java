package com.samurai.el.field;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Block extends Sprite{
	public boolean isVisible;
	public int owner;
	
	public Block() {
		super();
		owner = -1;
		isVisible = false;
	}
	
	public void occupy(int owner) {
		this.owner = owner;
		//implements to set sprite
	}
	
	public void hide() {
		isVisible = false;
		//implements to set sprite
	}
	
	public void show() {
		isVisible = true;
		//implements to set sprite
	}
}
