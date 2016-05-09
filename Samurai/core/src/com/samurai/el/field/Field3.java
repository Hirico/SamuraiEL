package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field3 extends Field {
	
	public Field3() {
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background3();
		size = new Vector2(11, 11);
		blockSize = 48;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 11; i++) {
			for(int j = 0; j <= 11; j++) {
				blocks[i][j] = new Block();
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(3,9), new Vector2(4,6), new Vector2(6,3), new Vector2(8,4), 
				new Vector2(7,8), new Vector2(6,10) 
		};
		for(int i = 0; i < 5; i++) {
			blocks[(int) homePositions[i].x][(int) homePositions[i].y].isHome = true;
		}
		
		
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(352, 72);
	}
}
