package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.resource.Resources;

public class RedSword extends Player {
	public RedSword(Vector2 homePosition) {
		super(homePosition);
		side = 0;
		id = 1;
		specBlockTexture = Resources.getInstance().block1;
		attackSound = Resources.getInstance().attack1;
	}
	
}
