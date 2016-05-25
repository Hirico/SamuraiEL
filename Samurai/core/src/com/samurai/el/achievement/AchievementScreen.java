
package com.samurai.el.achievement;

import java.util.ArrayList;

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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;

public class AchievementScreen implements Screen{

	Stage stage;
	ImageButton returnbutton;
	Screen mainmenuscreen;
	
	Sprite background;
	SpriteBatch batch;
	
	Sprite blackFade;
	SpriteBatch fadeBatch;
	public float fade;
	
	ArrayList<Integer> achievelist= new ArrayList(); 
	
	Sprite[] cup0=new Sprite[10];
	Sprite[] cup1=new Sprite[10];
	
	BitmapFont font;
	Label wintime;
	
	
	public void calcachieve()
	{
		if (Gdx.app.getPreferences("challenge").getInteger("winNum", 0)>20) achievelist.add(0);
		
		String[] playername={"Advancer","Tracker","Reaper"};
		
		if (Gdx.app.getPreferences("challenge").getInteger("ACE", 0)>20) achievelist.add(1);
		
		if (Gdx.app.getPreferences("challenge").getInteger("killNum",0)>300) achievelist.add(2);
		
		for (int i=0;i<3;++i) if (Gdx.app.getPreferences("challenge").getInteger(playername[i],0)>30) achievelist.add(i+3);
		for (int i=0;i<achievelist.size();++i) System.out.println(achievelist.get(i));
	}
	
	public AchievementScreen() {
		stage = new Stage(new StretchViewport(1280,720));
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);
		background = new Sprite(new Texture(Gdx.files.internal("img/background/achieve.png")));
		batch = new SpriteBatch();
		background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		fadeBatch = new SpriteBatch();		
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		blackFade = Resources.getInstance().blackFade;
		
		Sprite returnbutton0=new Sprite(new Texture("img/button/mainmenu/return0.png"));
		Sprite returnbutton1=new Sprite(new Texture("img/button/mainmenu/return1.png"));
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbutton1);
		
		returnbutton=new ImageButton(returnbuttonstyle);
		
		calcachieve();
		
		
		for (int i=0;i<10;++i) cup0[i]=new Sprite(new Texture("img/achieve/not"+i+".png"));
		for (int i=0;i<10;++i) cup1[i]=new Sprite(new Texture("img/achieve/is"+i+".png"));
		
		
	}
	
	@Override
	public void show() {
		fade = 1.0f;
		
		Gdx.input.setInputProcessor(stage);
		stage.addActor(returnbutton);
		returnbutton.setPosition(40,30);

		
		cup0[0].setPosition(715,400);
		cup0[1].setPosition(720,300);
		cup0[2].setPosition(900,440);
		cup0[3].setPosition(900,340);
		cup0[4].setPosition(900,240);
		cup0[5].setPosition(1090,430);
		cup0[6].setPosition(440,340);
		cup0[7].setPosition(550,340);
		cup0[8].setPosition(500,100);
		cup0[9].setPosition(200,140);
		
		
		for (int i=0;i<10;++i)
		{
			cup1[i].setPosition(cup0[i].getX(),cup0[i].getY());
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
		background.draw(batch);
		for (int i=0;i<10;++i) cup0[i].draw(batch);
		for (int i=0;i<achievelist.size();++i) cup1[achievelist.get(i)].draw(batch);
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

