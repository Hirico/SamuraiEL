package com.samurai.el.player;

import com.badlogic.gdx.math.Vector2;
import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;

public class RedAxe extends Player {
	public RedAxe(Vector2 homePosition) {
		super(homePosition);
		side = 0;
		id = 2;
	}
	
}
