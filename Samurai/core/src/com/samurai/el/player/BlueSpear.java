package com.samurai.el.player;

import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;

public class BlueSpear extends Player {
	public BlueSpear() {
		super();
		side = 1;
	}
	
	@Override
	public void occupy() {
		Field field = GameInstance.getInstance().field;
		field.executeOccupation(3, position, direction);
		super.occupy();
	}
}
