package com.samurai.el.ai;

import com.samurai.el.player.Player;

public class HardAI extends PlayerAI {

	public HardAI(Player player) {
		super(player);
		totalMoveCooldown = 0.1f;
		
	}
	
}