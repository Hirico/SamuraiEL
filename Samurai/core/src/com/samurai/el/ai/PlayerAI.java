package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public abstract class PlayerAI {
	
	public double moveCooldown = 0.2f;
	public Player player;
	public Player target;
	public GameInstance gameInstance;
	
	public PlayerAI(Player player) {
		this.player = player;
		gameInstance = GameInstance.getInstance();
	}
	
	public void update() {
		
		//find target
		if(target == null) {
			getRandomTarget();
		}


		if(!player.isRecovering) {	
		//move				
			if(moveCooldown >= 0) {
				moveCooldown -= Gdx.graphics.getDeltaTime();
			} else {
				pursueMove();
				moveCooldown = 0.2f;
			}
			
		//occupy
			if(player.occupiable()) {
				player.occupy();
			}
		}
		
		
	}
	
	public void getRandomTarget() {
		if(player.side == 0) {
			int targetId = (int)(Math.random()*2 + 3);
			target = gameInstance.players.get(targetId);
		} else {
			int targetId = (int)(Math.random()*2);
			target = gameInstance.players.get(targetId);
		}
		
		
	}
	
	public void pursueMove() {
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
}
