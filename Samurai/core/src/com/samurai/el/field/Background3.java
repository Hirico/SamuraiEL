package com.samurai.el.field;

import com.badlogic.gdx.Gdx;
import com.samurai.el.resource.Resources;

public class Background3 extends Background {
	public Background3() {
		super();
		background = Resources.getInstance().background3;
		background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		music = Resources.getInstance().bgm05;
		music.setVolume(Gdx.app.getPreferences("volumePref").getFloat("musicVolume", 0.8f));
		music.setLooping(true);
		music.play();
	}
}
