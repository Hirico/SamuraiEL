package com.samurai.el.mainmenu;
import com.badlogic.gdx.Game;
import com.samurai.el.achievement.AchievementScreen;
import com.samurai.el.gamesetting.GameSetScreen;
import com.samurai.el.maingame.GameScreen;
import com.samurai.el.setting.SettingScreen;
public class ScreenCenter 
{
	static public Game game;
	static public SettingScreen settingscreen;
	static public AchievementScreen achievementscreen;
	static public GameSetScreen gamesetscreen;
	static public MainMenuScreen mainmenuscreen;

	
	public static void setscreencenter(Game _game)
	{
		game=_game;
		
		mainmenuscreen=new MainMenuScreen();
		settingscreen=new SettingScreen();
		achievementscreen=new AchievementScreen();
		gamesetscreen=new GameSetScreen();

		
	}
	public static void setscreen(int screennum)
	{
		switch(screennum)
		{
		case 0:
			game.setScreen(mainmenuscreen);
			break;
		case 1:
			game.setScreen(settingscreen);
			break;
		case 2:
			game.setScreen(gamesetscreen);
			break;
		case 3:
			game.setScreen(achievementscreen);
			break;
		case 4:
			game.setScreen(new GameScreen(game));
		}
	}
}
