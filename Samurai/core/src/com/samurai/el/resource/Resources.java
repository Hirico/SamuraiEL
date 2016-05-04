package com.samurai.el.resource;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Resources {
	public static Resources instance;

	public static Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}
		return instance;
	}
	
	private Resources() {
		
	}
}
