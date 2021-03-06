package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.player.Player;

public class PlanetEasyAI extends PlanetAI{
	public float occupyDelay;

	public PlanetEasyAI(Player player) {
		super(player);
		occupyDelay = 0.5f;
		
	}
	
	// only chase linearly
	@Override
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
				pursueMove1();
				moveCooldown = totalMoveCooldown;
			}
		}
	}
	
	@Override
	/**move to a planet position linearly*/
	public void occupyPlanet() {
		
		if(moveCooldown >= 0) {
			moveCooldown -= Gdx.graphics.getDeltaTime();
		} else {
			if(player.isStuck) {
				resolveStuck();
			} else {
				occupyMove1();			
			}
			
			moveCooldown = totalMoveCooldown;
		}
	}
	

}
