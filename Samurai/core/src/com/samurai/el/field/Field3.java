package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field3 extends Field {
	
	public Field3(int mode) {
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background3();
		size = new Vector2(11, 11);
		blockSize = 48;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 11; i++) {
			for(int j = 0; j <= 11; j++) {
				blocks[i][j] = new Block(blockSize);
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(3,9), new Vector2(4,6), new Vector2(6,3), new Vector2(8,4), 
				new Vector2(7,8), new Vector2(6,10) 
		};
		
		if(mode == 0) {
			planetPositions = new Vector2[] {
					new Vector2(3,2), new Vector2(6,6), new Vector2(8,9)
			};
			planets = new Block[] {
					blocks[3][2], blocks[6][6], blocks[8][9]
			};
			
			
			for(int i = 0; i < planets.length; i++) {
				planets[i].setPlanet(2, planetPositions[i]);
			}
		}
		
		
	}
	
	public Field3() {
		this(0);
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(352, 72);
	}
}
