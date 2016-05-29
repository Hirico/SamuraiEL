package com.samurai.el.maingame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;

public class ConquerSetScreen implements Screen{
	public Sprite background;
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public Stage sideStage;
	public Stage shipStage;
	public Stage currentStage;
	public BitmapFont font;
	public Label timeset_label;
	public Slider timeset;
	public ImageButton republic;
	public ImageButton union;
	public Sprite republicFont;
	public boolean republicOver;
	public Sprite unionFont;
	public boolean unionOver;
	public ImageButton advancer;
	public ImageButton tracker;
	public ImageButton reaper;
	public int side;
	public int player;
	public int time;
	public int difficulty;
	public Game game;
	public Music bgm;
	
	public ConquerSetScreen(Game game) {
		this.game = game;
		background = new Sprite();
		background.set(Resources.getInstance().selectSide);
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sideStage = new Stage(new StretchViewport(1280,720));
		shipStage = new Stage(new StretchViewport(1280,720));
		currentStage = sideStage;
		batch = new SpriteBatch();
		bgm = Resources.getInstance().bgm09;
		bgm.setLooping(true);
		Preferences volumePref = Gdx.app.getPreferences("volumePref");
		bgm.setVolume(volumePref.getFloat("musicVolume", 0.5f));
		bgm.play();
		
		republicFont = Resources.getInstance().republicFont;
		unionFont = Resources.getInstance().unionFont;
	    republicFont.setPosition(300, 200);
	    unionFont.setPosition(740, 200);
		
		ImageButton.ImageButtonStyle republicstyle=new ImageButton.ImageButtonStyle();
		ImageButton.ImageButtonStyle unionstyle=new ImageButton.ImageButtonStyle();
		
		republicstyle.imageUp=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/republicP.png"))));	
		republicstyle.imageDown=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/republicP.png"))));
		republicstyle.imageOver=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/republicP.png"))));
		republic = new ImageButton(republicstyle);
		republic.setPosition(400, 300);
		republic.addListener(new InputListener()
		{
			    @Override
		        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.enter(event, x, y, pointer, fromActor);
		            Sound sound = Resources.getInstance().hover;
		            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
		            republicOver = true;
		        }
			    
			    @Override
		        public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.exit(event, x, y, pointer, fromActor);
		            republicOver = false;
		        }
			    
			 
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	           {
	        	   side = 0;	        	
	        	   characterSelectInitialize();
	        	   republicOver = false;
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
						return true;
	           }
		});	
		
		
		unionstyle.imageUp=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/unionP.png"))));	
		unionstyle.imageDown=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/unionP.png"))));
		unionstyle.imageOver=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/unionP.png"))));
		union = new ImageButton(unionstyle);
		union.setPosition(800, 300);
		union.addListener(new InputListener()
		{
			    @Override
		        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.enter(event, x, y, pointer, fromActor);
		            Sound sound = Resources.getInstance().hover;
		            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
		            unionOver = true;
		        }
			    
			    @Override
		        public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.exit(event, x, y, pointer, fromActor);
		            unionOver = false;
		        }
			 
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	           {
	        	   side = 1;
	        	   characterSelectInitialize();
	        	   unionOver = false;
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
						return true;
	           }
		});
		
		sideStage.addActor(republic);
		sideStage.addActor(union);
		
		//time slider and time label
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);
		Sprite bar=new Sprite(new Texture("img/setting/barA.png"));
		Sprite tt=new Sprite(new Texture("img/setting/tA.png"));
		Slider.SliderStyle timestyle=new Slider.SliderStyle(new SpriteDrawable(bar),new SpriteDrawable(tt)); 
		timeset=new Slider(200,3000,1,false,timestyle);
		
		Label.LabelStyle labelstyle =new Label.LabelStyle(font, Color.WHITE);		
		timeset_label=new Label("90S",labelstyle);
		shipStage.addActor(timeset);
		timeset.setBounds(640-700/2, 90,700, 35);
		timeset.setValue(900);
		shipStage.addActor(timeset_label);
		timeset_label.setPosition(1000, 90);
		timeset.addListener(new ChangeListener()
		{
			 public void changed (ChangeEvent event, Actor actor) 
			 {
				 int timetime=(int) (timeset.getValue()/10);
				 
				 timeset_label.setText(timetime+"s");
			 }
		});
				
	}
	
	/**after select the side */
	public void characterSelectInitialize() {
		
		//change image
		background.set(Resources.getInstance().selectShip);
 	    background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if(side == 0) {
			ImageButton.ImageButtonStyle advancerstyle=new ImageButton.ImageButtonStyle();
			ImageButton.ImageButtonStyle trackerstyle=new ImageButton.ImageButtonStyle();
			ImageButton.ImageButtonStyle reaperstyle=new ImageButton.ImageButtonStyle();
			
			advancerstyle.imageUp=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/r0.png"))));	
			advancerstyle.imageDown=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h0.png"))));
			advancerstyle.imageOver=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h0.png"))));
			advancer = new ImageButton(advancerstyle);
			advancer.setPosition(395, 320);
			advancer.addListener(new InputListener()
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
		        	   player = 0;
		        	   GameInstance.setInstance(player, 2, (int) (timeset.getValue()/10));
		        	   bgm.stop();
		        	   bgm.dispose();
		        	   game.setScreen(new GameScreen(game));
		           }
		           @Override
		           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
		           {
							return true;
		           }
			});	
			
			trackerstyle.imageUp=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/r1.png"))));	
			trackerstyle.imageDown=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h1.png"))));
			trackerstyle.imageOver=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h1.png"))));
			tracker = new ImageButton(trackerstyle);
			tracker.setPosition(595, 320);
			tracker.addListener(new InputListener()
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
		        	   player = 1;
		        	   GameInstance.setInstance(player, 2, (int) (timeset.getValue()/10));
		        	   bgm.stop();
		        	   bgm.dispose();
		        	   game.setScreen(new GameScreen(game));
		           }
		           @Override
		           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
		           {
							return true;
		           }
			});	
			
			reaperstyle.imageUp=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/r2.png"))));	
			reaperstyle.imageDown=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h2.png"))));
			reaperstyle.imageOver=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h2.png"))));
			reaper = new ImageButton(reaperstyle);
			reaper.setPosition(795, 320);
			reaper.addListener(new InputListener()
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
		        	   player = 2;
		        	   GameInstance.setInstance(player, 2, (int) (timeset.getValue()/10));
		        	   bgm.stop();
		        	   bgm.dispose();
		        	   game.setScreen(new GameScreen(game));
		           }
		           @Override
		           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
		           {
							return true;
		           }
			});	
		} else {
			ImageButton.ImageButtonStyle advancerstyle=new ImageButton.ImageButtonStyle();
			ImageButton.ImageButtonStyle trackerstyle=new ImageButton.ImageButtonStyle();
			ImageButton.ImageButtonStyle reaperstyle=new ImageButton.ImageButtonStyle();
			
			advancerstyle.imageUp=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/r3.png"))));	
			advancerstyle.imageDown=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h3.png"))));
			advancerstyle.imageOver=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h3.png"))));
			advancer = new ImageButton(advancerstyle);
			advancer.setPosition(395, 320);
			advancer.addListener(new InputListener()
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
		        	   player = 3;
		        	   GameInstance.setInstance(player, 2, (int) (timeset.getValue()/10));
		        	   bgm.stop();
		        	   bgm.dispose();
		        	   game.setScreen(new GameScreen(game));
		           }
		           @Override
		           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
		           {
							return true;
		           }
			});	
			
			trackerstyle.imageUp=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/r4.png"))));	
			trackerstyle.imageDown=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h4.png"))));
			trackerstyle.imageOver=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h4.png"))));
			tracker = new ImageButton(trackerstyle);
			tracker.setPosition(595, 320);
			tracker.addListener(new InputListener()
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
		        	   player = 4;
		        	   GameInstance.setInstance(player, 2, (int) (timeset.getValue()/10));
		        	   bgm.stop();
		        	   bgm.dispose();
		        	   game.setScreen(new GameScreen(game));
		           }
		           @Override
		           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
		           {
							return true;
		           }
			});	
			
			reaperstyle.imageUp=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/r5.png"))));	
			reaperstyle.imageDown=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h5.png"))));
			reaperstyle.imageOver=new TextureRegionDrawable(new Sprite(new Texture(Gdx.files.internal("img/gameset/h5.png"))));
			reaper = new ImageButton(reaperstyle);
			reaper.setPosition(795, 320);
			reaper.addListener(new InputListener()
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
		        	   player = 5;
		        	   GameInstance.setInstance(player, 2, (int) (timeset.getValue()/10));
		        	   bgm.stop();
		        	   bgm.dispose();
		        	   game.setScreen(new GameScreen(game));
		           }
		           @Override
		           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
		           {
							return true;
		           }
			});	
		}
		
		//change current stage
		shipStage.addActor(advancer);
		shipStage.addActor(tracker);
		shipStage.addActor(reaper);
		currentStage = shipStage;
		Gdx.input.setInputProcessor(currentStage);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(sideStage);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			bgm.stop();
     	   bgm.dispose();
     	   ScreenCenter.startmusic();
     	   ScreenCenter.setscreen(0);
		}
		
		batch.begin();
		background.draw(batch);
		if(republicOver) {
			republicFont.draw(batch);
		}
		if(unionOver) {
			unionFont.draw(batch);
		}
		batch.end();
		
		currentStage.act();
		currentStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		sideStage.dispose();
		shipStage.dispose();
	}

}
