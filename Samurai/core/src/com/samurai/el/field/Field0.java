package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field0 extends Field {
	
	public Field0() {
		super();
		background = new Background0();
		size = new Vector2(19, 19);
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 19; i++) {
			for(int j = 0; j <= 19; j++) {
				blocks[i][j] = new Block();
			}
		}
		
		blockSize = 32;
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(320, 40);
	}
}
