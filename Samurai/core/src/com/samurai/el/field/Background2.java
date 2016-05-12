package com.samurai.el.field;

import com.samurai.el.resource.Resources;

public class Background2 extends Background {
	public Background2() {
		super();
		background = Resources.getInstance().background2;
		music = Resources.getInstance().bgm04;
		music.setVolume(0.8f);
		music.setLooping(true);
		music.play();
	}
}
