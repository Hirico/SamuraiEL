
package com.samurai.el.mainmenu;

import com.badlogic.gdx.Game;


public class Samurai extends Game {
	@Override 
	public void create () {
		
		ScreenCenter.setscreencenter(this);
		setScreen(new MainMenuScreen());
	}	
}

