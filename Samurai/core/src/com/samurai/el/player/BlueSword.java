package com.samurai.el.player;

import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;

public class BlueSword extends Player {
	public BlueSword() {
		super();
		side = 1;
	}
	
	@Override
	public void occupy() {
		Field field = GameInstance.getInstance().field;
		field.executeOccupation(4, position, direction);
		super.occupy();
	}
}
