package com.samurai.el.field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.samurai.el.field.Background;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public abstract class Field implements Disposable{
	public Background background;
	public SpriteBatch fieldBatch;
	public Vector2 size;
	public Block[][] blocks;
	public int blockSize;
	public Vector2[] homePositions;
	public Vector2[] planetPositions;
	public Block[] planets;
	public ParticleEffect[] homeEffects;
	
	public Field() {
		//this is initialized before player, so player reference is not accessible here
		fieldBatch = new SpriteBatch();
		
		//due to a problem of particle, must create a maximum array even if useless
		homeEffects = new ParticleEffect[] {
				new ParticleEffect(), new ParticleEffect(), new ParticleEffect(),
				new ParticleEffect(), new ParticleEffect(), new ParticleEffect()
		};
		
	}
	
	
	public void render() {
		background.render();
		
		fieldBatch.begin();
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks[0].length; j++) {
				blocks[i][j].draw(fieldBatch);
			}
		}
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
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks[0].length; j++) {
				blocks[i][j].dispose();
			}
		}
		for(int i = 0; i < homeEffects.length; i++) {
			homeEffects[i].dispose();
		}
		fieldBatch.dispose();
	}
	
	public boolean isOwnSide(Player samurai, Vector2 blockPosition) {
		if(blocks[(int)blockPosition.x][(int)blockPosition.y].side == samurai.side) {
			return true;
		} else {
			return false;
		}
			
	}
	
	public boolean isPlanet(Vector2 targetPosition) {
		if(planetPositions != null) {
			for(Vector2 p: planetPositions) {
				if(p.equals(targetPosition)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void closeVision(Vector2 position , Player player) {
		Vector2 targetPosition = new Vector2();
		for(int i = 1; i <= 6; i++) {
			for(int j = 1; j <= (2*i-1); j++) {
				targetPosition.set(position.x+i-6, position.y+j-i);
				if(targetPosition.x >= 0 && targetPosition.x <= size.x
						&& targetPosition.y >= 0 && targetPosition.y <= size.y) {
					blocks[(int)targetPosition.x][(int)targetPosition.y].hide();
				}
			}
		}
		
		for(int i = 7; i <= 11; i++) {
			for(int j = (2*i-11); j <= 11; j++) {
				targetPosition.set(position.x+i-6, position.y+j-i);
				if(targetPosition.x >= 0 && targetPosition.x <= size.x
						&& targetPosition.y >= 0 && targetPosition.y <= size.y) {
					blocks[(int)targetPosition.x][(int)targetPosition.y].hide();
				}
			}
		}
		
	}
	
	public void openVision(Vector2 position) {
		Vector2 targetPosition = new Vector2();
		for(int i = 1; i <= 6; i++) {
			for(int j = 1; j <= (2*i-1); j++) {
				targetPosition.set(position.x+i-6, position.y+j-i);
				if(targetPosition.x >= 0 && targetPosition.x <= size.x
						&& targetPosition.y >= 0 && targetPosition.y <= size.y) {
					blocks[(int)targetPosition.x][(int)targetPosition.y].show();
				}
			}
		}
		
		for(int i = 7; i <= 11; i++) {
			for(int j = (2*i-11); j <= 11; j++) {
				targetPosition.set(position.x+i-6, position.y+j-i);
				if(targetPosition.x >= 0 && targetPosition.x <= size.x
						&& targetPosition.y >= 0 && targetPosition.y <= size.y) {
					blocks[(int)targetPosition.x][(int)targetPosition.y].show();
				}
			}
		}
		
	}
	
	/**return an enemy inside vision, null if no enemy in vision. Use for AI*/
	public Player checkVision(Player player, Vector2 position) {
		Vector2 targetPosition = new Vector2();
		
		for(int i = 1; i <= 6; i++) {
			for(int j = 1; j <= (2*i-1); j++) {
				targetPosition.set(position.x+i-6, position.y+j-i);
				if(targetPosition.x >= 0 && targetPosition.x <= size.x
						&& targetPosition.y >= 0 && targetPosition.y <= size.y) {
					Player checkedPlayer = blocks[(int)targetPosition.x][(int)targetPosition.y].playerOn;
					if(checkedPlayer != null) {
						if(!checkedPlayer.isRecovering && checkedPlayer.side != player.side) {
							return checkedPlayer; 
						}
					}
				}
			}
		}
		
		for(int i = 7; i <= 11; i++) {
			for(int j = (2*i-11); j <= 11; j++) {
				targetPosition.set(position.x+i-6, position.y+j-i);
				if(targetPosition.x >= 0 && targetPosition.x <= size.x
						&& targetPosition.y >= 0 && targetPosition.y <= size.y) {
					Player checkedPlayer = blocks[(int)targetPosition.x][(int)targetPosition.y].playerOn;
					if(checkedPlayer != null) {
						if(!checkedPlayer.isRecovering && checkedPlayer.side != player.side) {
							return checkedPlayer; 
						}
					}
				}
			}
		}
		return null;
	}
	
	public void homeInitialize(int i) {
		blocks[(int) homePositions[i].x][(int) homePositions[i].y].occupy(i);
		blocks[(int) homePositions[i].x][(int) homePositions[i].y].playerArrive(i);
		blocks[(int) homePositions[i].x][(int) homePositions[i].y].isHome = true;
		if(i <= 2) {
			homeEffects[i].load(Gdx.files.internal("img/field/red.p"), Gdx.files.internal("img/field"));
		} else {
			homeEffects[i].load(Gdx.files.internal("img/field/blue.p"), Gdx.files.internal("img/field"));
		}
		blocks[(int) homePositions[i].x][(int) homePositions[i].y].effectInitialize(homeEffects[i]);
	}

	public void executeOccupation(Player player, Vector2 position, int direction) {
		int weapon = player.id % 3;
		int[][] ox = {
			    {0, 0, 0, 0},
			    {0, 0, -1, -1, -2},
			    {-1,-1,-1,0, 1, 1, 1}
			};
		int[][] oy = {
			    {1, 2, 3, 4},
			    {1, 2, 0, 1, 0},
			    {-1,0 ,1, 1, 1, 0,-1}
			};
		int size = 0;
		
		switch(weapon) {
		case 0 :
			size = 4;
			break;
		case 1 :
			size = 5;
			break;
		case 2 :
			size = 7;
			break;
		}
		
		switch(direction) {
		case 0:
			break;
		case 1:
			for(int i = 0; i <= 2; i++) {
				for(int j = 0; j < ox[i].length; j++) {
					oy[i][j] = - oy[i][j];
					ox[i][j] = - ox[i][j];
				}
			}
			break;
		case 2:
			for(int i = 0; i <= 2; i++) {
				for(int j = 0; j < ox[i].length; j++) {
					int c = ox[i][j];
					ox[i][j] = - oy[i][j];
					oy[i][j] = c;
				}
			}
			break;
		case 3:
			for(int i = 0; i <= 2; i++) {
				for(int j = 0; j < ox[i].length; j++) {
					int c = ox[i][j];
					ox[i][j] = oy[i][j];
					oy[i][j] = -c;
				}
			}
			break;
		}
		
			for(int i = 0; i < size; i++) {
				Vector2 targetPosition = new Vector2();
				targetPosition.set (position.x+ox[weapon][i], position.y+oy[weapon][i]);
				
				
				if(targetPosition.x <= blocks.length-1 && targetPosition.x >= 0 
						&&targetPosition.y <= blocks[0].length-1 && targetPosition.y >= 0) {
					
					//detect enemy
					Array<Player> players = GameInstance.getInstance().players;
					for(Player p: players) {
						if(p.position.equals(targetPosition) && !p.isRecovering) {
							p.attacked();
							player.getKillBonus();
						}
					}
					
					//manipulate block
					Block targetBlock = blocks[(int) targetPosition.x][(int) targetPosition.y];
					if(targetBlock.owner != player) {
						if(targetBlock.isHome == false) {
							if(targetBlock.owner != null) {						
								targetBlock.owner.loseABlock();							
							}
							targetBlock.occupy(player.id);
							player.getABlock();
						}
					}										
				}
			}
			
		
		
	}
	
	
	public boolean fireSafeCheck(Player player, Vector2 position, int direction) {
		int weapon = player.id % 3;
		int[][] ox = {
			    {0, 0, 0, 0},
			    {0, 0, -1, -1, -2},
			    {-1,-1,-1,0, 1, 1, 1}
			};
		int[][] oy = {
			    {1, 2, 3, 4},
			    {1, 2, 0, 1, 0},
			    {-1,0 ,1, 1, 1, 0,-1}
			};
		int size = 0;
		
		switch(weapon) {
		case 0 :
			size = 4;
			break;
		case 1 :
			size = 5;
			break;
		case 2 :
			size = 7;
			break;
		}
		
		switch(direction) {
		case 0:
			break;
		case 1:
			for(int i = 0; i <= 2; i++) {
				for(int j = 0; j < ox[i].length; j++) {
					oy[i][j] = - oy[i][j];
					ox[i][j] = - ox[i][j];
				}
			}
			break;
		case 2:
			for(int i = 0; i <= 2; i++) {
				for(int j = 0; j < ox[i].length; j++) {
					int c = ox[i][j];
					ox[i][j] = - oy[i][j];
					oy[i][j] = c;
				}
			}
			break;
		case 3:
			for(int i = 0; i <= 2; i++) {
				for(int j = 0; j < ox[i].length; j++) {
					int c = ox[i][j];
					ox[i][j] = oy[i][j];
					oy[i][j] = -c;
				}
			}
			break;
		}
		
			boolean safe = true;
			for(int i = 0; i < size; i++) {
				Vector2 targetPosition = new Vector2();
				targetPosition.set (position.x+ox[weapon][i], position.y+oy[weapon][i]);								
				if(targetPosition.x <= blocks.length-1 && targetPosition.x >= 0 
						&&targetPosition.y <= blocks[0].length-1 && targetPosition.y >= 0) {
					
					//detect friend and enemy (fire first)
					Array<Player> players = GameInstance.getInstance().players;
					for(Player p: players) {
						if(p.position.equals(targetPosition)) {
							if(p.side != player.side) {
								return true;
							} else {
								safe = false;
							}
						}				
					}
														
				}
			}
			return safe;
			
		
		
	}

	public Block getSpefBlock(Vector2 position) {
		return blocks[(int)position.x][(int)position.y];
	}

	public boolean isOthersHome(Player player, Vector2 targetPosition) {
		Array<Player> players = GameInstance.getInstance().players;
		for(Player p: players) {
			if((player != p) && targetPosition.equals(p.homePosition)) {
				return true;
			}
		}
		return false;
	}
	
}