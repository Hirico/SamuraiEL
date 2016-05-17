package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.field.Block;
import com.samurai.el.player.Player;

public class PlanetAI extends PlayerAI {

	public PlanetAI(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
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

}
