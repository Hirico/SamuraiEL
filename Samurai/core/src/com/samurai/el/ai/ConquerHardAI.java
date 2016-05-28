package com.samurai.el.ai;

import com.samurai.el.player.Player;

public class ConquerHardAI extends ConquerAI {

	public ConquerHardAI(Player player) {
		super(player);
		initialTotalMoveCooldown = 0.1f;
	}

}
