package com.samurai.el.field;

import com.samurai.el.resource.Resources;

public class Background1 extends Background {
	public Background1() {
		super();
		background = Resources.getInstance().background1;
		music = Resources.getInstance().bgm03;
		music.setLooping(true);
		music.play();
	}
}
