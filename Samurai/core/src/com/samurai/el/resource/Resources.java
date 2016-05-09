package com.samurai.el.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Resources {
	
	public Texture block6 = new Texture(Gdx.files.internal("hirico_temp/block6.jpg")); // invisible block
	public Texture block7 = new Texture(Gdx.files.internal("hirico_temp/block7.jpg")); // neutral block
	public Sprite stand0 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/stand0.jpg")));
	public Sprite stand1 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/stand1.jpg")));
	public Sprite stand2 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/stand2.jpg")));
	public Sprite stand3 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/stand3.jpg")));
	public Sprite move0 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/move0.jpg")));
	public Sprite move1 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/move1.jpg")));
	public Sprite move2 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/move2.jpg")));
	public Sprite move3 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/move3.jpg")));
	
	public static Resources instance;
	
	public static Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}
		return instance;
	}
	
	public Resources() {
		reInit();
	}
	
	public void reInit() {
		dispose();
		
		block6 = new Texture(Gdx.files.internal("hirico_temp/block6.jpg")); 
		block7 = new Texture(Gdx.files.internal("hirico_temp/block7.jpg"));
		stand0 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/stand0.jpg")));
		stand1 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/stand1.jpg")));
		stand2 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/stand2.jpg")));
		stand3 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/stand3.jpg")));
		move0 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/move0.jpg")));
		move1 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/move1.jpg")));
		move2 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/move2.jpg")));
		move3 = new Sprite(new Texture(Gdx.files.internal("hirico_temp/move3.jpg")));
	}
	
	public void dispose() {
		block6.dispose();
		block7.dispose();
	}
}
