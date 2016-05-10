package com.samurai.el.mainmenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
	static public Music music;
	
	public static void setscreencenter(Game _game)
	{
		game=_game;
		
		//mainmenuscreen=new MainMenuScreen();
		//settingscreen=new SettingScreen();
		//achievementscreen=new AchievementScreen();
		//gamesetscreen=new GameSetScreen();

		music=Gdx.audio.newMusic(Gdx.files.internal("foxwel_temp/test.mp3"));
		music.setLooping(true);
		music.play();
	}
	
	public static void stopmusic()
	{
		music.stop();
		music.dispose();
	}
	
	public static void startmusic()
	{
		music=Gdx.audio.newMusic(Gdx.files.internal("foxwel_temp/test.mp3"));
		music.setLooping(true);
		music.play();
	}
	
	public static void setscreen(int screennum)
	{
		switch(screennum)
		{
			case 0:
				game.getScreen().dispose();
				game.setScreen(new MainMenuScreen());
				break;
			case 1:
				game.getScreen().dispose();
				game.setScreen(new SettingScreen());
				break;
			case 2:
				game.getScreen().dispose();
				game.setScreen(new GameSetScreen());
				break;
			case 3:
				game.getScreen().dispose();
				game.setScreen(new AchievementScreen());
				break;
			case 4:
				game.setScreen(new GameScreen(game));
		}
	}
}
