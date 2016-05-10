package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.resource.Resources;

public class BlueSpear extends Player {
	public BlueSpear(Vector2 homePosition) {
		super(homePosition);
		side = 1;
		id = 3;
		specBlockTexture = Resources.getInstance().block3;
	}
	
}
