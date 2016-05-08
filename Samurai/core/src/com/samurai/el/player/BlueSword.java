package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BlueSword extends Player {
	public BlueSword(Vector2 homePosition) {
		super(homePosition);
		side = 1;
		id = 4;
		specBlockTexture = new Texture(Gdx.files.internal("hirico_temp/block4.jpg"));
	}
	
}
