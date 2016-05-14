
package com.samurai.el.setting;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;

public class SettingScreen implements Screen{
	Game game;
	Screen mainmenuscreen;
	Stage stage;
	ImageButton returnbutton;
	CheckBox ismusic;
	SpriteBatch batch;
	Sprite background;
	BitmapFont font;
	Slider volume;
	Label volume_label;
	
	public SettingScreen() {
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);
		
		batch=new SpriteBatch();
		background=new Sprite(new Texture("img/background/setting.png"));
		
		stage = new Stage(new StretchViewport(1280,720));
		
		
		Sprite returnbutton0=new Sprite(new Texture("foxwel_temp/return0.png"));
		Sprite returnbutton1=new Sprite(new Texture("foxwel_temp/return1.png"));
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbutton1);
		
		returnbutton=new ImageButton(returnbuttonstyle);
		
		Sprite bar=new Sprite(new Texture("img/setting/barA.png"));
		Sprite tt=new Sprite(new Texture("img/setting/tA.png"));
		Slider.SliderStyle volumestyle=new Slider.SliderStyle(new SpriteDrawable(bar),new SpriteDrawable(tt)); 
		
		volume=new Slider(0,1000,1,false,volumestyle);
		
		
		
		Sprite check0=new Sprite(new Texture("img/setting/check0.png"));
		Sprite check1=new Sprite(new Texture("img/setting/check1.png"));
		Sprite check2=new Sprite(new Texture("img/setting/check2.png"));
		
		CheckBox.CheckBoxStyle checkstyle=new CheckBox.CheckBoxStyle();
		checkstyle.checkboxOver=new SpriteDrawable(check1);
		checkstyle.checkboxOn=new SpriteDrawable(check2);
		checkstyle.checkboxOff=new SpriteDrawable(check0);
		checkstyle.font=font;
		checkstyle.fontColor=new Color(Color.YELLOW);
		
		ismusic=new CheckBox("   Music",checkstyle);
		
		
		Label.LabelStyle labelstyle =new Label.LabelStyle(font, Color.YELLOW);
		
		volume_label=new Label("volume",labelstyle);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		stage.addActor(volume);
		volume.setBounds(640-700/2, 450, 700, 35);
		volume.setValue(1000*Gdx.app.getPreferences("volumePref").getFloat("musicVolume", 0.5f));
	
		
		stage.addActor(ismusic);
		ismusic.setX(640-65/2);
		ismusic.setY(300);
		ismusic.setChecked(true);
		
		
		
		stage.addActor(volume_label);
		volume_label.setX(150);
		volume_label.setY(450);
		
		stage.addActor(returnbutton);
		returnbutton.setPosition(1280-128, 0);
	
		returnbutton.addListener(new InputListener(){
			 @Override
		        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.enter(event, x, y, pointer, fromActor);
		            Sound sound = Resources.getInstance().hover;
		            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
		        
		        }
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   ScreenCenter.setscreen(0);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
	        	 

						return true;  
	           }
		});
		
		ismusic.addListener(new InputListener(){
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   if (!ismusic.isChecked())
	        	   {
	        		   volume.setDisabled(true);
	        		   ScreenCenter.stopmusic();
	        	   }else{
	        		   volume.setDisabled(false);
	        		   ScreenCenter.startmusic();
	        	   }
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
	        	 

						return true;  
	           }
		});
		

		
		
		volume.addListener(new ChangeListener(){
			 public void changed (ChangeEvent event, Actor actor) {
				 //ScreenCenter.music.setVolume((float) (volume.getValue()/1000.0));
				 Gdx.app.getPreferences("volumePref").putFloat("musicVolume", (float) (volume.getValue()/1000.0));
				 Gdx.app.getPreferences("volumePref").flush();
				 ScreenCenter.changeVolume();
	           }
		});
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background,0,0);
		batch.end();
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
		stage.dispose();
		batch.dispose();
	}

}