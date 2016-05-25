package com.samurai.el.gamesetting;


import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;



public class MapSetScreen implements Screen{

	Stage stage;

	ImageButton returnbutton;
	ImageButton enterbutton;
	
	BitmapFont font;


	CheckBox mode0;
	CheckBox mode1;

	SpriteBatch batch;
	Sprite background;
	
	ImageButton leftbutton;
	ImageButton rightbutton;
	
		
	Sprite[] mappic=new Sprite[4];
	int mapid;
	
	Sprite blackFade;
	SpriteBatch fadeBatch;
	public float fade;
	public OrthographicCamera camera;
	
	void rightone()
	{
		mapid+=1;
		if (mapid==4) mapid=0;
		background=mappic[mapid];
	}
	
	void leftone()
	{
		mapid-=1;
		if (mapid==-1) mapid=3;
		background=mappic[mapid];
	}
	
	public MapSetScreen() {
		

		
		mapid=0;
		
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		blackFade = Resources.getInstance().blackFade;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		camera.position.set(640,360,0);
	
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);
		// TODO Auto-generated method stub
		
		batch=new SpriteBatch();
		
		for (int i=0;i<4;++i) mappic[i]=new Sprite(new Texture(Gdx.files.internal("img/gameset/map"+i+".png")));
		
		background=mappic[0];
		
		background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		stage = new Stage(new StretchViewport(1280,720));
		

		
		Sprite left0=new Sprite(new Texture("img/gameset/left0.png"));
		Sprite left1=new Sprite(new Texture("img/gameset/left1.png"));
		ImageButton.ImageButtonStyle leftbuttonstyle=new ImageButton.ImageButtonStyle();
		
		leftbuttonstyle.imageUp=new SpriteDrawable(left0);
		leftbuttonstyle.imageDown=new SpriteDrawable(left0);
		leftbuttonstyle.imageOver=new SpriteDrawable(left1);
		
		leftbutton=new ImageButton(leftbuttonstyle);
		
		
		
		Sprite right0=new Sprite(new Texture("img/gameset/right0.png"));
		Sprite right1=new Sprite(new Texture("img/gameset/right1.png"));
		ImageButton.ImageButtonStyle rightbuttonstyle=new ImageButton.ImageButtonStyle();
		
		rightbuttonstyle.imageUp=new SpriteDrawable(right0);
		rightbuttonstyle.imageDown=new SpriteDrawable(right0);
		rightbuttonstyle.imageOver=new SpriteDrawable(right1);
		
		rightbutton=new ImageButton(rightbuttonstyle);
		
		
		
		Sprite returnbuttonp0=new Sprite(new Texture("img/button/mainmenu/return0.png"));
		Sprite returnbuttonp1=new Sprite(new Texture("img/button/mainmenu/return1.png"));
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbuttonp0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbuttonp0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbuttonp1);
		
		returnbutton=new ImageButton(returnbuttonstyle);
		
		
		
		
		Sprite enterbuttonp0=new Sprite(new Texture("img/button/go0.png"));
		Sprite enterbuttonp1=new Sprite(new Texture("img/button/go1.png"));
		ImageButton.ImageButtonStyle enterbuttonstyle=new ImageButton.ImageButtonStyle();
		
		enterbuttonstyle.imageUp=new SpriteDrawable(enterbuttonp0);
		enterbuttonstyle.imageDown=new SpriteDrawable(enterbuttonp0);
		enterbuttonstyle.imageOver=new SpriteDrawable(enterbuttonp1);
		
		enterbutton=new ImageButton(enterbuttonstyle);
		
		
		Sprite check0=new Sprite(new Texture("img/gameset/mode0.png"));
		Sprite check1=new Sprite(new Texture("img/gameset/mode1.png"));
		
		CheckBox.CheckBoxStyle checkstyle=new CheckBox.CheckBoxStyle();
		checkstyle.checkboxOver=new SpriteDrawable(check1);
		checkstyle.checkboxOn=new SpriteDrawable(check1);
		checkstyle.checkboxOff=new SpriteDrawable(check0);
		checkstyle.font=font;
		checkstyle.fontColor=new Color(Color.YELLOW);
		mode0=new CheckBox("",checkstyle);
		mode1=new CheckBox("",checkstyle);
		
	}
		

	
	@Override
	public void show() {
		fade = 1.0f;
		Gdx.input.setInputProcessor(stage);
		
		stage.addActor(returnbutton);
		returnbutton.setPosition(30, 8);
		
		stage.addActor(enterbutton);
		enterbutton.setPosition(1020, 8);

		stage.addActor(leftbutton);
		stage.addActor(rightbutton);
		leftbutton.setPosition(100, 328);
		rightbutton.setPosition(1135, 328);
		
		stage.addActor(mode0);
		stage.addActor(mode1);
		
		mode0.setPosition(570,52);
		mode1.setPosition(690,52);
		mode0.setChecked(true);
		
			
		mode0.addListener(new InputListener()
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
				mode1.setChecked(false);
			}
           @Override
           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
           {
					return true;
           }
		});
		
		mode1.addListener(new InputListener()
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
				mode0.setChecked(false);
			}
           @Override
           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
           {
					return true;
           }
		});
		
		leftbutton.addListener(new InputListener(){
			 @Override
		        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.enter(event, x, y, pointer, fromActor);
		            Sound sound = Resources.getInstance().hover;
		            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
		        
		        }
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   leftone();
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
	        	 

						return true;
	           }
		});
		
		rightbutton.addListener(new InputListener(){
			 @Override
		        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.enter(event, x, y, pointer, fromActor);
		            Sound sound = Resources.getInstance().hover;
		            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
		        
		        }
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   rightone();
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {
	        	 

						return true;
	           }
		});
		
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
		
		enterbutton.addListener(new InputListener(){
			 @Override
		        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.enter(event, x, y, pointer, fromActor);
		            Sound sound = Resources.getInstance().hover;
		            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
		        
		        }
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   
	        	   if (mode0.isChecked()) ScreenCenter.setgamesetscreen(mapid,0);else ScreenCenter.setgamesetscreen(mapid,1);
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
		//Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.draw(batch);


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
	}

}