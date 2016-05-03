package com.samurai.el.player;

import com.badlogic.gdx.math.Vector2;
import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;

public class BlueSpear extends Player {
	public BlueSpear(Vector2 homePosition) {
		super(homePosition);
		side = 1;
		id = 3;
	}
	
}
