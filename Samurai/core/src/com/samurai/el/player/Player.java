package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.samurai.el.field.Block;
import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;

public abstract class Player extends Sprite{
	public boolean isHuman;
	public Vector2 position;
	public Vector2 homePosition;
	public int direction;
	public int side;
	public int cooldownTime;
	public boolean isAllied;
	public boolean isVisible;
	public boolean isHidden;
	public boolean isRecovering;
	public double recoverLeftTime;
	public boolean isMoving;
	
	public Player() {
		isVisible = false;
		isMoving = false;
		isRecovering = false;
		isMoving = false;
		recoverLeftTime = 0;
		isAllied = false;
		position = new Vector2(0,0);
		cooldownTime = 0;
		
	}
	
	
	@Override
	public void draw(Batch batch) {
		//implements waited
		if(cooldownTime != 0) {
			cooldownTime -= 1;
		}
		if(isRecovering) {
			recoverLeftTime -= 1;
			if(recoverLeftTime == 0) {
				isRecovering = false;
			}
		}
		Field field = GameInstance.getInstance().field;
		this.set(new Sprite(new Texture(Gdx.files.internal("deletethis.png"))));
		this.setPosition(field.getBottomLeftCorner().x + position.x*field.blockSize, 
				field.getBottomLeftCorner().y + position.y*field.blockSize);
		if(isMoving) {
			//this.set()
		}
		super.draw(batch);
	}
	
	public boolean occupiable() {
		if(cooldownTime == 0 && !isRecovering && !isHidden && !isMoving) {
			return true;
		} else {
			return false;
		}
	}
	
	public void occupy() {
		cooldownTime += 30;
	}
	
	public void attacked() {
		
		position.x = homePosition.x;
		position.y = homePosition.y;
		isRecovering = true;
		recoverLeftTime += 300;
	}
	
	public void hide() {
		isHidden = true;
		if(!isAllied) {
			isVisible = false;
		} else {
			//setTheHideSprite 
		}
	}
	
	public void show() {
		isHidden = false;
		if(!isAllied) {
			isVisible = true;
		}
	}


	public Timer moveBegin(final int direction) {
		float delay = 0;
		if(direction != this.direction) {
			this.direction = direction;
			delay = 0.1f;
		}
		
		Timer moveTimer = new Timer();
		moveTimer.scheduleTask(new Timer.Task() {
			
			@Override
			public void run() {
				move(direction);
			}
		}
		, delay, 0.3f);//run,turnDelay,interval/moveSpeed(seconds)
		
		return moveTimer;
		
	}
	

	public void move(final int direction) {
		if(!isRecovering) {
			this.direction = direction;
		
			isMoving = true;
			Timer slowMoveTimer = new Timer(); // isolate the move and slowMove timers to ensure each move complete
			
			slowMoveTimer.scheduleTask(new Timer.Task() {
				
				@Override
				//filter the pace
				public void run() {
					if(position.y <= GameInstance.getInstance().field.getSize().y && direction == 0) {
						position.y += 0.05;
					}
						
					if(position.y >= 0 && direction == 1) {
						position.y -= 0.05;
					}
						
					if(position.x >= 0 && direction == 2) {
						position.x -= 0.05;
					}
						
					if(position.x <= GameInstance.getInstance().field.getSize().x && direction == 3) {
						position.x += 0.05;
					}
				}
			}
			, 0, 0.015f, 20);
					
			isMoving = false;
		}
			
	}
	
	public void turn(int direction) {
		this.direction = direction;	
	}


	public void moveEnd(Timer timer) {
		timer.clear();
	}
	
	public void setPosition(int x, int y) {
		position.x = x;
		position.y = y;
	}
	
	public void setHomePosition(int x, int y) {
		homePosition.x = x;
		homePosition.y = y;
	}
	
	public Vector2 getPosition() {
		return new Vector2(position.x,position.y);
	}
	
	public Vector2 getHomePosition() {
		return new Vector2(homePosition.x,homePosition.y);
	}
	
}
