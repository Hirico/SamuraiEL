package com.samurai.el.field;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Block extends Sprite{
	public boolean isVisible;
	public int owner;
	public int side;
	
	public Block() {
		super();
		owner = -1;
		side = -1;
		isVisible = false;
	}
	
	
	public void occupy(int owner) {
		this.owner = owner;
		if(owner <= 2) {
			side = 0;
		} else {
			side = 1;
		}
		//implements to set sprite
	}
	
	public void hide() {
		isVisible = false;
		//implements to set sprite
	}
	
	public void show() {
		isVisible = true;
		//implements to show enemy not hidden on it
		//implements to set sprite
	}
}
