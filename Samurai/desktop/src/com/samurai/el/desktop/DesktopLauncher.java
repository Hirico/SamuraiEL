package com.samurai.el.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.samurai.el.mainmenu.Samurai;

public class DesktopLauncher {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable=true;
		config.title = "Colony";
		config.width = 1280;
		config.height = 720;
		config.fullscreen = true;
		new LwjglApplication(new Samurai(), config);
	}
}