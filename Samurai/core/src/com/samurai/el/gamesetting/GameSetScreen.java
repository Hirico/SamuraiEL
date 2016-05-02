package com.samurai.el.gamesetting;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.mainmenu.MainMenuScreen;



public class GameSetScreen implements Screen{
	Game game;
	Stage stage;
	ImageButton returnbutton;
	ImageButton enterbutton;
	BitmapFont font;
	
	CheckBox fieldcheckbox0;
	CheckBox fieldcheckbox1;
	CheckBox fieldcheckbox2;
	CheckBox fieldcheckbox3;
	
	
	
	public GameSetScreen(Game game) {
		this.game = game;//return game;
		
	}
	
	@Override
	public void show() {
		
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);
		// TODO Auto-generated method stub
		stage = new Stage(new StretchViewport(1280,720));
		Gdx.input.setInputProcessor(stage);
		
		Sprite returnbuttonp0=new Sprite(new Texture("foxwel_temp/return0.png"));
		Sprite returnbuttonp1=new Sprite(new Texture("foxwel_temp/return1.png"));
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbuttonp0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbuttonp0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbuttonp1);
		
		returnbutton=new ImageButton(returnbuttonstyle);
		stage.addActor(returnbutton);
		returnbutton.setPosition(1280-128, 0);
		

		
		
		Sprite enterbuttonp0=new Sprite(new Texture("foxwel_temp/choose/enter0.png"));
		Sprite enterbuttonp1=new Sprite(new Texture("foxwel_temp/choose/enter1.png"));
		ImageButton.ImageButtonStyle enterbuttonstyle=new ImageButton.ImageButtonStyle();
		
		enterbuttonstyle.imageUp=new SpriteDrawable(enterbuttonp0);
		enterbuttonstyle.imageDown=new SpriteDrawable(enterbuttonp0);
		enterbuttonstyle.imageOver=new SpriteDrawable(enterbuttonp1);
		
		enterbutton=new ImageButton(enterbuttonstyle);
		stage.addActor(enterbutton);
		enterbutton.setPosition(720-enterbutton.getWidth()-50, 200);
		
		
		Sprite c0=new Sprite(new Texture("foxwel_temp/choose/c0.png"));
		Sprite c1=new Sprite(new Texture("foxwel_temp/choose/c1.png"));
		Sprite c2=new Sprite(new Texture("foxwel_temp/choose/c2.png"));
		Sprite c4=new Sprite(new Texture("foxwel_temp/choose/c4.png"));
		
		
		CheckBox.CheckBoxStyle temp=new CheckBox.CheckBoxStyle();
		temp.checkboxOver=new SpriteDrawable(c1);
		temp.checkboxOn=new SpriteDrawable(c4);
		temp.checkboxOff=new SpriteDrawable(c0);
		temp.font=font;
		temp.fontColor=new Color(0, 1, 0, 1);
		
	
		fieldcheckbox0=new CheckBox("map0",temp);
		fieldcheckbox1=new CheckBox("map1",temp);
		fieldcheckbox2=new CheckBox("map2",temp);
		fieldcheckbox3=new CheckBox("map3",temp);
		
		
		stage.addActor(fieldcheckbox0);
		stage.addActor(fieldcheckbox1);
		stage.addActor(fieldcheckbox2);
		stage.addActor(fieldcheckbox3);
		
		
		fieldcheckbox0.setX(720-fieldcheckbox0.getWidth()-400);
		fieldcheckbox0.setY(360);
		fieldcheckbox1.setX(720-fieldcheckbox1.getWidth()-130);
		fieldcheckbox1.setY(360);
		fieldcheckbox2.setX(720-fieldcheckbox2.getWidth()+130);
		fieldcheckbox2.setY(360);
		fieldcheckbox3.setX(720-fieldcheckbox3.getWidth()+400);
		fieldcheckbox3.setY(360);
		
		fieldcheckbox0.addListener(new InputListener(){
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		        	fieldcheckbox1.setChecked(false);
		   			fieldcheckbox2.setChecked(false);
		   			fieldcheckbox3.setChecked(false);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
						return true;
	           }
		});
		
		fieldcheckbox1.addListener(new InputListener(){
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		        	fieldcheckbox0.setChecked(false);
		   			fieldcheckbox2.setChecked(false);
		   			fieldcheckbox3.setChecked(false);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
						return true;
	           }
		});
		
		fieldcheckbox2.addListener(new InputListener(){
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		        	fieldcheckbox1.setChecked(false);
		   			fieldcheckbox0.setChecked(false);
		   			fieldcheckbox3.setChecked(false);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
						return true;
	           }
		});
		
		fieldcheckbox3.addListener(new InputListener(){
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	fieldcheckbox1.setChecked(false);
	   			fieldcheckbox2.setChecked(false);
	   			fieldcheckbox0.setChecked(false);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
						return true;
	           }
		});
		
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
