package com.samurai.el.ai;

import com.badlogic.gdx.utils.Array;
import com.samurai.el.field.Block;
import com.samurai.el.player.Player;

public class Targeting {

	/**return a nearest enemy/neutral planet, null if all planets are friend-side */
	public static Block getNearestPlanet(Player p, Block[] planets) {
				
		Array<Block> targetPlanets = new Array<Block>();
		for(int i = 0; i < planets.length; i++) {
			if(planets[i].side != p.side) {
				targetPlanets.add(planets[i]);
			}
		}
		
		
		if(targetPlanets.size == 0) {
			return null;
		} else {
			int[] distances = new int[targetPlanets.size];
			int minId = 0;
			
			for(int i = 0; i < targetPlanets.size; i++) {
				distances[i] = (int) (Math.pow((targetPlanets.get(i).planetPosition.x-p.position.x), 2) +Math.pow((targetPlanets.get(i).planetPosition.y-p.position.y), 2));
				if(i > 0 && distances[i] < distances[i-1]) {
					minId = i;
				}			
			}
			return targetPlanets.get(minId);
			
		}			
	}
	
	public static Player getNearestEnemy(Player p) {
		Array<Player> enemies = p.getEnemies();
		int[] distances = new int[enemies.size];
		int minId = 0;
		
		for(int i = 0; i < enemies.size; i++) {
			distances[i] = (int) (Math.pow((enemies.get(i).position.x-p.position.x), 2) +Math.pow((enemies.get(i).position.y-p.position.y), 2));
			if(i > 0 && distances[i] < distances[i-1]) {
				minId = i;
			}			
		}
		return enemies.get(minId);
				
	}
}
