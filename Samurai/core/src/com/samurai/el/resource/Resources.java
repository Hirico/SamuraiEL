package com.samurai.el.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Resources {

	public Texture block0 = new Texture(Gdx.files.internal("img/field/block0.png"));
	public Texture block1 = new Texture(Gdx.files.internal("img/field/block1.png"));
	public ParticleEffect block0P = new ParticleEffect();
	public ParticleEffect block1P = new ParticleEffect();
	//public Texture block2 = new Texture(Gdx.files.internal("img/field/block2.png"));
	//public Texture block3 = new Texture(Gdx.files.internal("img/field/block3.png"));
	//public Texture block4 = new Texture(Gdx.files.internal("img/field/block4.png"));
	//public Texture block5 = new Texture(Gdx.files.internal("img/field/block5.png"));
	public Texture block6 = new Texture(Gdx.files.internal("img/field/block6.png"));
	public Texture block7 = new Texture(Gdx.files.internal("img/field/block7.png"));
	public Texture planet0 = new Texture(Gdx.files.internal("img/field/planet0.png"));
	public Texture planet1 = new Texture(Gdx.files.internal("img/field/planet1.png"));
	public Texture planet2 = new Texture(Gdx.files.internal("img/field/planet2.png"));
	public Texture planet3 = new Texture(Gdx.files.internal("img/field/planet3.png"));
	public Texture planet4 = new Texture(Gdx.files.internal("img/field/planet4.png"));
	
	public Sprite redWin = new Sprite(new Texture(Gdx.files.internal("img/field/redwin.png")));
	public Sprite redLose = new Sprite(new Texture(Gdx.files.internal("img/field/redlose.png")));
	public Sprite blueWin = new Sprite(new Texture(Gdx.files.internal("img/field/bluewin.png")));
	public Sprite blueLose = new Sprite(new Texture(Gdx.files.internal("img/field/bluelose.png")));
	public Sprite draw = new Sprite(new Texture(Gdx.files.internal("img/field/draw.png")));

	public Sprite stand0_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_0.png")));
	public Sprite stand1_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_0.png")));
	public Sprite stand2_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_0.png")));
	public Sprite stand3_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_0.png")));
	public Sprite stand0_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_1.png")));
	public Sprite stand1_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_1.png")));
	public Sprite stand2_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_1.png")));
	public Sprite stand3_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_1.png")));
	public Sprite stand0_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_2.png")));
	public Sprite stand1_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_2.png")));
	public Sprite stand2_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_2.png")));
	public Sprite stand3_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_2.png")));
	public Sprite stand0_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_3.png")));
	public Sprite stand1_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_3.png")));
	public Sprite stand2_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_3.png")));
	public Sprite stand3_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_3.png")));
	public Sprite stand0_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_4.png")));
	public Sprite stand1_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_4.png")));
	public Sprite stand2_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_4.png")));
	public Sprite stand3_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_4.png")));
	public Sprite stand0_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_5.png")));
	public Sprite stand1_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_5.png")));
	public Sprite stand2_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_5.png")));
	public Sprite stand3_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_5.png")));
	public Sprite move0_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_0.png")));
	public Sprite move1_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_0.png")));
	public Sprite move2_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_0.png")));
	public Sprite move3_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_0.png")));
	public Sprite move0_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_1.png")));
	public Sprite move1_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_1.png")));
	public Sprite move2_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_1.png")));
	public Sprite move3_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_1.png")));
	public Sprite move0_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_2.png")));
	public Sprite move1_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_2.png")));
	public Sprite move2_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_2.png")));
	public Sprite move3_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_2.png")));
	public Sprite move0_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_3.png")));
	public Sprite move1_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_3.png")));
	public Sprite move2_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_3.png")));
	public Sprite move3_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_3.png")));
	public Sprite move0_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_4.png")));
	public Sprite move1_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_4.png")));
	public Sprite move2_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_4.png")));
	public Sprite move3_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_4.png")));
	public Sprite move0_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_5.png")));
	public Sprite move1_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_5.png")));
	public Sprite move2_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_5.png")));
	public Sprite move3_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_5.png")));

	public Sprite background0 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpg")));
	public Sprite background1 = new Sprite(new Texture(Gdx.files.internal("img/field/bg1.jpg")));
	public Sprite background2 = new Sprite(new Texture(Gdx.files.internal("img/field/bg2.jpg")));
	public Sprite background3 = new Sprite(new Texture(Gdx.files.internal("img/field/bg3.jpg")));
	public Sprite gameload = new Sprite(new Texture(Gdx.files.internal("img/background/gameload.jpg")));
	public Sprite gameload2 = new Sprite(new Texture(Gdx.files.internal("img/background/gameload2.jpg")));
	public Sprite help = new Sprite(new Texture(Gdx.files.internal("img/background/help.png")));
	public Sprite blackFade = new Sprite(new Texture(Gdx.files.internal("img/background/blackfade.png")));

	public Music bgm02 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm02.mp3"));
	public Music bgm03 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm03.mp3"));
	public Music bgm04 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm04.mp3"));
	public Music bgm05 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm05.mp3"));
	public Music bgm06 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm06.mp3"));
	public Music bgm07 = Gdx.audio.newMusic(Gdx.files.internal("music/bgm07.mp3"));

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
		block0P.load(Gdx.files.internal("img/field/block0.p"), Gdx.files.internal("img/field"));;
		block1P.load(Gdx.files.internal("img/field/block1.p"), Gdx.files.internal("img/field"));;
		//block2 = new Texture(Gdx.files.internal("img/field/block2.png"));
		//block3 = new Texture(Gdx.files.internal("img/field/block3.png"));
		//block4 = new Texture(Gdx.files.internal("img/field/block4.png"));
		//block5 = new Texture(Gdx.files.internal("img/field/block5.png"));
		block6 = new Texture(Gdx.files.internal("img/field/block6.png"));
		block7 = new Texture(Gdx.files.internal("img/field/block7.png"));
		planet0 = new Texture(Gdx.files.internal("img/field/planet0.png"));
		planet1 = new Texture(Gdx.files.internal("img/field/planet1.png"));
		planet2 = new Texture(Gdx.files.internal("img/field/planet2.png"));
		planet3 = new Texture(Gdx.files.internal("img/field/planet3.png"));
		planet4 = new Texture(Gdx.files.internal("img/field/planet4.png"));
		
		redWin = new Sprite(new Texture(Gdx.files.internal("img/field/redwin.png")));
		redLose = new Sprite(new Texture(Gdx.files.internal("img/field/redlose.png")));
		blueWin = new Sprite(new Texture(Gdx.files.internal("img/field/bluewin.png")));
		blueLose = new Sprite(new Texture(Gdx.files.internal("img/field/bluelose.png")));
		draw = new Sprite(new Texture(Gdx.files.internal("img/field/draw.png")));
		stand0_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_0.png")));
		stand1_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_0.png")));
		stand2_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_0.png")));
		stand3_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_0.png")));
		stand0_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_1.png")));
		stand1_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_1.png")));
		stand2_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_1.png")));
		stand3_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_1.png")));
		stand0_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_2.png")));
		stand1_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_2.png")));
		stand2_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_2.png")));
		stand3_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_2.png")));
		stand0_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_3.png")));
		stand1_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_3.png")));
		stand2_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_3.png")));
		stand3_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_3.png")));
		stand0_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_4.png")));
		stand1_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_4.png")));
		stand2_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_4.png")));
		stand3_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_4.png")));
		stand0_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_5.png")));
		stand1_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_5.png")));
		stand2_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_5.png")));
		stand3_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_5.png")));
		move0_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_0.png")));
		move1_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_0.png")));
		move2_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_0.png")));
		move3_0 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_0.png")));
		move0_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_1.png")));
		move1_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_1.png")));
		move2_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_1.png")));
		move3_1 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_1.png")));
		move0_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_2.png")));
		move1_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_2.png")));
		move2_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_2.png")));
		move3_2 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_2.png")));
		move0_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_3.png")));
		move1_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_3.png")));
		move2_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_3.png")));
		move3_3 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_3.png")));
		move0_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_4.png")));
		move1_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_4.png")));
		move2_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_4.png")));
		move3_4 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_4.png")));
		move0_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand0_5.png")));
		move1_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand1_5.png")));
		move2_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand2_5.png")));
		move3_5 = new Sprite(new Texture(Gdx.files.internal("img/character/stand3_5.png")));
		background0 = new Sprite(new Texture(Gdx.files.internal("img/field/bg0.jpg")));
		background1 = new Sprite(new Texture(Gdx.files.internal("img/field/bg1.jpg")));
		background2 = new Sprite(new Texture(Gdx.files.internal("img/field/bg2.jpg")));
		background3 = new Sprite(new Texture(Gdx.files.internal("img/field/bg3.jpg")));
		help = new Sprite(new Texture(Gdx.files.internal("img/background/help.png")));
		blackFade = new Sprite(new Texture(Gdx.files.internal("img/background/blackfade.png")));
		gameload = new Sprite(new Texture(Gdx.files.internal("img/background/gameload.jpg")));
		gameload2 = new Sprite(new Texture(Gdx.files.internal("img/background/gameload2.jpg")));
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
		block0.dispose();
		block1.dispose();
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
