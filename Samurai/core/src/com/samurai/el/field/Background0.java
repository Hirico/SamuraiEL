package com.samurai.el.field;

import com.badlogic.gdx.Gdx;
import com.samurai.el.resource.Resources;

public class Background0 extends Background {
	public Background0() {
		super();
		background = Resources.getInstance().background0;
		background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		music = Resources.getInstance().bgm02;
		music.setVolume(Gdx.app.getPreferences("volumePref").getFloat("musicVolume", 0.8f));
		music.setLooping(true);
		music.play();
	}
}
