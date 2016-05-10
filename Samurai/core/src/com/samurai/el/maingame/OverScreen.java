package com.samurai.el.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.mainmenu.ScreenCenter;

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
	
	
	public OverScreen(int[][] result,int winflag)
	{
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);
		labelstyle=new LabelStyle(font, font.getColor());
		
		background=new Sprite(new Texture(Gdx.files.internal("foxwel_temp/photo.jpg")));
		batch=new SpriteBatch();
		
		this.result=result;
		this.winflag=winflag;
		stage = new Stage(new StretchViewport(1280,720));
		
		for (int i=0;i<6;++i) scorelabel[i]=new Label(String.valueOf(result[i][0]),labelstyle);
		
		Sprite returnbutton0=new Sprite(new Texture("foxwel_temp/return0.png"));
		Sprite returnbutton1=new Sprite(new Texture("foxwel_temp/return1.png"));
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbutton0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbutton1);
		
		returnbutton=new ImageButton(returnbuttonstyle);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		ScreenCenter.startmusic();
		stage.addActor(returnbutton);
		for (int i=0;i<6;++i) stage.addActor(scorelabel[i]);
		returnbutton.setPosition(1280-128, 0);
		for (int i=0;i<6;++i)
		{
			scorelabel[i].setY(360);
			scorelabel[i].setX(100+100*i);
		}
		
		returnbutton.addListener(new InputListener(){
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
		batch.dispose();
		stage.dispose();
		font.dispose();
	}

}




