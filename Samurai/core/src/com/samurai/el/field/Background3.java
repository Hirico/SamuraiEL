package com.samurai.el.field;

import com.samurai.el.resource.Resources;

public class Background3 extends Background {
	public Background3() {
		super();
		music = Resources.getInstance().bgm04;
		music.setLooping(true);
		music.play();
	}
}
