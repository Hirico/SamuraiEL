package com.samurai.el.field;

import com.samurai.el.resource.Resources;

public class Background3 extends Background {
	public Background3() {
		super();
		background = Resources.getInstance().background3;
		music = Resources.getInstance().bgm05;
		music.setVolume(0.8f);
		music.setLooping(true);
		music.play();
	}
}
