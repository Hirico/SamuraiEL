package com.samurai.el.field;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;

public class Field4 extends Field {
	
	/**Conquer mode map */
	public Field4(int mode) { 
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background4();
		size = new Vector2(29, 23);
		blockSize = 48;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 29; i++) {
			for(int j = 0; j <= 23; j++) {
				blocks[i][j] = new Block(blockSize);
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(0,2), new Vector2(0,5), new Vector2(0,8), new Vector2(29,2), 
				new Vector2(29,5), new Vector2(29,8)
		};
		
		planetPositions = new Vector2[] {
				new Vector2(2,7), new Vector2(2,15), new Vector2(9,16), new Vector2(12,13),
				new Vector2(27,7), new Vector2(27,15), new Vector2(20,6), new Vector2(16,9)
		};
		planets = new Block[] {
				blocks[2][7], blocks[2][15], blocks[9][16], blocks[12][13], blocks[27][7],
				blocks[27][15], blocks[20][6], blocks[16][9]
		};
			
			
		for(int i = 0; i < planets.length; i++) {
			planets[i].setPlanet(2, planetPositions[i]);
		}
			
		if(mode == 2) {
			for(int i = 0; i < planets.length ; i++) {
				planets[i].setConquerPoint();
			}
			homePositions = new Vector2[] {
					new Vector2(0,2), new Vector2(0,5), new Vector2(0,8), new Vector2(0,11), 
					new Vector2(0,14), new Vector2(0,17), new Vector2(0,20), new Vector2(0,23),
					new Vector2(29,2), new Vector2(29,5), new Vector2(29,8), new Vector2(29,11),
					new Vector2(29,14), new Vector2(29,17), new Vector2(29,20), new Vector2(29,23)
			};
			homeEffects = new ParticleEffect[][] {
				{new ParticleEffect(), new ParticleEffect()}, {new ParticleEffect(), new ParticleEffect()},
				{new ParticleEffect(), new ParticleEffect()}, {new ParticleEffect(), new ParticleEffect()},
				{new ParticleEffect(), new ParticleEffect()}, {new ParticleEffect(), new ParticleEffect()},
				{new ParticleEffect(), new ParticleEffect()}, {new ParticleEffect(), new ParticleEffect()},
				{new ParticleEffect(), new ParticleEffect()}, {new ParticleEffect(), new ParticleEffect()},
				{new ParticleEffect(), new ParticleEffect()}, {new ParticleEffect(), new ParticleEffect()},
				{new ParticleEffect(), new ParticleEffect()}, {new ParticleEffect(), new ParticleEffect()},
				{new ParticleEffect(), new ParticleEffect()}, {new ParticleEffect(), new ParticleEffect()}
		};
		}
		
		
	}
	
	public Field4() {
		//this is initialized before player, so player reference is not accessible here
		this(2);		
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {		
		return new Vector2(0, 0);
	}
}
