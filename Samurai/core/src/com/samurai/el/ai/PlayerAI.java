package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.samurai.el.field.Block;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public abstract class PlayerAI {
	
	public double moveCooldown;
	public double initialTotalMoveCooldown;
	public double totalMoveCooldown;
	public float occupyDelay;
	public Player player;
	public Player target;
	public GameInstance gameInstance;
	public int stuckState;
	public Array<Player> enemies;
	public Array<Player> allies;
	public Block[] planets;
	public Vector2 targetPlanetPosition;
	public Vector2 wanderPosition;
	
	public PlayerAI(Player player) {
		this.player = player;		
		gameInstance = GameInstance.getInstance();
		stuckState = 0;
		occupyDelay = 0f;
		moveCooldown = 0.2f;
		initialTotalMoveCooldown = 0.2f;
		totalMoveCooldown = initialTotalMoveCooldown;
				
		planets = gameInstance.field.planets;
		targetPlanetPosition = new Vector2();
		wanderPosition = new Vector2();
		wanderPosition.set((float)(int)(Math.random()*gameInstance.field.blocks.length), (float)(int)(Math.random()*gameInstance.field.blocks[0].length));

	}
	
	public void initializeEnemy() {
		enemies = new Array<Player>();
		for(Player p: gameInstance.players) {
			if(p.side != player.side) {
				enemies.add(p);
			}	
		}
		initializeAllies();
	}
	
	public void initializeAllies() {
		allies = new Array<Player>();
		for(Player p: gameInstance.players) {
			if(p.side == player.side) {
				allies.add(p);
			}	
		}
	}
	
	public void update() {
				
	}
	

	public void getRandomTarget() {
		int targetId = (int)(Math.random()*(enemies.size-1));
		if(targetId < enemies.size) {
			target = enemies.get(targetId);	
		}
	}
	
	public void changeTarget() {
		int index = enemies.indexOf(target, true);
		if(index + 1 < enemies.size) {
			target = enemies.get(index+1);
		}
		else if(index -1 >=0) {
			target = enemies.get(index-1);
		}
	}
	
	/**pursue move strategy, chase a random enemy */
	public void pursue() {
		
		if(moveCooldown >= 0) {
			moveCooldown -= Gdx.graphics.getDeltaTime();
		} else {
			if(player.isStuck) {
				resolveStuck();
				moveCooldown = totalMoveCooldown;
			} else {
				if(Math.random() < 0.5f) {
					pursueMove1();
				} else {
					pursueMove2();
				}
				moveCooldown = totalMoveCooldown;
			}
		}
	}
	
	/**wander to some random place to occupy and detect enemy */
	public void wander() {
		if(player.isStuck || player.position.equals(wanderPosition)) {
			wanderPosition.set((float)(int)(Math.random()*gameInstance.field.blocks.length), (float)(int)(Math.random()*gameInstance.field.blocks[0].length));
		}
		if(moveCooldown >= 0) {
			moveCooldown -= Gdx.graphics.getDeltaTime();
		} else {
			if(player.isStuck) {
				resolveStuck();
				moveCooldown = totalMoveCooldown;
			} else {
				if(Math.random() < 0.5f) {
					wanderMove1();
				} else {
					wanderMove2();
				}
				moveCooldown = totalMoveCooldown;
			}
		}
	}
	
	/** horizontal first*/
	public void pursueMove1() {
		if(target.position.x < player.position.x) {
			player.moveForAI(2);
		}
		else if(target.position.x > player.position.x) {
			player.moveForAI(3);
		}
		else if(target.position.y < player.position.y) {
			player.moveForAI(1);
		}
		else if(target.position.y > player.position.y) {
			player.moveForAI(0);
		}
	}
	
	/** vertical first*/
	public void pursueMove2() {		
		if(target.position.y < player.position.y) {
			player.moveForAI(1);
		}
		else if(target.position.y > player.position.y) {
			player.moveForAI(0);
		}
		else if(target.position.x < player.position.x) {
			player.moveForAI(2);
		}
		else if(target.position.x > player.position.x) {
			player.moveForAI(3);
		}
	}
	
	/** horizontal first*/
	public void wanderMove1() {
		if(wanderPosition.x < player.position.x) {
			player.moveForAI(2);
		}
		else if(wanderPosition.x > player.position.x) {
			player.moveForAI(3);
		}
		else if(wanderPosition.y < player.position.y) {
			player.moveForAI(1);
		}
		else if(wanderPosition.y > player.position.y) {
			player.moveForAI(0);
		}
	}
	
	/** vertical first*/
	public void wanderMove2() {		
		if(wanderPosition.y < player.position.y) {
			player.moveForAI(1);
		}
		else if(wanderPosition.y > player.position.y) {
			player.moveForAI(0);
		}
		else if(wanderPosition.x < player.position.x) {
			player.moveForAI(2);
		}
		else if(wanderPosition.x > player.position.x) {
			player.moveForAI(3);
		}
	}
	
	
	public void resolveStuck() {
		if(stuckState == 0) {
			player.moveForAI((player.direction+2)%4);
			stuckState = 1;
		}
		else if(stuckState == 1) {
			player.moveForAI((player.direction+2)%4);
			stuckState = 0;
			player.isStuck = false;
		}
	}
	
	public void boundDebuff() {
		
	}
}
