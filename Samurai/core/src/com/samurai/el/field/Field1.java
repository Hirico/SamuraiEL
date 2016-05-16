package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field1 extends Field {
	
	public Field1() {
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background1();
		size = new Vector2(60, 33);
		blockSize = 16;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 60; i++) {
			for(int j = 0; j <= 33; j++) {
				blocks[i][j] = new Block(blockSize);
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(18,24), new Vector2(30,27), new Vector2(42,24), new Vector2(18,9), 
				new Vector2(30,6), new Vector2(42,9) 
		};
		
		planetPositions = new Vector2[] {
				new Vector2(12,24), new Vector2(12,9), new Vector2(15,15), new Vector2(48,24),
				new Vector2(48,9), new Vector2(48,18)
		};
		planets = new Block[] {
				blocks[12][24], blocks[12][9], blocks[15][15], blocks[48][24],
				blocks[48][9], blocks[48][18]
		};
		
		
		for(int i = 0; i < planets.length; i++) {
			planets[i].setPlanet(2, planetPositions[i]);
		}
		
		
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(152, 88);
	}
}
