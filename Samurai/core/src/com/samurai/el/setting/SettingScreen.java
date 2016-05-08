package com.samurai.el.setting;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.mainmenu.MainMenuScreen;

public class SettingScreen implements Screen{
	Game game;
	Stage stage;
	ImageButton returnbutton;
	public SettingScreen(Game game) {
		this.game = game;//return game;
		
	}
	
	@Override
	public void show() {
		stage = new Stage(new StretchViewport(1280,720));
		Gdx.input.setInputProcessor(stage);
		
		Sprite returnbutton0=new Sprite(new Texture("foxwel_temp/return0.png"));
		Sprite returnbutton1=new Sprite(new Texture("foxwel_temp/return1.png"));
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbutton1);
		
		
		returnbutton=new ImageButton(returnbuttonstyle);
		stage.addActor(returnbutton);
		returnbutton.setPosition(1280-128, 0);
		
		returnbutton.addListener(new InputListener(){
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   game.setScreen(new MainMenuScreen(game));
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
	        	 

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
