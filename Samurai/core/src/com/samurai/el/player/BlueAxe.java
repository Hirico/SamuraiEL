package com.samurai.el.player;

import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;

public class BlueAxe extends Player {
	public BlueAxe() {
		super();
		side = 1;
	}
	
	@Override
	public void occupy() {
		Field field = GameInstance.getInstance().field;
		field.executeOccupation(5, position, direction);
		super.occupy();
	}
}
