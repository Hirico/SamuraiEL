package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class RedSword extends Player {
	public RedSword(Vector2 homePosition) {
		super(homePosition);
		side = 0;
		id = 1;
		specBlockTexture = new Texture(Gdx.files.internal("hirico_temp/block1.jpg"));
	}
	
}
