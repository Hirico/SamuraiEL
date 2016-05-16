package com.samurai.el.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.resource.Resources;


public class MainMenuScreen implements Screen{
	Stage stage;
	ImageButton button1;
	ImageButton button2;
	ImageButton button3;
	ImageButton button4;
	Sprite buttonA0;
	Sprite buttonA1;
	Sprite buttonB0;
	Sprite buttonB1;
	Sprite buttonC0;
	Sprite buttonC1;
	Sprite blackFade;
	SpriteBatch fadeBatch;
	public float fade;
	
	
	Texture buttonpng;
	SpriteBatch batch;
	Sprite background;
	public Music music;
	
	public MainMenuScreen()
	{
		stage=new Stage(new StretchViewport(1280,720));
		
		batch=new SpriteBatch();
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		blackFade = Resources.getInstance().blackFade;
		background=new Sprite(new Texture(Gdx.files.internal("img/background/mainmenu.jpg")));

		buttonA0=new Sprite(new Texture(Gdx.files.internal("img/button/mainmenu/buttonA0.png")));
		buttonA1=new Sprite(new Texture(Gdx.files.internal("img/button/mainmenu/buttonA1.png")));
		buttonB0=new Sprite(new Texture(Gdx.files.internal("img/button/mainmenu/buttonB0.png")));
		buttonB1=new Sprite(new Texture(Gdx.files.internal("img/button/mainmenu/buttonB1.png")));
		buttonC0=new Sprite(new Texture(Gdx.files.internal("img/button/mainmenu/buttonC0.png")));
		buttonC1=new Sprite(new Texture(Gdx.files.internal("img/button/mainmenu/buttonC1.png")));
		
		
		Sprite returnbutton0=new Sprite(new Texture("img/button/mainmenu/return0.png"));
		Sprite returnbutton1=new Sprite(new Texture("img/button/mainmenu/return1.png"));
		
		ImageButton.ImageButtonStyle button1style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button2style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button3style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button4style=new ImageButton.ImageButtonStyle();
		
		button2style.imageUp=new TextureRegionDrawable(buttonA0);	
		button2style.imageDown=new TextureRegionDrawable(buttonA0);
		button2style.imageOver=new TextureRegionDrawable(buttonA1);
		
		button1style.imageUp=new TextureRegionDrawable(buttonB0);
		button1style.imageDown=new TextureRegionDrawable(buttonB0);
		button1style.imageOver=new TextureRegionDrawable(buttonB1);
		
		button3style.imageUp=new TextureRegionDrawable(buttonC0);
		button3style.imageDown=new TextureRegionDrawable(buttonC0);
		button3style.imageOver=new TextureRegionDrawable(buttonC1);
				
		button4style.imageUp=new SpriteDrawable(returnbutton0);
		button4style.imageDown=new SpriteDrawable(returnbutton0);
		button4style.imageOver=new SpriteDrawable(returnbutton1);
		
		button1=new ImageButton(button1style);
		button2=new ImageButton(button2style);
		button3=new ImageButton(button3style);
		button4=new ImageButton(button4style);
	
	}
	
	@Override
	public void show() {
		fade = 1.0f;
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		
		stage.addActor(button1);
		stage.addActor(button2);
		stage.addActor(button3);
		stage.addActor(button4);
		
		button1.setHeight(81);
		button1.setWidth(238);
		
		button2.setHeight(81);
		button2.setWidth(238);
		
		button3.setHeight(81);
		button3.setWidth(238);
		
		int std=400;
		button2.setPosition(850,300);
		button1.setPosition(850,180);
		button3.setPosition(850,60);
		button4.setPosition(40, 30);
		
		button1.addListener(new InputListener()
		{
			    @Override
		        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.enter(event, x, y, pointer, fromActor);
		            Sound sound = Resources.getInstance().hover;
		            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
		        
		        }
			 
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	           {
	        	   ScreenCenter.setscreen(1);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
						return true;
	           }
		});
		
		button2.addListener(new InputListener()
		{
			@Override
	        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
	            super.enter(event, x, y, pointer, fromActor);
	            Sound sound = Resources.getInstance().hover;
	            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
	        
	        }
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	           {
	        	   ScreenCenter.setscreen(2);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
						return true;
	           }
		});
		
		button3.addListener(new InputListener()
		{
			@Override
	        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
	            super.enter(event, x, y, pointer, fromActor);
	            Sound sound = Resources.getInstance().hover;
	            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
	        
	        }
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	           {
	        	   ScreenCenter.setscreen(3);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
						return true;
	           }
		});
		button4.addListener(new InputListener()
		{
			@Override
	        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
	            super.enter(event, x, y, pointer, fromActor);
	            Sound sound = Resources.getInstance().hover;
	            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
	        
	        }
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	           {
	        	   Gdx.app.exit();
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
	        	   return true;
	           }
		});
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		batch.begin();
		batch.draw(background, 0, 0,1280,720);
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
		batch.dispose();
		stage.dispose();
	}
}
