package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field0 extends Field {
	
	public Field0() {
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background0();
		size = new Vector2(29, 18);
		blockSize = 32;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 29; i++) {
			for(int j = 0; j <= 18; j++) {
				blocks[i][j] = new Block();
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(6,0), new Vector2(0,9), new Vector2(6,18), new Vector2(10,9), 
				new Vector2(15,9), new Vector2(20,9) 
		};
		for(int i = 0; i < 5; i++) {
			blocks[(int) homePositions[i].x][(int) homePositions[i].y].isHome = true;
		}
		
		
		
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(160, 56);
	}
}
