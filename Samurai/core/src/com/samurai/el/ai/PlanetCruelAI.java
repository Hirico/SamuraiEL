package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.field.Block;
import com.samurai.el.player.Player;

public class PlanetCruelAI extends PlanetAI {

	public PlanetCruelAI(Player player) {
		super(player);
		initialTotalMoveCooldown = 0.1f;
		totalMoveCooldown = initialTotalMoveCooldown;
	}
	
	/**hide when possible */
	@Override
	public void update() {
		if(!player.isRecovering) {	
			
			//hide in self territory
			if(player.side == gameInstance.field.getSpefBlock(player.position).side) {
				if(!player.isHidden) {
					player.hide();
				}
				if(player.isHidden && player.occupiableForAI()) {
					player.show();
					player.occupy();
				}
			}
			
			//move
			target = gameInstance.field.checkVision(player, player.position);
			if(target == null) {
				Block targetPlanet = Targeting.getNearestPlanet(player, planets);
				
				if(targetPlanet != null) {
					targetPlanetPosition.set(targetPlanet.planetPosition);
					occupyPlanet();
				} else {
					for(Player p: allies) {
						if(!p.isHuman) {
							if(p.ai.target != null) {
								target = p.ai.target;
								break;
							}
						} else {
							target = gameInstance.field.checkVision(p, p.position);
							if(target != null) {
								break;
							}
						}
					}
					if(target != null) {
						pursue();
					} else {
						wander();
					}
				}
			} else {
				pursue();
			}
			
			//occupy
			if(player.occupiableForAI()) {
				occupyDelay -= Gdx.graphics.getDeltaTime();
				if(occupyDelay <= 0) {
					occupyDelay = 0;
					player.occupy();
				}
			}
		}
				
	}
	@Override
	/** horizontal first*/
	public void pursueMove1() {
		switch(target.direction) {
		case 0:
			if(target.position.x < player.position.x) {
				player.moveForAI(2);
			}
			else if(target.position.x > player.position.x) {
				player.moveForAI(3);
			}
			else if(target.position.y-1 < player.position.y) {
				player.moveForAI(1);
			}
			else if(target.position.y-1 > player.position.y) {
				player.moveForAI(0);
			} 
			else {
				player.moveForAI(0);
			}
			break;
		case 1:
			if(target.position.x < player.position.x) {
				player.moveForAI(2);
			}
			else if(target.position.x > player.position.x) {
				player.moveForAI(3);
			}
			else if(target.position.y+1 < player.position.y) {
				player.moveForAI(1);
			}
			else if(target.position.y+1 > player.position.y) {
				player.moveForAI(0);
			}
			else {
				player.moveForAI(1);
			}
			break;
		case 2:
			if(target.position.x+1 < player.position.x) {
				player.moveForAI(2);
			}
			else if(target.position.x+1 > player.position.x) {
				player.moveForAI(3);
			}
			else if(target.position.y < player.position.y) {
				player.moveForAI(1);
			}
			else if(target.position.y > player.position.y) {
				player.moveForAI(0);
			}
			else {
				player.moveForAI(2);
			}
			break;
		case 3:
			if(target.position.x-1 < player.position.x) {
				player.moveForAI(2);
			}
			else if(target.position.x-1 > player.position.x) {
				player.moveForAI(3);
			}
			else if(target.position.y < player.position.y) {
				player.moveForAI(1);
			}
			else if(target.position.y > player.position.y) {
				player.moveForAI(0);
			}
			else {
				player.moveForAI(3);
			}
			break;
		}
	}
	
	@Override
	/** vertical first*/
	public void pursueMove2() {	
		switch(target.direction) {
		case 0:
			if(target.position.y-1 < player.position.y) {
				player.moveForAI(1);
			}
			else if(target.position.y-1 > player.position.y) {
				player.moveForAI(0);
			}
			else if(target.position.x < player.position.x) {
				player.moveForAI(2);
			}
			else if(target.position.x > player.position.x) {
				player.moveForAI(3);
			}
			else {
				player.moveForAI(0);
			}
			break;
		case 1:
			if(target.position.y+1 < player.position.y) {
				player.moveForAI(1);
			}
			else if(target.position.y+1 > player.position.y) {
				player.moveForAI(0);
			}
			else if(target.position.x < player.position.x) {
				player.moveForAI(2);
			}
			else if(target.position.x > player.position.x) {
				player.moveForAI(3);
			}
			else {
				player.moveForAI(1);
			}
			break;
		case 2:
			if(target.position.y < player.position.y) {
				player.moveForAI(1);
			}
			else if(target.position.y > player.position.y) {
				player.moveForAI(0);
			}
			else if(target.position.x+1 < player.position.x) {
				player.moveForAI(2);
			}
			else if(target.position.x+1 > player.position.x) {
				player.moveForAI(3);
			}
			else {
				player.moveForAI(2);
			}
			break;
		case 3:
			if(target.position.y < player.position.y) {
				player.moveForAI(1);
			}
			else if(target.position.y > player.position.y) {
				player.moveForAI(0);
			}
			else if(target.position.x-1 < player.position.x) {
				player.moveForAI(2);
			}
			else if(target.position.x-1 > player.position.x) {
				player.moveForAI(3);
			}
			else {
				player.moveForAI(3);
			}
			break;
		}
	}

}
