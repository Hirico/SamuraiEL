package com.samurai.el.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.samurai.el.maingame.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;

public class OverScreen implements Screen
{
	int[][] result;
	int winflag;
	Stage stage;
	ImageButton returnbutton;
	BitmapFont font;
	LabelStyle labelstyle;
	Label[] scorelabel=new Label[6];
	SpriteBatch batch;
	Sprite background;
	Music endMusic;
	
	Sprite blackFade;
	Sprite republic;
	Sprite union;
	SpriteBatch fadeBatch;
	public float fade;
	
	
	public OverScreen(int[][] result,int winflag, Music endMusic)
	{
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);
		labelstyle=new LabelStyle(font, font.getColor());
		
		background=new Sprite(new Texture(Gdx.files.internal("img/background/over.png")));
		batch=new SpriteBatch();
		
		republic=new Sprite(new Texture(Gdx.files.internal("img/gameset/republic.png")));
		union=new Sprite(new Texture(Gdx.files.internal("img/gameset/union.png")));
		
		
		this.result=result;
		this.winflag=winflag;
		this.endMusic = endMusic;
		stage = new Stage(new StretchViewport(1280,720));
		
		if(winflag == 1) {
			Gdx.app.getPreferences("challenge").putInteger("winNum", Gdx.app.getPreferences("challenge").getInteger("winNum", 0)+1);
		}
		
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		blackFade = Resources.getInstance().blackFade;
		
		for (int i=0;i<6;++i) scorelabel[i]=new Label(String.valueOf(result[i][0]+"/"+result[i][1]+"/"+result[i][2]),labelstyle);
		
		
		Sprite returnbutton0=new Sprite(new Texture("img/button/mainmenu/return0.png"));
		Sprite returnbutton1=new Sprite(new Texture("img/button/mainmenu/return1.png"));
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbutton1);
		
		returnbutton=new ImageButton(returnbuttonstyle);
	}
	
	@Override
	public void show() {
		Timer.clearInstances();
		System.gc();
		fade = 1.0f;
		Gdx.input.setInputProcessor(stage);
		stage.addActor(returnbutton);
		for (int i=0;i<6;++i) stage.addActor(scorelabel[i]);
		returnbutton.setPosition(40,30);
		for (int i=0;i<3;++i)
		{
			scorelabel[i].setY(500-i*120);
			scorelabel[i].setX(200);
		}
		for (int i=3;i<6;++i)
		{
			scorelabel[i].setY(500-(i-3)*120);
			scorelabel[i].setX(700);
		}
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
	        	   ScreenCenter.startmusic();
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
		
		batch.begin();
		batch.draw(background,0,0);
		batch.draw(republic,300,640);
		batch.draw(union,800,640);
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
		font.dispose();
		endMusic.dispose();
		fadeBatch.dispose();
	}

}




