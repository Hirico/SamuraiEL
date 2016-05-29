package com.samurai.el.desktop;

import org.lwjgl.LWJGLUtil;

import com.badlogic.gdx.Files;
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
		
		if(LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_MACOSX) {
			config.fullscreen = false;
		} else {
			config.fullscreen = true;
		}
		config.addIcon("icon128.png", Files.FileType.Internal);
		config.addIcon("icon32.png", Files.FileType.Internal);
		config.addIcon("icon16.png", Files.FileType.Internal);
		new LwjglApplication(new Samurai(), config);
	}
}
