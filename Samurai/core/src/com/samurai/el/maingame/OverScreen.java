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
	int[] teamScores;
	Stage stage;
	ImageButton returnbutton;
	BitmapFont font;
	LabelStyle labelstyle;
	Label[] scorelabel=new Label[6];
	Label Rscore;
	Label Uscore;
	Label Mscore;
	SpriteBatch batch;
	Sprite background;
	Music endMusic;
	
	Sprite blackFade;
	Sprite republic;
	Sprite union;
	Sprite win;
	Sprite lose;
	Sprite kill;
	SpriteBatch fadeBatch;
	public float fade;
	int maxnum;
	int maxmax;
	Sprite[] playername=new Sprite[6];
	
	public OverScreen(int[][] result,int winflag, Music endMusic, int[] teamScores)
	{
		this.result=result;
		this.winflag=winflag;
		this.endMusic = endMusic;
		this.teamScores = teamScores;
		
		font=new BitmapFont(Gdx.files.internal("font/over1.fnt"),Gdx.files.internal("font/over1.png"), false);
		labelstyle=new LabelStyle(font, font.getColor());
		
		background=new Sprite(new Texture(Gdx.files.internal("img/background/over.jpg")));
		batch=new SpriteBatch();
		
		republic=new Sprite(new Texture(Gdx.files.internal("img/gameset/republic.png")));
		union=new Sprite(new Texture(Gdx.files.internal("img/gameset/union.png")));
		

		
		Rscore=new Label(Integer.toString(this.teamScores[0]),labelstyle);
		Uscore=new Label(Integer.toString(this.teamScores[1]),labelstyle);
		Mscore=new Label(":",labelstyle);
		
		win=new Sprite(new Texture("img/over/win.png"));
		lose=new Sprite(new Texture("img/over/lose.png"));
		kill=new Sprite(new Texture("img/over/kill.png"));
		
		for (int i=0;i<6;++i)
		{
			playername[i]=new Sprite(new Texture("img/gameset/f"+i+".png"));
		}
		
		
		stage = new Stage(new StretchViewport(1280,720));
		
		if(winflag == 1) {
			Gdx.app.getPreferences("challenge").putInteger("winNum", Gdx.app.getPreferences("challenge").getInteger("winNum", 0)+1);
			Gdx.app.getPreferences("challenge").flush();
		}
		
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		blackFade = Resources.getInstance().blackFade;
		
		
		maxmax=0;
		for (int i=0;i<6;++i)
			{
			scorelabel[i]=new Label(String.valueOf(result[i][0]+" / "+result[i][1]+" / "+result[i][2]),labelstyle);
			if (result[i][1]>maxmax)
			{
				maxmax=result[i][1];
				maxnum=i;
			}
			}
		
		
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
		
		republic.setPosition(220, 620);
		union.setPosition(780, 620);		
		republic.setSize(republic.getWidth()*1.1f, republic.getHeight()*1.1f);
		union.setSize(union.getWidth()*1.1f, union.getHeight()*1.1f);
		
		win.setPosition(490, 550);
		lose.setPosition(490, 530);
		//win.setAlpha(0.6f);
		
		stage.addActor(Rscore);
		stage.addActor(Uscore);
		//stage.addActor(Mscore);
		
		Rscore.setPosition(350, 540);
		Uscore.setPosition(930, 540);
		Mscore.setPosition(640, 540);
		Mscore.setScale(1.4f, 1.4f);
		
		for (int i=0;i<3;++i)
		{
			
			playername[i].setPosition(300,460-130*(i));
			if (i==maxnum) kill.setPosition(300-80,460-130*(i)-20);
		}
		
		for (int i=3;i<6;++i)
		{
			playername[i].setPosition(877,460-130*(i-3));
			if (i==maxnum) kill.setPosition(877-80,460-130*(i-3)-20);
		}
		
		for (int i=0;i<3;++i)
		{
			scorelabel[i].setY(385-i*130);
			scorelabel[i].setX(240);
		}
		for (int i=3;i<6;++i)
		{
			scorelabel[i].setY(385-(i-3)*130);
			scorelabel[i].setX(810);
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
		
		republic.draw(batch);
		union.draw(batch);
		if (winflag==1) win.draw(batch);
		if (winflag==0) lose.draw(batch);
		for (int i=0;i<6;++i) playername[i].draw(batch);
		if (maxmax!=0) kill.draw(batch);
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




