package com.samurai.el.gamesetting;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
	CheckBox[] playercheckbox=new CheckBox[6];
	boolean flag;
	Skin skin;
	List list;
	SelectBox[] playerselectbox=new SelectBox[6];
	Object[] disitemlist={"","Player","Easy AI","Normal AI","Hard AI","Cruel AI"};
	Object[] itemlist={"Player","Easy AI","Normal AI","Hard AI","Cruel AI"};
	String[] name={"Advancer","Tracker","Reaper"};
	SpriteBatch batch;
	Sprite background;
	Sprite republic;
	Sprite union;
	Slider timeset;
	int icon;
	int[][] position=new int[6][2];
	Label timeset_label;
	Sprite[] playername=new Sprite[6];
	
	int mapid;
	int modeid;
	
	Sprite blackFade;
	SpriteBatch fadeBatch;
	public float fade;
	public OrthographicCamera camera;
	
	public GameSetScreen(int mapid,int modeid) {
		
		this.mapid=mapid;
		this.modeid=modeid;
		
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);
		blackFade = Resources.getInstance().blackFade;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		camera.position.set(640,360,0);
		
		icon=535;
		for (int i=0;i<6;++i)
		{
			if (i<3) position[i][0]=260;else position[i][0]=735;
			position[i][1]=icon-40-(i%3)*150;
		}
		
		flag=true;
		font=new BitmapFont(Gdx.files.internal("foxwel_temp/choose/1.fnt"),Gdx.files.internal("foxwel_temp/choose/1.png"), false);

		
		batch=new SpriteBatch();
		background=new Sprite(new Texture(Gdx.files.internal("img/background/gameset.png")));
		background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		stage = new Stage(new StretchViewport(1280,720));
		
			
		
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
		
		
		
		
		Sprite check0=new Sprite(new Texture("img/setting/check0.png"));
		Sprite check1=new Sprite(new Texture("img/setting/check1.png"));
		Sprite check2=new Sprite(new Texture("img/setting/check2.png"));

		
		CheckBox.CheckBoxStyle checkstyle=new CheckBox.CheckBoxStyle();
		checkstyle.checkboxOver=new SpriteDrawable(check1);
		checkstyle.checkboxOn=new SpriteDrawable(check2);
		checkstyle.checkboxOff=new SpriteDrawable(check0);
		checkstyle.font=font;
		checkstyle.fontColor=new Color(Color.YELLOW);
	
		
		
		for (int i=0;i<6;++i)
		{
			Sprite checkB0=new Sprite(new Texture("img/gameset/r"+i+".png"));
			Sprite checkB1=new Sprite(new Texture("img/gameset/h"+i+".png"));
			Sprite checkB2=new Sprite(new Texture("img/gameset/r"+i+".png"));
			
			checkB0.setSize(80, 80);
			checkB0.setAlpha(0.4f);
			
			CheckBox.CheckBoxStyle playercheckstyle=new CheckBox.CheckBoxStyle();
			playercheckstyle.checkboxOver=new SpriteDrawable(checkB1);
			playercheckstyle.checkboxOn=new SpriteDrawable(checkB2);
			playercheckstyle.checkboxOff=new SpriteDrawable(checkB0);
			playercheckstyle.font=font;
			playercheckstyle.fontColor=new Color(Color.YELLOW);
			playercheckbox[i]=new CheckBox("",playercheckstyle);
			
		}
		
		for (int i=0;i<6;++i)
		{
			playername[i]=new Sprite(new Texture("img/gameset/f"+i+".png"));
		}
		
		skin = new Skin(Gdx.files.internal("test.json"));
		for (int i=0;i<6;++i) playerselectbox[i]=new SelectBox(skin);
		
		republic=new Sprite(new Texture("img/gameset/republic.png"));
		union=new Sprite(new Texture("img/gameset/union.png"));
		
		
		Sprite bar=new Sprite(new Texture("img/setting/barA.png"));
		Sprite tt=new Sprite(new Texture("img/setting/tA.png"));
		Slider.SliderStyle volumestyle=new Slider.SliderStyle(new SpriteDrawable(bar),new SpriteDrawable(tt)); 
		
		timeset=new Slider(200,3000,1,false,volumestyle);

		Label.LabelStyle labelstyle =new Label.LabelStyle(font, Color.YELLOW);
		
		timeset_label=new Label("90S",labelstyle);

	}
		

	
	@Override
	public void show() {
		fade = 1.0f;
		Gdx.input.setInputProcessor(stage);
		
		stage.addActor(returnbutton);
		returnbutton.setPosition(30, 8);
		
		stage.addActor(enterbutton);
		enterbutton.setPosition(1020, 8);

		stage.addActor(timeset_label);
		timeset_label.setPosition(1000, 90);
		
		stage.addActor(timeset);
		timeset.setBounds(640-700/2, 90,700, 35);
		timeset.setValue(900);
		if (Gdx.app.getPreferences("challenge").getInteger("winNum", 0)<=20)
			{
			timeset.setVisible(false);
			timeset_label.setVisible(false);
			}
		


		
		for (int i=0;i<6;++i)
		{
			
			stage.addActor(playercheckbox[i]);
			stage.addActor(playerselectbox[i]);
			
			
			playercheckbox[i].setPosition(position[i][0],position[i][1]);
			
			playerselectbox[i].setItems(disitemlist);
			playerselectbox[i].setSelected("");
			playerselectbox[i].setDisabled(true);
			playerselectbox[i].getScrollPane().setScrollingDisabled(false, false);
			playerselectbox[i].setPosition(position[i][0]+90,position[i][1]-17);
			playerselectbox[i].setSize(160, 33);
		}

		for (int i=0;i<6;++i)
		{
			final int temp=i;

			playerselectbox[i].addListener(new ChangeListener()
			{
				
				
				 public void changed (ChangeEvent event, Actor actor) 
				 {
					 if ((playerselectbox[temp].getSelected()=="Player")&&flag)
					 {
						 
						 for (int j=0;j<6;++j)
						 {
							 if (j!=temp)
							 {
								 if (playerselectbox[j].getSelected()=="Player") playerselectbox[j].setSelected("Normal AI");
							 }
						 }
					 }
				 }
			});
			playerselectbox[i].addListener(new InputListener(){
				 @Override
			        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
			            super.enter(event, x, y, pointer, fromActor);
			            Sound sound = Resources.getInstance().hover;
			            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
			        
			        }
					
			});
			playercheckbox[i].addListener(new ChangeListener()
			{
				
				
				 public void changed (ChangeEvent event, Actor actor) 
				 {
					 if (playercheckbox[temp].isChecked())
					 {
						 playerselectbox[temp].setDisabled(false);
						 
						 flag=false;
						 playerselectbox[temp].setItems(itemlist);
						 flag=true;
						 
						 playerselectbox[temp].setSelected("Normal AI");
						 
						 if (temp==0) playerselectbox[0].setSelected("Player");
						 
					 }else
					 {
						 playerselectbox[temp].setDisabled(true);
						 flag=false;
						 playerselectbox[temp].setItems(disitemlist);
						 flag=true;
						 playerselectbox[temp].setSelected("");
						 
					 }
				 }
			});
			playercheckbox[i].addListener(new InputListener(){
				 @Override
			        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
			            super.enter(event, x, y, pointer, fromActor);
			            Sound sound = Resources.getInstance().hover;
			            sound.play(Gdx.app.getPreferences("volumePref").getFloat("soundVolume", 1f));
			        
			        }
					
			});
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
	        	   ScreenCenter.setscreen(2);
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
	        	   
	        	   int time=90;
	        	   //time=(int) (timeset.getValue()/10);
	        	   if (Gdx.app.getPreferences("challenge").getInteger("winNum", 0)>20) time=(int) (timeset.getValue()/10);
	        	   GameInstance.setInstance(mapid,getme(),getai(),time,modeid);
	        	   ScreenCenter.stopmusic();
	        	   ScreenCenter.setscreen(4);
	           }
	           @Override
	           public boolean touchDown(InputEvent event, float x, float y,int pointer, int button) 
	           {

	        	  if (OK()) return true;else return false;
	           }
		});
		timeset.addListener(new ChangeListener()
		{
			 public void changed (ChangeEvent event, Actor actor) 
			 {
				 int timetime=(int) (timeset.getValue()/10);
				 
				 timeset_label.setText(timetime+"s");
			 }
		});
	}
	

	boolean OK()
	{
		if ((getme()!=-1))
		{
			return true;
		}else return false;
	}
	
	int getme()
	{
		for (int i=0;i<6;++i) if (playercheckbox[i].isChecked()&&(playerselectbox[i].getSelected()=="Player")) return i;
		return -1;
	}
	
	int[][] getai()
	{
		int m=0;
		for (int i=0;i<6;++i)
		{
			if (playercheckbox[i].isChecked())
			{
				if (playerselectbox[i].getSelected()=="Easy AI")
				{
					
					++m;
				}
				if (playerselectbox[i].getSelected()=="Normal AI")
				{
					++m;
				}
				if (playerselectbox[i].getSelected()=="Hard AI")
				{
					++m;
				}
				if (playerselectbox[i].getSelected()=="Cruel AI")
				{
					++m;
				}
			}
		}
		int[][] res=new int[m][2];
		m=0;for (int i=0;i<6;++i)
		{
			if (playercheckbox[i].isChecked())
			{
				if (playerselectbox[i].getSelected()=="Easy AI")
				{
					res[m][0]=i;
					res[m][1]=0;
					++m;
				}
				if (playerselectbox[i].getSelected()=="Normal AI")
				{

					res[m][0]=i;
					res[m][1]=1;
					++m;
				}
				if (playerselectbox[i].getSelected()=="Hard AI")
				{

					res[m][0]=i;
					res[m][1]=2;
					++m;
				}
				if (playerselectbox[i].getSelected()=="Cruel AI")
				{

					res[m][0]=i;
					res[m][1]=3;
					++m;
				}
			}
		}	return res;
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

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.draw(batch);
		batch.draw(republic,253,icon+100);
		batch.draw(union,778,icon+100);
		for (int i=0;i<6;++i)
		{
			batch.draw(playername[i],position[i][0]+84,position[i][1]+20);
		}
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