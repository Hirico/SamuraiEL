package com.samurai.el.field;

import com.badlogic.gdx.Gdx;
import com.samurai.el.resource.Resources;

public class Background2 extends Background {
	public Background2() {
		super();
		background = Resources.getInstance().background2;
		background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		music = Resources.getInstance().bgm04;
		music.setVolume(Gdx.app.getPreferences("volumePref").getFloat("musicVolume", 0.8f));
		music.setLooping(true);
		music.play();
	}
}
