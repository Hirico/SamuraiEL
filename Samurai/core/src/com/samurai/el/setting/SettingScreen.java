
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
	CheckBox setwindowed;
	CheckBox setfullscreen;
	SpriteBatch batch;
	Sprite background;
	BitmapFont font;
	Slider musicvolume;
	Label musicvolume_label;
	Slider soundvolume;
	Label soundvolume_label;	
	
	Sprite blackFade;
	SpriteBatch fadeBatch;
	public float fade;

	
	public SettingScreen() {
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);
		batch=new SpriteBatch();
		stage = new Stage(new StretchViewport(1280,720));
		
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		blackFade = Resources.getInstance().blackFade;
		
		
		background=new Sprite(new Texture("img/background/setting.png"));
		
		
		
		
		Sprite returnbutton0=new Sprite(new Texture("img/button/mainmenu/return0.png"));
		Sprite returnbutton1=new Sprite(new Texture("img/button/mainmenu/return1.png"));
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbutton1);
		
		returnbutton=new ImageButton(returnbuttonstyle);
		
		Sprite bar=new Sprite(new Texture("img/setting/barA.png"));
		Sprite tt=new Sprite(new Texture("img/setting/tA.png"));
		Slider.SliderStyle volumestyle=new Slider.SliderStyle(new SpriteDrawable(bar),new SpriteDrawable(tt)); 
		
		musicvolume=new Slider(0,1000,1,false,volumestyle);
		soundvolume=new Slider(0,1000,1,false,volumestyle);
		
		
		
		Sprite check0=new Sprite(new Texture("img/setting/check0.png"));
		Sprite check1=new Sprite(new Texture("img/setting/check1.png"));
		Sprite check2=new Sprite(new Texture("img/setting/check2.png"));
		
		CheckBox.CheckBoxStyle checkstyle=new CheckBox.CheckBoxStyle();
		checkstyle.checkboxOver=new SpriteDrawable(check1);
		checkstyle.checkboxOn=new SpriteDrawable(check2);
		checkstyle.checkboxOff=new SpriteDrawable(check0);
		checkstyle.font=font;
		checkstyle.fontColor=new Color(Color.YELLOW);
		
		setwindowed=new CheckBox("   windowed",checkstyle);
		setfullscreen=new CheckBox("   fullscreen",checkstyle);
		
		
		
		Label.LabelStyle labelstyle =new Label.LabelStyle(font, Color.YELLOW);
		
		musicvolume_label=new Label("Music Volume",labelstyle);
		soundvolume_label=new Label("Sound Volume",labelstyle);
	}
	
	@Override
	public void show() {
		fade = 1.0f;
		Gdx.input.setInputProcessor(stage);
		
		stage.addActor(musicvolume);
		stage.addActor(soundvolume);
		musicvolume.setBounds(640-700/2+100, 400, 700, 35);
		musicvolume.setValue(1000*Gdx.app.getPreferences("volumePref").getFloat("musicVolume", 0.5f));
		soundvolume.setBounds(640-700/2+100, 300, 700, 35);
		soundvolume.setValue(1000*Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 0.5f));
		
		stage.addActor(musicvolume_label);
		stage.addActor(soundvolume_label);
		musicvolume_label.setX(100);
		musicvolume_label.setY(400);
		soundvolume_label.setX(100);
		soundvolume_label.setY(300);
		
		
		
		stage.addActor(setwindowed);
		stage.addActor(setfullscreen);
		setwindowed.setX(640-65/2-300);
		setwindowed.setY(150);
		setwindowed.setChecked(true);
		
		setfullscreen.setX(640-65/2+100);
		setfullscreen.setY(150);
		setfullscreen.setChecked(false);
		
		
		
		

		
		stage.addActor(returnbutton);
		returnbutton.setPosition(40,30);
	
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
		
		
		setwindowed.addListener(new InputListener(){
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
			{
				if (setwindowed.isChecked())
				{
					setfullscreen.setChecked(false);
					//Gdx.graphics.setWindowedMode(1280,720);
				}else
				{
					setfullscreen.setChecked(true);
					//Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
				}	   
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				return true;  
			}
		});
		
		setfullscreen.addListener(new InputListener(){
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
			{
				if (setfullscreen.isChecked())
				{
					setwindowed.setChecked(false);
					//Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
				}else
				{
					setwindowed.setChecked(true);
					//Gdx.graphics.setWindowedMode(1280,720);
					
				}	   
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
				return true;  
			}
		});
		

		musicvolume.addListener(new ChangeListener()
		{
			 public void changed (ChangeEvent event, Actor actor) 
			 {
				 Gdx.app.getPreferences("volumePref").putFloat("musicVolume", (float) (musicvolume.getValue()/1000.0));
				 Gdx.app.getPreferences("volumePref").flush();
				 ScreenCenter.changeVolume();
			 }
		});
		
		soundvolume.addListener(new ChangeListener()
		{
			 public void changed (ChangeEvent event, Actor actor) 
			 {
				 Gdx.app.getPreferences("volumePref").putFloat("soundVolume", (float) (soundvolume.getValue()/1000.0));
				 Gdx.app.getPreferences("volumePref").flush();
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
		
		if (fade > 0) {
			fade = Math.max(fade - Gdx.graphics.getDeltaTime() / 2.f, 0);
			fadeBatch.begin();
			blackFade.setColor(blackFade.getColor().r, blackFade.getColor().g, blackFade.getColor().b, fade);
			blackFade.draw(fadeBatch);
			fadeBatch.end();
		}
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
		fadeBatch.dispose();
	}

}