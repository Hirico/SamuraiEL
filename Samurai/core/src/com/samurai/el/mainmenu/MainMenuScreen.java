package com.samurai.el.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.maingame.OverScreen;
import com.samurai.el.resource.Resources;


public class MainMenuScreen implements Screen{
	Stage stage;
	Stage stage1;
	boolean Flag;
	
	ImageButton button1;
	ImageButton button2;
	ImageButton button3;
	ImageButton button4;
	ImageButton button5;
	
	ImageButton returnbutton;
	ImageButton stdbutton;
	ImageButton conquerbutton;
	
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
	
	public Array<ParticleEffect> explosions;
	public SpriteBatch explosionBatch;
	public float explosionInterval;
	public ParticleEffect currentParticle;
	
	public MainMenuScreen()
	{
		Flag=false;
		stage=new Stage(new StretchViewport(1280,720));
		stage1=new Stage(new StretchViewport(1280,720));
		
		explosions = new Array<ParticleEffect>();
		explosionBatch = new SpriteBatch();
		currentParticle = null;
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
		
		
		Sprite exitbutton0=new Sprite(new Texture("img/button/mainmenu/exit0.png"));
		Sprite exitbutton1=new Sprite(new Texture("img/button/mainmenu/exit1.png"));
		
		Sprite helpbutton0=new Sprite(new Texture("img/button/mainmenu/help0.png"));
		Sprite helpbutton1=new Sprite(new Texture("img/button/mainmenu/help1.png"));
		
		Sprite returnbutton0=new Sprite(new Texture("img/button/mainmenu/return0.png"));
		Sprite returnbutton1=new Sprite(new Texture("img/button/mainmenu/return1.png"));
		
		Sprite stdbutton0=new Sprite(new Texture("img/button/mainmenu/standard0.png"));
		Sprite stdbutton1=new Sprite(new Texture("img/button/mainmenu/standard1.png"));
		
		Sprite conquerbutton0=new Sprite(new Texture("img/button/mainmenu/conquer0.png"));
		Sprite conquerbutton1=new Sprite(new Texture("img/button/mainmenu/conquer1.png"));
		
		ImageButton.ImageButtonStyle button1style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button2style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button3style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button4style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle button5style=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle stdbuttonstyle=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle conquerbuttonstyle=new ImageButton.ImageButtonStyle();
		
		button2style.imageUp=new TextureRegionDrawable(buttonA0);	
		button2style.imageDown=new TextureRegionDrawable(buttonA0);
		button2style.imageOver=new TextureRegionDrawable(buttonA1);
		
		button1style.imageUp=new TextureRegionDrawable(buttonB0);
		button1style.imageDown=new TextureRegionDrawable(buttonB0);
		button1style.imageOver=new TextureRegionDrawable(buttonB1);
		
		button3style.imageUp=new TextureRegionDrawable(buttonC0);
		button3style.imageDown=new TextureRegionDrawable(buttonC0);
		button3style.imageOver=new TextureRegionDrawable(buttonC1);
				
		button4style.imageUp=new SpriteDrawable(exitbutton0);
		button4style.imageDown=new SpriteDrawable(exitbutton0);
		button4style.imageOver=new SpriteDrawable(exitbutton1);
		
		button5style.imageUp=new SpriteDrawable(helpbutton0);
		button5style.imageDown=new SpriteDrawable(helpbutton0);
		button5style.imageOver=new SpriteDrawable(helpbutton1);
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbutton1);
		
		stdbuttonstyle.imageUp=new SpriteDrawable(stdbutton0);
		stdbuttonstyle.imageDown=new SpriteDrawable(stdbutton0);
		stdbuttonstyle.imageOver=new SpriteDrawable(stdbutton1);
		
		conquerbuttonstyle.imageUp=new SpriteDrawable(conquerbutton0);
		conquerbuttonstyle.imageDown=new SpriteDrawable(conquerbutton0);
		conquerbuttonstyle.imageOver=new SpriteDrawable(conquerbutton1);
		
		button1=new ImageButton(button1style);
		button2=new ImageButton(button2style);
		button3=new ImageButton(button3style);
		button4=new ImageButton(button4style);
		button5=new ImageButton(button5style);
		
		returnbutton=new ImageButton(returnbuttonstyle);
		stdbutton=new ImageButton(stdbuttonstyle);
		conquerbutton=new ImageButton(conquerbuttonstyle);
		
			
	}
	
	@Override
	public void show() {
		fade = 1.0f;
		explosionInterval = 0f;
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		if(Gdx.app.getType() == ApplicationType.Android) {
			Resources.getInstance().reInit();
		}
		
		stage.addActor(button1);
		stage.addActor(button2);
		stage.addActor(button3);
		stage.addActor(button4);
		stage.addActor(button5);
		
		stage1.addActor(returnbutton);
		stage1.addActor(stdbutton);
		stage1.addActor(conquerbutton);
		
		stdbutton.setPosition(850,380);
		conquerbutton.setPosition(850,300);
		returnbutton.setPosition(850,220);
		
		int std=400;
		button2.setPosition(850,380);
		button1.setPosition(850,300);
		button3.setPosition(850,220);
		button5.setPosition(850,140);
		button4.setPosition(850,60);
		
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
	        	  Flag=true;
	        	  Gdx.input.setInputProcessor(stage1);
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
	       			/*
	        	   int[][] result={{12,30,0,1},{88,0,40,2},{77,2,2,3},{0,0,3,4},{35,2,1,5},{20,4,1,6}};
	       			int[] score={200,233};
	    			ScreenCenter.game.setScreen(new OverScreen(5,result,0,music,score,0));
	    			*/
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
	        	   return true;
	           }
		});
		button5.addListener(new InputListener()
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
	        	   if(Gdx.app.getType() == ApplicationType.Desktop) {	
	        		   ScreenCenter.setscreen(5);
	        	   }
	        	   else if(Gdx.app.getType() == ApplicationType.Android) {
	        		   ScreenCenter.setscreen(6);
	        	   }
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
	        	   return true;
	           }
		});
		
		stdbutton.addListener(new InputListener()
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
		
		conquerbutton.addListener(new InputListener()
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
	        		  ScreenCenter.setscreen(7);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
	        	   return true;
	           }
		});
		
		returnbutton.addListener(new InputListener()
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
	        	   Gdx.input.setInputProcessor(stage);
	        		Flag=false;
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
		batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.end();
		if (Flag==false)
		{
		stage.act();
		stage.draw();
		}
		else
		{
			stage1.act();
			stage1.draw();
		}
		
		if(explosionInterval > 0) {
			explosionInterval -= delta;
		} else {			
			currentParticle = explosionSpawner();
			explosionInterval = 0.3f + 1.7f * (float)Math.random();			
		}
		if(currentParticle != null) {
			explosionBatch.begin();
			currentParticle.draw(explosionBatch, delta);
			currentParticle.update(delta);
			explosionBatch.end();
		}
		
		if (fade > 0) {
			fade = Math.max(fade - Gdx.graphics.getDeltaTime() / 2.f, 0);
			fadeBatch.begin();
			blackFade.setColor(blackFade.getColor().r, blackFade.getColor().g, blackFade.getColor().b, fade);
			blackFade.draw(fadeBatch);
			fadeBatch.end();
		}
		
		
	}
	
	public ParticleEffect explosionSpawner() {
		ParticleEffect particle;
		if(explosions.size != 0) {
			particle = explosions.pop();			
			particle.reset();			
		} else {
			particle = new ParticleEffect();
			particle.load(Gdx.files.internal("img/field/explosion.p"), Gdx.files.internal("img/field"));
			particle.scaleEffect(0.4f);
		}
		
		explosions.add(particle);
		
		particle.setPosition(1280*(float)Math.random(), 720*(float)Math.random());
		return particle;
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
		stage1.dispose();
		fadeBatch.dispose();
		for(ParticleEffect p: explosions) {
			p.dispose();
		}
	}
}
