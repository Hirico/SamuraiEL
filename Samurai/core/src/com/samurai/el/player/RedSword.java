package com.samurai.el.player;

import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;

public class RedSword extends Player {
	public RedSword() {
		super();
		side = 0;
	}
	
	@Override
	public void occupy() {
		Field field = GameInstance.getInstance().field;
		field.executeOccupation(1, position, direction);
		super.occupy();
		
	}
}
