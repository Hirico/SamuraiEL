package com.samurai.el.gamesetting;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.mainmenu.ScreenCenter;
import com.samurai.el.resource.Resources;



public class GameSetScreen implements Screen{

	Stage stage;

	ImageButton returnbutton;
	ImageButton enterbutton;
	BitmapFont font;
	Music music;
	CheckBox[] fieldcheckbox=new CheckBox[4];
	CheckBox[][] playercheckbox=new CheckBox[6][5];
	
	SpriteBatch batch;
	Sprite background;
	
	public GameSetScreen() {
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);
		// TODO Auto-generated method stub
		
		batch=new SpriteBatch();
		background=new Sprite(new Texture(Gdx.files.internal("foxwel_temp/setphoto.jpg")));
		
		stage = new Stage(new StretchViewport(1280,720));
		
		
		
		Sprite returnbuttonp0=new Sprite(new Texture("foxwel_temp/return0.png"));
		Sprite returnbuttonp1=new Sprite(new Texture("foxwel_temp/return1.png"));
		ImageButton.ImageButtonStyle returnbuttonstyle=new ImageButton.ImageButtonStyle();
		
		returnbuttonstyle.imageUp=new SpriteDrawable(returnbuttonp0);
		returnbuttonstyle.imageDown=new SpriteDrawable(returnbuttonp0);
		returnbuttonstyle.imageOver=new SpriteDrawable(returnbuttonp1);
		
		returnbutton=new ImageButton(returnbuttonstyle);
		
		
		
		
		Sprite enterbuttonp0=new Sprite(new Texture("foxwel_temp/choose/enter0.png"));
		Sprite enterbuttonp1=new Sprite(new Texture("foxwel_temp/choose/enter1.png"));
		ImageButton.ImageButtonStyle enterbuttonstyle=new ImageButton.ImageButtonStyle();
		
		enterbuttonstyle.imageUp=new SpriteDrawable(enterbuttonp0);
		enterbuttonstyle.imageDown=new SpriteDrawable(enterbuttonp0);
		enterbuttonstyle.imageOver=new SpriteDrawable(enterbuttonp1);
		
		enterbutton=new ImageButton(enterbuttonstyle);
		
		
		
		
		Sprite check0=new Sprite(new Texture("img/setting/check0.png"));
		Sprite check1=new Sprite(new Texture("img/setting/check1.png"));
		Sprite check2=new Sprite(new Texture("img/setting/check2.png"));

		
		CheckBox.CheckBoxStyle checkstyle=new CheckBox.CheckBoxStyle();
		checkstyle.checkboxOver=new SpriteDrawable(check1);
		checkstyle.checkboxOn=new SpriteDrawable(check2);
		checkstyle.checkboxOff=new SpriteDrawable(check0);
		checkstyle.font=font;
		checkstyle.fontColor=new Color(Color.YELLOW);
	
		fieldcheckbox[0]=new CheckBox("  map0",checkstyle);
		fieldcheckbox[1]=new CheckBox("  map1",checkstyle);
		fieldcheckbox[2]=new CheckBox("  map2",checkstyle);
		fieldcheckbox[3]=new CheckBox("  map3",checkstyle);

		playercheckbox[0][0]=new CheckBox("  redSpear",checkstyle);
		playercheckbox[1][0]=new CheckBox("  redSword",checkstyle);
		playercheckbox[2][0]=new CheckBox("  redAxe",checkstyle);
		playercheckbox[3][0]=new CheckBox("  blueSpear",checkstyle);
		playercheckbox[4][0]=new CheckBox("  blueSword",checkstyle);
		playercheckbox[5][0]=new CheckBox("  blueAxe",checkstyle);
		
		for (int i=0;i<6;++i) playercheckbox[i][1]=new CheckBox("  Me!",checkstyle);
		for (int i=0;i<6;++i) playercheckbox[i][2]=new CheckBox("  Simple AI!",checkstyle);
		for (int i=0;i<6;++i) playercheckbox[i][3]=new CheckBox("  Normal AI!",checkstyle);
		for (int i=0;i<6;++i) playercheckbox[i][4]=new CheckBox("  Hard AI!",checkstyle);
	}
	
	@Override
	public void show() {
		
		Gdx.input.setInputProcessor(stage);
		
		stage.addActor(returnbutton);
		returnbutton.setPosition(1280-128, 0);
		
		stage.addActor(enterbutton);
		enterbutton.setPosition(720-enterbutton.getWidth()-50, 20);
		
		
		int filedstartX=120;
		for (int i=0;i<4;++i)
		{
			stage.addActor(fieldcheckbox[i]);
			fieldcheckbox[i].setY(650);
			fieldcheckbox[i].setX(filedstartX+i*260);
		}
	
		for (int i=0;i<4;++i)
		{
			final int temp1=i;
			fieldcheckbox[i].addListener(new InputListener()
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
					for (int j=0;j<4;++j)
	        	   	{
	        	   		if (temp1!=j) fieldcheckbox[j].setChecked(false);
	        	   	}
				}
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {
						return true;
	           }
			});
		}
		
		

		for (int i=0;i<6;++i)
		{
			int tempx=0;
			int tempy=0;
			for (int j=0;j<5;++j) stage.addActor(playercheckbox[i][j]);
			
			if (i<3) tempx=150;else tempx=650;
			tempy=500-(i%3)*160;
			
			playercheckbox[i][0].setX(tempx);
			playercheckbox[i][0].setY(tempy);
			
			tempx+=120;
			
			
			playercheckbox[i][1].setX(tempx);
			playercheckbox[i][1].setY(tempy+90);
			playercheckbox[i][2].setX(tempx);
			playercheckbox[i][2].setY(tempy+30);
			playercheckbox[i][3].setX(tempx);
			playercheckbox[i][3].setY(tempy-30);
			playercheckbox[i][4].setX(tempx);
			playercheckbox[i][4].setY(tempy-90);
			
		}
	
		for (int i=0;i<6;++i)
		{
			final int temp0=i;
			playercheckbox[i][0].addListener(new InputListener()
			{
				 @Override
				 public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) 
				 {
				 	super.enter(event, x, y, pointer, fromActor);
			 		Sound sound = Resources.getInstance().hover;
			 		sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
				 }
			});
			
			for (int j=1;j<5;++j)
			{
				final int temp1=j;
				playercheckbox[i][j].addListener(new InputListener()
				{
					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) 
					{
			            	super.enter(event, x, y, pointer, fromActor);
			            	Sound sound = Resources.getInstance().hover;
			            	sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
					}
					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
					{
						for (int k=1;k<5;++k)
						{
							if (temp1!=k) playercheckbox[temp0][k].setChecked(false);
						}
						if (temp1==1)
						{
							for (int k=0;k<6;++k)
							{
								if (k!=temp0) playercheckbox[k][1].setChecked(false);
							}
						}
					}
					@Override
					public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
					{
						return true;
					}
				});
			}
			
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
		
		
		enterbutton.addListener(new InputListener(){
			 @Override
		        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		            super.enter(event, x, y, pointer, fromActor);
		            Sound sound = Resources.getInstance().hover;
		            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
		        
		        }
	           @Override
	           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	        	   GameInstance.setInstance(getchoose(fieldcheckbox),getme(),getai(),90);

	        	   ScreenCenter.stopmusic();
	        	   ScreenCenter.setscreen(4);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) {

	        	  if (OK()) return true;else return false;
	           }
		});
	}

	boolean OK()
	{
		boolean temp=false;
		for (int i=0;i<fieldcheckbox.length;++i) if (fieldcheckbox[i].isChecked()) temp=true;
		if (temp)
		{
			int[][] temp1=getai();
			if (temp1.length>0) return true;else return false;
		}else return false;
	}
	
	int getme()
	{
		for (int i=0;i<6;++i) if (playercheckbox[i][0].isChecked()&&playercheckbox[i][1].isChecked()) return i;
		return 0;
	}
	
	int[][] getai()
	{
		int m=0;
		
		for (int i=0;i<6;++i)
		{
			if (playercheckbox[i][0].isChecked())
			{
				
				int temp=-1;
				for (int j=0;j<3;++j)
				{
					if (playercheckbox[i][j+2].isChecked())
					{
						temp=j;
					}
				}
				if (temp!=-1)
				{
					++m;
				}
			}
		}
		int[][] res=new int[m][2];
		m=0;
		for (int i=0;i<6;++i)
		{
			if (playercheckbox[i][0].isChecked())
			{
				
				int temp=-1;
				for (int j=0;j<3;++j)
				{
					if (playercheckbox[i][j+2].isChecked())
					{
						temp=j;
					}
				}
				if (temp!=-1)
				{
					res[m][0]=i;
					res[m][1]=temp;
					++m;
				}
			}
		}
		return res;
	}
	
	int getchoose(CheckBox[] checkone)
	{
		for (int i=0;i<checkone.length;++i) if (checkone[i].isChecked()) return i;
		return 0;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		//Gdx.gl.glClearColor(1, 1, 1, 1);
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
		stage.dispose();
		batch.dispose();
	}

}