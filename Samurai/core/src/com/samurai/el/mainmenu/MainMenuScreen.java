package com.samurai.el.mainmenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.achievement.AchievementScreen;
import com.samurai.el.gamesetting.GameSetScreen;
import com.samurai.el.setting.SettingScreen;


public class MainMenuScreen implements Screen{
	private Game game;
	Stage stage;
	ImageButton button1;
	ImageButton button2;
	ImageButton button3;
	ImageButton button4;
	Texture buttonpng;
	
	public MainMenuScreen(Game game) {
		this.game = game;//return game;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage(new StretchViewport(1280,720));
		Gdx.input.setInputProcessor(stage);
		
		buttonpng=new Texture(Gdx.files.internal("foxwel_temp/mainbutton.png"));
		final TextureRegion[][] buttonsplit=TextureRegion.split(buttonpng, 128, 128);
		Sprite returnbutton0=new Sprite(new Texture("foxwel_temp/return0.png"));
		Sprite returnbutton1=new Sprite(new Texture("foxwel_temp/return1.png"));
		
		ImageButton.ImageButtonStyle button1style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button2style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button3style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button4style=new ImageButton.ImageButtonStyle();
		
		button1style.imageUp=new TextureRegionDrawable(buttonsplit[0][0]);
		button1style.imageDown=new TextureRegionDrawable(buttonsplit[0][0]);
		button1style.imageOver=new TextureRegionDrawable(buttonsplit[0][1]);
		
		button2style.imageUp=new TextureRegionDrawable(buttonsplit[0][2]);
		button2style.imageDown=new TextureRegionDrawable(buttonsplit[0][2]);
		button2style.imageOver=new TextureRegionDrawable(buttonsplit[0][3]);
		
		button3style.imageUp=new TextureRegionDrawable(buttonsplit[0][4]);
		button3style.imageDown=new TextureRegionDrawable(buttonsplit[0][4]);
		button3style.imageOver=new TextureRegionDrawable(buttonsplit[0][5]);
		

		
		button4style.imageUp=new SpriteDrawable(returnbutton0);
		button4style.imageDown=new SpriteDrawable(returnbutton0);
		button4style.imageOver=new SpriteDrawable(returnbutton1);
		
		button1=new ImageButton(button1style);
		button2=new ImageButton(button2style);
		button3=new ImageButton(button3style);
		button4=new ImageButton(button4style);
		
		stage.addActor(button1);
		stage.addActor(button2);
		stage.addActor(button3);
		stage.addActor(button4);
		
		button1.setPosition(576-200, 360-64);
		button2.setPosition(576, 360-64);
		button3.setPosition(576+200, 360-64);
		button4.setPosition(1280-128, 0);
		
		button1.addListener(new InputListener(){
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   game.setScreen(new SettingScreen(game));
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {

						return true;
	           }
		});
		
		button2.addListener(new InputListener(){
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   game.setScreen(new GameSetScreen(game));
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {

						return true;
	           }
		});
		
		button3.addListener(new InputListener(){
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   game.setScreen(new AchievementScreen(game));
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
	        	   		button3=new ImageButton( new TextureRegionDrawable(buttonsplit[0][5]),new TextureRegionDrawable(buttonsplit[0][4]));

						return true;
	           }
		});
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
