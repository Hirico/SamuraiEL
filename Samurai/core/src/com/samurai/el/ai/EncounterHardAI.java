package com.samurai.el.ai;

import com.samurai.el.player.Player;

public class EncounterHardAI extends EncounterAI {

	public EncounterHardAI(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		initialTotalMoveCooldown = 0.1f;
	}

}
