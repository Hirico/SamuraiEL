
package com.samurai.el.mainmenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.samurai.el.resource.Resources;


public class Samurai extends Game {
	@Override 
	public void create () {
		Gdx.graphics.setCursor(Resources.getInstance().cursor);
		ScreenCenter.setscreencenter(this);
		setScreen(new MainMenuScreen());
	}	
}

