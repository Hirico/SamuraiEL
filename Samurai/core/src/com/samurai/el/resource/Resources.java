package com.samurai.el.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Resources {
	
	public Texture block0 = new Texture(Gdx.files.internal("img/field/block0.png"));
	public Texture block1 = new Texture(Gdx.files.internal("img/field/block1.png"));
	public Texture block2 = new Texture(Gdx.files.internal("img/field/block2.png"));
	public Texture block3 = new Texture(Gdx.files.internal("img/field/block3.png"));
	public Texture block4 = new Texture(Gdx.files.internal("img/field/block4.png"));
	public Texture block5 = new Texture(Gdx.files.internal("img/field/block5.png"));
	public Texture block6 = new Texture(Gdx.files.internal("img/field/block6.png")); 
	public Texture block7 = new Texture(Gdx.files.internal("img/field/block7.png"));
	public Sprite stand0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0.png")));
	public Sprite stand1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1.png")));
	public Sprite stand2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2.png")));
	public Sprite stand3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3.png")));
	public Sprite move0_0 = new Sprite(new Texture(Gdx.files.internal("img/character/move0_0.png")));
	public Sprite move1_0 = new Sprite(new Texture(Gdx.files.internal("img/character/move1_0.png")));
	public Sprite move2_0 = new Sprite(new Texture(Gdx.files.internal("img/character/move2_0.png")));
	public Sprite move3_0 = new Sprite(new Texture(Gdx.files.internal("img/character/move3_0.png")));
	public Sprite move0_1 = new Sprite(new Texture(Gdx.files.internal("img/character/move0_1.png")));
	public Sprite move1_1 = new Sprite(new Texture(Gdx.files.internal("img/character/move1_1.png")));
	public Sprite move2_1 = new Sprite(new Texture(Gdx.files.internal("img/character/move2_1.png")));
	public Sprite move3_1 = new Sprite(new Texture(Gdx.files.internal("img/character/move3_1.png")));
	public Sprite background0 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpeg")));
	public Sprite background1 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpeg")));
	public Sprite background2 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpeg")));
	public Sprite background3 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpeg")));
	public Sprite gameload = new Sprite(new Texture(Gdx.files.internal("img/background/gameload.jpg")));
	public Sprite blackFade = new Sprite(new Texture(Gdx.files.internal("img/background/blackfade.png")));
	public Music bgm02 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm02.mp3"));
	public Music bgm03 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm03.mp3"));
	public Music bgm04 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm04.mp3"));
	public Music bgm05 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm05.mp3"));
	public Music bgm06 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm05.mp3"));
	public Music bgm07 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm05.mp3"));
	public Sound attack0 = Gdx.audio.newSound(Gdx.files.internal(("sound/attack0.wav")));
	public Sound attack1 = Gdx.audio.newSound(Gdx.files.internal(("sound/attack1.wav")));
	public Sound attack2 = Gdx.audio.newSound(Gdx.files.internal(("sound/attack2.wav")));
	public Sound hover = Gdx.audio.newSound(Gdx.files.internal(("sound/hoverSound.wav")));
	public Cursor cursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("img/ui/cursor.png")), 0, 0);
	
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
		
		block0 = new Texture(Gdx.files.internal("img/field/block0.png"));
		block1 = new Texture(Gdx.files.internal("img/field/block1.png"));
		block2 = new Texture(Gdx.files.internal("img/field/block2.png"));
		block3 = new Texture(Gdx.files.internal("img/field/block3.png"));
		block4 = new Texture(Gdx.files.internal("img/field/block4.png"));
		block5 = new Texture(Gdx.files.internal("img/field/block5.png"));
		block6 = new Texture(Gdx.files.internal("img/field/block6.png")); 
		block7 = new Texture(Gdx.files.internal("img/field/block7.png"));
		stand0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0.png")));
		stand1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1.png")));
		stand2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2.png")));
		stand3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3.png")));
		move0_0 = new Sprite(new Texture(Gdx.files.internal("img/character/move0_0.png")));
		move1_0 = new Sprite(new Texture(Gdx.files.internal("img/character/move1_0.png")));
		move2_0 = new Sprite(new Texture(Gdx.files.internal("img/character/move2_0.png")));
		move3_0 = new Sprite(new Texture(Gdx.files.internal("img/character/move3_0.png")));
		move0_1 = new Sprite(new Texture(Gdx.files.internal("img/character/move0_1.png")));
		move1_1 = new Sprite(new Texture(Gdx.files.internal("img/character/move1_1.png")));
		move2_1 = new Sprite(new Texture(Gdx.files.internal("img/character/move2_1.png")));
		move3_1 = new Sprite(new Texture(Gdx.files.internal("img/character/move3_1.png")));
		background0 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpeg")));
		background1 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpeg")));
		background2 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpeg")));
		background3 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpeg")));
		blackFade = new Sprite(new Texture(Gdx.files.internal("img/background/blackfade.png")));
		gameload = new Sprite(new Texture(Gdx.files.internal("img/background/gameload.jpg")));
		bgm02 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm02.mp3"));
		bgm03 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm03.mp3"));
		bgm04 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm04.mp3"));
		bgm05 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm05.mp3"));
		bgm06 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm06.mp3"));
		bgm07 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm07.mp3"));
		attack0 = Gdx.audio.newSound(Gdx.files.internal(("sound/attack0.wav")));
		attack1 = Gdx.audio.newSound(Gdx.files.internal(("sound/attack1.wav")));
		attack2 = Gdx.audio.newSound(Gdx.files.internal(("sound/attack2.wav")));
		hover = Gdx.audio.newSound(Gdx.files.internal(("sound/hoverSound.wav")));
	}
	
	public void dispose() {
		block1.dispose();
		block2.dispose();
		block3.dispose();
		block4.dispose();
		block5.dispose();
		block6.dispose();
		block7.dispose();
		bgm02.dispose();
		bgm03.dispose();
		bgm04.dispose();
		bgm05.dispose();
		attack0.dispose();
		attack1.dispose();
		attack2.dispose();
	}
}
