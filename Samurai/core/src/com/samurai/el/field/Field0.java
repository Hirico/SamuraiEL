package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field0 extends Field {
	
	public Field0(int mode) {
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background0();
		size = new Vector2(19, 12);
		blockSize = 48;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 19; i++) {
			for(int j = 0; j <= 12; j++) {
				blocks[i][j] = new Block(blockSize);
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(4,0), new Vector2(0,6), new Vector2(4,12), new Vector2(6,6), 
				new Vector2(10,6), new Vector2(14,6) 
		};
		
		if(mode == 0) {
			planetPositions = new Vector2[] {
					new Vector2(2,8), new Vector2(2,4), new Vector2(4,6), new Vector2(10,10),
					new Vector2(10,2)
			};
			planets = new Block[] {
					blocks[2][8], blocks[2][4], blocks[4][6], blocks[10][10], blocks[10][2]
			};
			
			
			for(int i = 0; i < planets.length; i++) {
				planets[i].setPlanet(2, planetPositions[i]);
			}
		}
		
		
	}
	
	public Field0() {
		//this is initialized before player, so player reference is not accessible here
		this(0);		
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(160, 48);
	}
}
