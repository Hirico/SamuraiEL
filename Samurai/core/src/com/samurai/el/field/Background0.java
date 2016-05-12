package com.samurai.el.field;

import com.samurai.el.resource.Resources;

public class Background0 extends Background {
	public Background0() {
		super();
		background = Resources.getInstance().background0;
		music = Resources.getInstance().bgm02;
		music.setVolume(0.8f);
		music.setLooping(true);
		music.play();
	}
}
