package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.resource.Resources;

public class RedSpear extends Player {
	public RedSpear(Vector2 homePosition) {
		super(homePosition);
		side = 0;
		id = 0;
		specBlockTexture = Resources.getInstance().block0;
	}
	
}
