package com.el.colonyandroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samurai.el.mainmenu.MainMenuScreen;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;

public class Colony extends Game {
	@Override
	public void create () {
		Gdx.graphics.setCursor(Resources.getInstance().cursor);
		ScreenCenter.setscreencenter(this);
		setScreen(new MainMenuScreen());
	}
}

