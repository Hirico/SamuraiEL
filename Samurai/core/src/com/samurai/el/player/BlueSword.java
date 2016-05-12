package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.resource.Resources;

public class BlueSword extends Player {
	public BlueSword(Vector2 homePosition) {
		super(homePosition);
		side = 1;
		id = 4;
		specBlockTexture = Resources.getInstance().block4;
		attackSound = Resources.getInstance().attack1;
	}
	
}
