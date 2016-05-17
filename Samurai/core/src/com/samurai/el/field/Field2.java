package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field2 extends Field {
	
	public Field2(int mode) {
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background2();
		size = new Vector2(9, 9);
		blockSize = 48;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 9; i++) {
			for(int j = 0; j <= 9; j++) {
				blocks[i][j] = new Block(blockSize);
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(0,0), new Vector2(0,9), new Vector2(9,0), new Vector2(3,3), 
				new Vector2(6,6), new Vector2(9,9) 
		};
		
		if(mode == 0) {
			planetPositions = new Vector2[] {
					new Vector2(3,6), new Vector2(6,3)
			};
			planets = new Block[] {
					blocks[3][6], blocks[6][3]
			};
			
			
			for(int i = 0; i < planets.length; i++) {
				planets[i].setPlanet(2, planetPositions[i]);
			}
		}
		
		
		
	}
	
	public Field2() {
		this(0);
	}
	
	
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(400, 120);
	}
}
