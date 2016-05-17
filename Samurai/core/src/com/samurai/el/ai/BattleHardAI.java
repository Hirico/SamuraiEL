package com.samurai.el.ai;

import com.samurai.el.player.Player;

public class BattleHardAI extends BattleAI {

	public BattleHardAI(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		totalMoveCooldown = 0.1f;
	}

}
