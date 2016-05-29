
package com.samurai.el.mainmenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.Music;
import com.samurai.el.maingame.OverScreen;
import com.samurai.el.resource.Resources;


public class Samurai extends Game {
	
	 Music music;
	@Override 
	public void create () {
		Gdx.graphics.setCursor(Resources.getInstance().cursor);
		

		if(Gdx.app.getType() == ApplicationType.Android) {
			ScreenCenter.setscreencenter(this);
			setScreen(new MainMenuScreen());
		} 
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			setScreen(new SplashScreen(this));
		}
	}	
}

