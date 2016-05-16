package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.samurai.el.field.Block;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public abstract class PlayerAI {
	
	public double moveCooldown;
	public double totalMoveCooldown;
	public Player player;
	public Player target;
	public GameInstance gameInstance;
	public int stuckState;
	public Array<Player> enemies;
	public Block[] planets;
	public Vector2 targetPlanetPosition;
	
	public PlayerAI(Player player) {
		this.player = player;		
		gameInstance = GameInstance.getInstance();
		stuckState = 0;
		moveCooldown = 0.2f;
		totalMoveCooldown = 0.2f;
		planets = gameInstance.field.planets;
		targetPlanetPosition = new Vector2();

	}
	
	public void initializeEnemy() {
		enemies = new Array<Player>();
		for(Player p: gameInstance.players) {
			if(p.side != player.side) {
				enemies.add(p);
			}	
		}
	}
	
	public void update() {

		if(!player.isRecovering) {	
		//move
			Block targetPlanet = Targeting.getNearestPlanet(player, planets);
			
			if(targetPlanet != null) {
				targetPlanetPosition.set(targetPlanet.planetPosition);
				occupyPlanet();
			} else {
				target = gameInstance.field.checkVision(player, player.position);
				if(target != null) {
					pursue();
				}
			}
			
		//occupy
			if(player.occupiableForAI()) {
				player.occupy();
			}
		}
				
	}
	

	public void getRandomTarget() {
		int targetId = (int)(Math.random()*(enemies.size-1));
		target = enemies.get(targetId);				
	}
	
	public void changeTarget() {	
		target = enemies.get((target.id+1)%enemies.size);
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
	
	/**move to a planet position*/
	public void occupyPlanet() {
		
		if(moveCooldown >= 0) {
			moveCooldown -= Gdx.graphics.getDeltaTime();
		} else {
			if(player.isStuck) {
				resolveStuck();
			} else {
				if(Math.random() < 0.5f) {
					occupyMove1();
				} else {
					occupyMove2();
				}				
			}
			
			moveCooldown = totalMoveCooldown;
		}
	}
	
	/** horizontal first*/
	public void occupyMove1() {
		if(targetPlanetPosition.x < player.position.x) {
			player.moveForAI(2);
		}
		else if(targetPlanetPosition.x > player.position.x) {
			player.moveForAI(3);
		}
		else if(targetPlanetPosition.y < player.position.y) {
			player.moveForAI(1);
		}
		else if(targetPlanetPosition.y > player.position.y) {
			player.moveForAI(0);
		}
	}
	
	/** vertical first*/
	public void occupyMove2() {		
		if(targetPlanetPosition.y < player.position.y) {
			player.moveForAI(1);
		}
		else if(targetPlanetPosition.y > player.position.y) {
			player.moveForAI(0);
		}
		else if(targetPlanetPosition.x < player.position.x) {
			player.moveForAI(2);
		}
		else if(targetPlanetPosition.x > player.position.x) {
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
}
