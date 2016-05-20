package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class FieldGuide extends Field{
	public FieldGuide() {
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background0();
		size = new Vector2(4, 4);
		blockSize = 48;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 4; i++) {
			for(int j = 0; j <= 4; j++) {
				blocks[i][j] = new Block(blockSize);
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(0,0), new Vector2(0,0), new Vector2(0,0), new Vector2(4,4),
				 new Vector2(4,4),  new Vector2(4,4)
		};
		
		
		planetPositions = new Vector2[] {
				new Vector2(2,2)
		};
		planets = new Block[] {
				blocks[2][2]
		};
			
			
		for(int i = 0; i < planets.length; i++) {
			planets[i].setPlanet(2, planetPositions[i]);
		}
		
		
		
	}
	
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(520, 240);
	}
}
