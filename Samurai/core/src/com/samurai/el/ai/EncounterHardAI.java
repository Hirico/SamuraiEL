package com.samurai.el.ai;

import com.samurai.el.player.Player;

public class EncounterHardAI extends EncounterAI {

	public EncounterHardAI(Player player) {
		super(player);
		initialTotalMoveCooldown = 0.1f;
		totalMoveCooldown = initialTotalMoveCooldown;
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
