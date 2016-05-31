package com.samurai.el.ai;

import com.samurai.el.player.Player;

public class EncounterNormalAI extends EncounterAI {

	public EncounterNormalAI(Player player) {
		super(player);
		occupyDelay = 0.3f;
	}

}
