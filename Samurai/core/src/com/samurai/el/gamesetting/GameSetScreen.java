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
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.maingame.GameScreen;
import com.samurai.el.mainmenu.MainMenuScreen;



public class GameSetScreen implements Screen{
	Game game;
	Stage stage;
	ImageButton returnbutton;
	ImageButton enterbutton;
	BitmapFont font;
	
	CheckBox[] fieldcheckbox=new CheckBox[4];
	CheckBox[] playercheckbox=new CheckBox[6];
	CheckBox[] difficultycheckbox=new CheckBox[3];
	
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
		enterbutton.setPosition(720-enterbutton.getWidth()-50, 20);
		
		
		
		
		Sprite c0=new Sprite(new Texture("foxwel_temp/choose/c0.png"));
		Sprite c1=new Sprite(new Texture("foxwel_temp/choose/c1.png"));
		Sprite c4=new Sprite(new Texture("foxwel_temp/choose/c4.png"));
		
		CheckBox.CheckBoxStyle choosestyle=new CheckBox.CheckBoxStyle();
		choosestyle.checkboxOver=new SpriteDrawable(c1);
		choosestyle.checkboxOn=new SpriteDrawable(c4);
		choosestyle.checkboxOff=new SpriteDrawable(c0);
		choosestyle.font=font;
		choosestyle.fontColor=new Color(0, 1, 0, 1);
	
		fieldcheckbox[0]=new CheckBox("map0",choosestyle);
		fieldcheckbox[1]=new CheckBox("map1",choosestyle);
		fieldcheckbox[2]=new CheckBox("map2",choosestyle);
		fieldcheckbox[3]=new CheckBox("map3",choosestyle);

		int filedstartX=120;
		for (int i=0;i<4;++i)
		{
			stage.addActor(fieldcheckbox[i]);
			fieldcheckbox[i].setY(580);
			fieldcheckbox[i].setX(filedstartX+i*260);
		}
	
		for (int i=0;i<4;++i)
		{
			final int temp1=i;
			fieldcheckbox[i].addListener(new InputListener()
			{
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
				{
					for (int j=0;j<4;++j)
	        	   	{
	        	   		if (temp1!=j) fieldcheckbox[j].setChecked(false);
	        	   	}
				}
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
						return true;
	           }
			});
		}
		
		
		playercheckbox[0]=new CheckBox("redSpear",choosestyle);
		playercheckbox[1]=new CheckBox("redSword",choosestyle);
		playercheckbox[2]=new CheckBox("redAxe",choosestyle);
		playercheckbox[3]=new CheckBox("blueSpear",choosestyle);
		playercheckbox[4]=new CheckBox("blueSword",choosestyle);
		playercheckbox[5]=new CheckBox("blueAxe",choosestyle);
		
		int playerstartX=240;
		for (int i=0;i<6;++i)
		{
			stage.addActor(playercheckbox[i]);
			if (i<3) playercheckbox[i].setY(430);else playercheckbox[i].setY(280);
			playercheckbox[i].setX(playerstartX+(i%3)*280);
		}
	
		for (int i=0;i<6;++i)
		{
			final int temp1=i;
			playercheckbox[i].addListener(new InputListener()
			{
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
				{
					for (int j=0;j<6;++j)
	        	   	{
	        	   		if (temp1!=j) playercheckbox[j].setChecked(false);
	        	   	}
				}
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
						return true;
	           }
			});
		}
		
		difficultycheckbox[0]=new CheckBox("easy",choosestyle);
		difficultycheckbox[1]=new CheckBox("normal",choosestyle);
		difficultycheckbox[2]=new CheckBox("hard",choosestyle);

		int difficultydstartX=240;
		for (int i=0;i<3;++i)
		{
			stage.addActor(difficultycheckbox[i]);
			difficultycheckbox[i].setY(130);
			difficultycheckbox[i].setX(difficultydstartX+i*280);
		}
	
		for (int i=0;i<3;++i)
		{
			final int temp1=i;
			difficultycheckbox[i].addListener(new InputListener()
			{
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
				{
					for (int j=0;j<3;++j)
	        	   	{
	        	   		if (temp1!=j) difficultycheckbox[j].setChecked(false);
	        	   	}
				}
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
						return true;
	           }
			});
		}
		
		
		/*
		 * fieldcheckbox1.addListener(new InputListener(){
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
		*/
		
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
		
		
		enterbutton.addListener(new InputListener(){
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   GameInstance.setInstance(getchoose(fieldcheckbox), getchoose(playercheckbox), 200, getchoose(difficultycheckbox));
	        	   game.setScreen(new GameScreen(game));
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
	        	   if (ischoose(fieldcheckbox)&&ischoose(playercheckbox)&&ischoose(difficultycheckbox)) return true;else return false;
	           }
		});
	}

	boolean ischoose(CheckBox[] checkone)
	{
		for (int i=0;i<checkone.length;++i) if (checkone[i].isChecked()) return true;
		return false;
	}
	
	int getchoose(CheckBox[] checkone)
	{
		for (int i=0;i<checkone.length;++i) if (checkone[i].isChecked()) return i;
		return 0;
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
