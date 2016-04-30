package com.samurai.el.mainmenu;

import com.badlogic.gdx.Game;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.maingame.GameScreen;

public class Samurai extends Game {
	@Override 
	public void create () {
		setScreen(new MainMenuScreen(this));
	}	
}
