package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class RedAxe extends Player {
	public RedAxe(Vector2 homePosition) {
		super(homePosition);
		side = 0;
		id = 2;
		specBlockTexture = new Texture(Gdx.files.internal("hirico_temp/block2.jpg"));
	}
	
}
