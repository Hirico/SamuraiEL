package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.resource.Resources;

public class BlueAxe extends Player {
	public BlueAxe(Vector2 homePosition) {
		super(homePosition);
		side = 1;
		id = 5;
		specBlockTexture = Resources.getInstance().block5;
	}
	
}
