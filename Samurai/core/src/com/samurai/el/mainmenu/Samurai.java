
package com.samurai.el.mainmenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.Music;
import com.samurai.el.maingame.OverScreen;
import com.samurai.el.resource.Resources;


public class Samurai extends Game {
	
	// Music music;
	@Override 
	public void create () {
		Gdx.graphics.setCursor(Resources.getInstance().cursor);
		
		/*int[][] result={{12,3,0,1},{34,0,4,2},{56,2,2,3},{78,0,3,4},{90,2,1,5},{100,4,1,6}};
		int[] score={200,233};
		setScreen(new OverScreen(result,0,music,score));*/
		if(Gdx.app.getType() == ApplicationType.Android) {
			ScreenCenter.setscreencenter(this);
			setScreen(new MainMenuScreen());
		} 
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			setScreen(new SplashScreen(this));
		}
	}	
}

