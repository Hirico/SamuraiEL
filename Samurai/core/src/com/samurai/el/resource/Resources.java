package com.samurai.el.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Resources {
	
	public Texture block0 = new Texture(Gdx.files.internal("img/field/block0.jpg"));
	public Texture block1 = new Texture(Gdx.files.internal("img/field/block1.jpg"));
	public Texture block2 = new Texture(Gdx.files.internal("img/field/block2.jpg"));
	public Texture block3 = new Texture(Gdx.files.internal("img/field/block3.jpg"));
	public Texture block4 = new Texture(Gdx.files.internal("img/field/block4.jpg"));
	public Texture block5 = new Texture(Gdx.files.internal("img/field/block5.jpg"));
	public Texture block6 = new Texture(Gdx.files.internal("img/field/block6.jpg")); 
	public Texture block7 = new Texture(Gdx.files.internal("img/field/block7.jpg"));
	public Sprite stand0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0.jpg")));
	public Sprite stand1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1.jpg")));
	public Sprite stand2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2.jpg")));
	public Sprite stand3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3.jpg")));
	public Sprite move0 = new Sprite(new Texture(Gdx.files.internal("img/character/move0.jpg")));
	public Sprite move1 = new Sprite(new Texture(Gdx.files.internal("img/character/move1.jpg")));
	public Sprite move2 = new Sprite(new Texture(Gdx.files.internal("img/character/move2.jpg")));
	public Sprite move3 = new Sprite(new Texture(Gdx.files.internal("img/character/move3.jpg")));
	public Music bgm04 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm04.mp3"));
	public Music bgm05 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm04.mp3"));
	
	public static Resources instance;
	
	public static Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}
		return instance;
	}
	
	private Resources() {
		reInit();
	}
	
	public void reInit() {
		dispose();
		
		block0 = new Texture(Gdx.files.internal("img/field/block0.jpg"));
		block1 = new Texture(Gdx.files.internal("img/field/block1.jpg"));
		block2 = new Texture(Gdx.files.internal("img/field/block2.jpg"));
		block3 = new Texture(Gdx.files.internal("img/field/block3.jpg"));
		block4 = new Texture(Gdx.files.internal("img/field/block4.jpg"));
		block5 = new Texture(Gdx.files.internal("img/field/block5.jpg"));
		block6 = new Texture(Gdx.files.internal("img/field/block6.jpg")); 
		block7 = new Texture(Gdx.files.internal("img/field/block7.jpg"));
		stand0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0.jpg")));
		stand1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1.jpg")));
		stand2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2.jpg")));
		stand3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3.jpg")));
		move0 = new Sprite(new Texture(Gdx.files.internal("img/character/move0.jpg")));
		move1 = new Sprite(new Texture(Gdx.files.internal("img/character/move1.jpg")));
		move2 = new Sprite(new Texture(Gdx.files.internal("img/character/move2.jpg")));
		move3 = new Sprite(new Texture(Gdx.files.internal("img/character/move3.jpg")));
		bgm04 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm04.mp3"));
		bgm05 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm04.mp3"));
	}
	
	public void dispose() {
		block1.dispose();
		block2.dispose();
		block3.dispose();
		block4.dispose();
		block5.dispose();
		block6.dispose();
		block7.dispose();
		bgm04.dispose();
		bgm05.dispose();
	}
}
