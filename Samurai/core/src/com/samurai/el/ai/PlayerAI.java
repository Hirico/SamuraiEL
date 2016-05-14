package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public abstract class PlayerAI {
	
	public double moveCooldown;
	public double totalMoveCooldown;
	public Player player;
	public Player target;
	public Vector2 wanderTarget;
	public GameInstance gameInstance;
	public int stuckState;
	public Array<Player> enemies;
	
	public PlayerAI(Player player) {
		this.player = player;		
		gameInstance = GameInstance.getInstance();
		wanderTarget = new Vector2();
		stuckState = 0;
		moveCooldown = 0.2f;
		totalMoveCooldown = 0.2f;

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
			boolean wanderFlag = true;
			for(Player p: enemies) {
				if(!p.isRecovering) {
					wanderFlag = false;
					break;
				}
			}
			if(wanderFlag) {
				wander();
			} else {
				pursue();
			}
			
		//occupy
			if(player.occupiable() && player.fireSafe()) {
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
		
		//find target
		if(target == null) {
			getRandomTarget();
		}
		if(target.isRecovering || target.isHidden) {
			changeTarget();
		}
		
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
			player.move(2);
		}
		else if(target.position.x > player.position.x) {
			player.move(3);
		}
		else if(target.position.y < player.position.y) {
			player.move(1);
		}
		else if(target.position.y > player.position.y) {
			player.move(0);
		}
	}
	
	/** vertical first*/
	public void pursueMove2() {		
		if(target.position.y < player.position.y) {
			player.move(1);
		}
		else if(target.position.y > player.position.y) {
			player.move(0);
		}
		else if(target.position.x < player.position.x) {
			player.move(2);
		}
		else if(target.position.x > player.position.x) {
			player.move(3);
		}
	}
	
	/**move to a random position, called when all enemies are recovering */
	public void wander() {
		wanderTarget.set((int)(Math.random()*gameInstance.field.size.x), (int)(Math.random()*gameInstance.field.size.y));
		
		if(moveCooldown >= 0) {
			moveCooldown -= Gdx.graphics.getDeltaTime();
		} else {
			if(player.isStuck) {
				resolveStuck();
			} else {
				if(Math.random() < 0.5f) {
					wanderMove1();
				} else {
					wanderMove2();
				}				
			}
			
			moveCooldown = totalMoveCooldown;
		}
	}
	
	/** horizontal first*/
	public void wanderMove1() {
		if(wanderTarget.x < player.position.x) {
			player.move(2);
		}
		else if(wanderTarget.x > player.position.x) {
			player.move(3);
		}
		else if(wanderTarget.y < player.position.y) {
			player.move(1);
		}
		else if(wanderTarget.y > player.position.y) {
			player.move(0);
		}
	}
	
	/** vertical first*/
	public void wanderMove2() {		
		if(wanderTarget.y < player.position.y) {
			player.move(1);
		}
		else if(wanderTarget.y > player.position.y) {
			player.move(0);
		}
		else if(wanderTarget.x < player.position.x) {
			player.move(2);
		}
		else if(wanderTarget.x > player.position.x) {
			player.move(3);
		}
	}
	
	public void resolveStuck() {
		if(stuckState == 0) {
			player.move((player.direction+2)%4);
			stuckState = 1;
		}
		else if(stuckState == 1) {
			player.move((player.direction+2)%4);
			stuckState = 0;
			player.isStuck = false;
		}
	}
}
