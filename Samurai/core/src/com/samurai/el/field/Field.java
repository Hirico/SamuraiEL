package com.samurai.el.field;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.samurai.el.field.Background;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public abstract class Field{
	public Background background;
	public SpriteBatch fieldBatch;
	public Vector2 size;
	public Block[][] blocks;
	public int blockSize;
	
	public Field() {
		fieldBatch = new SpriteBatch();
	}
	
	
	public void render() {
		background.render();
		
		fieldBatch.begin();
		fieldBatch.end();
	}
	
	public Vector2 getBottomLeftCorner() {
		// to be overridden
		return null;
	}
	
	public Vector2 getSize() {
		return size;
	}
	
	public void dispose() {
		background.dispose();
		fieldBatch.dispose();
	}

	public void executeOccupation(int owner, Vector2 position, int direction) {
		int weapon = owner % 3;
		int[][] ox = {
			    {0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 1, 1, 2, 0, 0},
			    {-1,-1,-1,0, 1, 1, 1}
			};
		int[][] oy = {
			    {1, 2, 3, 4, 0, 0, 0},
			    {1, 2, 0, 1, 0, 0, 0},
			    {-1,0 ,1, 1, 1, 0,-1}
			};
		
		switch(direction) {
		case 0:
			break;
		case 1:
			for(int i = 0; i <= 2; i++) {
				for(int j = 0; j <= 6; j++) {
					oy[i][j] = - oy[i][j];
				}
			}
			break;
		case 2:
			for(int i = 0; i <= 2; i++) {
				for(int j = 0; j <= 6; j++) {
					int c = ox[i][j];
					ox[i][j] = - oy[i][j];
					oy[i][j] = c;
				}
			}
			break;
		case 3:
			for(int i = 0; i <= 2; i++) {
				for(int j = 0; j <= 6; j++) {
					int c = ox[i][j];
					ox[i][j] = oy[i][j];
					oy[i][j] = -c;
				}
			}
			break;
		}
		
			for(int i = 0; i <= 6; i++) {
				Vector2 targetPosition = new Vector2();
				targetPosition.x =  (position.x+ox[weapon][i]);
				targetPosition.y =  (position.y+oy[weapon][i]);
				blocks[(int) targetPosition.x][(int) targetPosition.y].owner = owner;
				Array<Player> players = GameInstance.getInstance().players;
				for(Player p: players) {
					if(p.position.equals(targetPosition)) {
						p.attacked();
					}
				}
			}
			
		
		
	}
	
}