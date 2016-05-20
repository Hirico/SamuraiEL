package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field1 extends Field {
	
	public Field1(int mode) {
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background1();
		size = new Vector2(20, 11);
		blockSize = 48;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 20; i++) {
			for(int j = 0; j <= 11; j++) {
				blocks[i][j] = new Block(blockSize);
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(6,8), new Vector2(10,9), new Vector2(14,8), new Vector2(6,3), 
				new Vector2(10,2), new Vector2(14,3) 
		};
		
		
		if(mode == 0) {
			planetPositions = new Vector2[] {
					new Vector2(4,8), new Vector2(4,3), new Vector2(5,5), new Vector2(16,8),
					new Vector2(16,3), new Vector2(15,6)
			};
			planets = new Block[] {
					blocks[4][8], blocks[4][3], blocks[5][5], blocks[16][8],
					blocks[16][3], blocks[15][6]
			};
			
			
			for(int i = 0; i < planets.length; i++) {
				planets[i].setPlanet(2, planetPositions[i]);
			}
		}
		
		
	}
	
	public Field1() {
		this(0);
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(144, 40);
	}
}
