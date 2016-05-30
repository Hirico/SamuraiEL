package com.samurai.el.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.samurai.el.maingame.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;

public class OverScreen implements Screen
{
	int[][] result;
	int[] place=new int[6];
	int winflag;
	int[] teamScores;
	int planetoccupynum;
	int humanid;
	Stage stage;
	ImageButton returnbutton;
	BitmapFont font;
	LabelStyle labelstyle;
	Label[][] scorelabel=new Label[6][4];
	
	Label Rscore;
	Label Uscore;
	Label Mscore;
	SpriteBatch batch;
	Sprite background;
	Music endMusic;
	Sprite me;
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
	int[] rank=new int[6];
	Sprite[] playername=new Sprite[6];
	public OrthographicCamera camera;
	int[][] result1;
	boolean[] rankflag=new boolean[6];

	int max(int x,int y)
	{
		if (x>y) return x;else return y;
	}
	int min(int x,int y)
	{
		if (x<y) return x;else return y;
	}
	
	void SolveAchieve()
	{
		String[] playername={"Advancer","Tracker","Reaper"};
		
		if(winflag == 1) 
		{
			int temp0=max(teamScores[0],teamScores[1]);
			int temp1=min(teamScores[0],teamScores[1]);
			if (temp0>=(3*temp1)) Gdx.app.getPreferences("challenge").putInteger("highScore", Gdx.app.getPreferences("challenge").getInteger("highScore", 0)+1);
			Gdx.app.getPreferences("challenge").putInteger("winNum", Gdx.app.getPreferences("challenge").getInteger("winNum", 0)+1);
		}
		
		if (result[humanid][0]>Gdx.app.getPreferences("challenge").getInteger(playername[humanid%3], 0))
			Gdx.app.getPreferences("challenge").putInteger(playername[humanid%3],result[humanid][0]);
		
		Gdx.app.getPreferences("challenge").putInteger("killNum", Gdx.app.getPreferences("challenge").getInteger("killNum", 0)+result[humanid][1]);
		
		if (maxnum==humanid)
		{
			Gdx.app.getPreferences("challenge").putInteger("ACE", Gdx.app.getPreferences("challenge").getInteger("ACE", 0)+1);
		}
		if (result[humanid][2]==0) Gdx.app.getPreferences("challenge").putInteger("noDie", Gdx.app.getPreferences("challenge").getInteger("noDie", 0)+1);
		
		Gdx.app.getPreferences("challenge").putInteger("planetOccupy", Gdx.app.getPreferences("challenge").getInteger("planetOccupy", 0)+planetoccupynum);
		

		Gdx.app.getPreferences("challenge").flush();		
	}
	
	public OverScreen(int human,int[][] result,int winflag, Music endMusic, int[] teamScores,int planetoccupynum)
	{
		this.result=result;
		this.winflag=winflag;
		this.endMusic = endMusic;
		this.teamScores = teamScores;
		this.humanid=human;
		this.planetoccupynum=planetoccupynum;
		for (int i=0;i<6;++i) rankflag[i]=false;
		
		for (int i=0;i<6;++i) rank[i]=-1;
		
		for (int i=0;i<6;++i)
		{
			int temp=0;
			int temp1=0;
			for (int j=0;j<6;++j)
			{
				if (!rankflag[j])
				{
					if (result[j][0]>temp)
					{
						temp=result[j][0];
						temp1=j;
					}
				}
			}
			if (rank[temp1]==-1) rank[temp1]=i+1;
			rankflag[temp1]=true;
		}
		
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		camera.position.set(640,360,0);
		
		font=new BitmapFont(Gdx.files.internal("font/achieve.fnt"),Gdx.files.internal("font/achieve.png"), false);
		labelstyle=new LabelStyle(font, font.getColor());
		
		background=new Sprite(new Texture(Gdx.files.internal("img/background/over.png")));
		batch=new SpriteBatch();
		
		republic=new Sprite(new Texture(Gdx.files.internal("img/gameset/republic.png")));
		union=new Sprite(new Texture(Gdx.files.internal("img/gameset/union.png")));
		

		
		Rscore=new Label(Integer.toString(this.teamScores[0]),labelstyle);
		Uscore=new Label(Integer.toString(this.teamScores[1]),labelstyle);
		Mscore=new Label(":",labelstyle);
		
		win=new Sprite(new Texture("img/over/win.png"));
		lose=new Sprite(new Texture("img/over/lose.png"));
		kill=new Sprite(new Texture("img/over/kill.png"));
		me=new Sprite(new Texture("img/over/me.png"));
		
		for (int i=0;i<6;++i)
		{
			playername[i]=new Sprite(new Texture("img/gameset/f"+i+".png"));
		}
		
		
		stage = new Stage(new StretchViewport(1280,720));
		
		
		SolveAchieve();
		
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		blackFade = Resources.getInstance().blackFade;
		
		
		maxmax=0;
		for (int i=0;i<6;++i)
			{
			if (result[i][0]==0) for (int j=0;j<4;++j) scorelabel[i][j]=new Label("-",labelstyle);else
			{
			
			scorelabel[i][0]=new Label(String.valueOf(rank[i]),labelstyle);
			scorelabel[i][1]=new Label(String.valueOf(result[i][1]),labelstyle);
			scorelabel[i][2]=new Label(String.valueOf(result[i][2]),labelstyle);
			scorelabel[i][3]=new Label(String.valueOf(result[i][0]),labelstyle);
			if (result[i][1]>maxmax)
				{
					maxmax=result[i][1];
					maxnum=i;
				}
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
		for (int i=0;i<6;++i) for (int j=0;j<4;++j) stage.addActor(scorelabel[i][j]);
		returnbutton.setPosition(15,10);
		
		win.setPosition(490, 580);
		lose.setPosition(490, 580);
		
		stage.addActor(Rscore);
		stage.addActor(Uscore);
		
		Rscore.setPosition(945, 564);
		Uscore.setPosition(945, 306);
		
		for (int i=0;i<3;++i)
		{
			place[i]=484-i*56;
		}
		for (int i=3;i<6;++i)
		{
			place[i]=230-(i-3)*56;
		}
		
		for (int i=0;i<6;++i)
		{
			if (i==maxnum)
				{
				kill.setPosition(270,place[i]+15);
				
				kill.setSize(kill.getWidth()*0.8f, kill.getHeight()*0.8f);
				}
			if (i==humanid)
			{
				me.setPosition(230,place[i]-8);
				me.setAlpha(0.7f);
			}
		}
		
		
		for (int i=0;i<6;++i) 
		{
			scorelabel[i][0].setX(632);
			scorelabel[i][1].setX(850);
			scorelabel[i][2].setX(923);
			scorelabel[i][3].setX(995);
			
		}
		
		for (int i=0;i<3;++i)
		{
			for (int j=0;j<4;++j)
			scorelabel[i][j].setY(484-i*56);
		}
		for (int i=3;i<6;++i)
		{
			for (int j=0;j<4;++j)
			scorelabel[i][j].setY(230-(i-3)*56);
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

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(background,0,0);
		
		me.draw(batch);
		
		if (winflag==1) win.draw(batch);
		if (winflag==0) lose.draw(batch);
		
		//if (maxmax!=0) kill.draw(batch);
		
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




