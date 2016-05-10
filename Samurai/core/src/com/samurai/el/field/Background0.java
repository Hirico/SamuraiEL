package com.samurai.el.field;

import com.samurai.el.resource.Resources;

public class Background0 extends Background {
	public Background0() {
		super();
		music = Resources.getInstance().bgm04;
		music.setLooping(true);
		music.play();
	}
}
