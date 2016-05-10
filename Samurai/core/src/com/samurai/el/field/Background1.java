package com.samurai.el.field;

import com.samurai.el.resource.Resources;

public class Background1 extends Background {
	public Background1() {
		super();
		music = Resources.getInstance().bgm04;
		music.setLooping(true);
		music.play();
	}
}
