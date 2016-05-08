package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BlueSpear extends Player {
	public BlueSpear(Vector2 homePosition) {
		super(homePosition);
		side = 1;
		id = 3;
		specBlockTexture = new Texture(Gdx.files.internal("hirico_temp/block3.jpg"));
	}
	
}
