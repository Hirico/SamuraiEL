package com.samurai.el.ai;

import com.samurai.el.player.Player;

public class EncounterHardAI extends EncounterAI {

	public EncounterHardAI(Player player) {
		super(player);
		initialTotalMoveCooldown = 0.1f;
		totalMoveCooldown = initialTotalMoveCooldown;
	}

}
