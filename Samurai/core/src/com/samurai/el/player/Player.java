package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;

public abstract class Player extends Sprite{
	public boolean isHuman;
	public Vector2 position;
	public Vector2 homePosition;
	public int direction;
	public int side;
	public int id;
	public double cooldownTime;
	public boolean isAllied;
	public boolean isVisible;
	public boolean isHidden;
	public boolean isRecovering;
	public double recoverLeftTime;
	public boolean isMoving;
	
	public Player(Vector2 homePosition) {
		
		isVisible = true;
		isHidden = false;
		isRecovering = false;
		isMoving = false;
		recoverLeftTime = 0;
		isAllied = false;
		this.homePosition = new Vector2();
		this.homePosition.set(homePosition);
		position = new Vector2();
		position.set(homePosition);
		cooldownTime = 0;
		
	}
	
	
	@Override
	public void draw(Batch batch) {
		//implements waited
		if(cooldownTime > 0) {
			cooldownTime -= 60*Gdx.graphics.getDeltaTime();
			if(cooldownTime < 0) {
				cooldownTime = 0;
			}
		}
		
		if(isRecovering) {
			recoverLeftTime -= 60*Gdx.graphics.getDeltaTime();
			if(recoverLeftTime <= 0) {
				isRecovering = false;
			}
		}
		Field field = GameInstance.getInstance().field;
		//this.set(new Sprite(new Texture(Gdx.files.internal("deletethis.png"))));
		this.setPosition(field.getBottomLeftCorner().x + position.x*field.blockSize, 
				field.getBottomLeftCorner().y + position.y*field.blockSize);
		if(isMoving) {
			//this.set()
		}
		if(isVisible) {
			super.draw(batch);
		}
	}
	
	public boolean occupiable() {
		if(cooldownTime == 0 && !isRecovering && !isHidden && !isMoving) {
			return true;
		} else {
			return false;
		}
	}
	
	public void occupy() {
		Field field = GameInstance.getInstance().field;
		field.executeOccupation(id, position, direction);
		cooldownTime = 30;
	}
	
	public void attacked() {
		Field field = GameInstance.getInstance().field;
		field.closeVision(position);
		position.set(homePosition);
		field.openVision(homePosition);
		isRecovering = true;
		recoverLeftTime = 180;
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
			Field field = GameInstance.getInstance().field;
			this.direction = direction;
			Vector2 targetPosition = new Vector2();
			
			if(position.y < field.getSize().y && direction == 0) {
				targetPosition.set(position.x, position.y+1);
			}
				
			if(position.y > 0 && direction == 1) {
				targetPosition.set(position.x, position.y-1);
			}
				
			if(position.x > 0 && direction == 2) {
				targetPosition.set(position.x-1, position.y);
			}
				
			if(position.x < field.getSize().x && direction == 3) {
				targetPosition.set(position.x+1, position.y);
			}
			
			if((!isHidden) || (isHidden && field.isOwnSide(this, targetPosition))) {
				isMoving = true;
				Timer slowMoveTimer = new Timer(); // isolate the move and slowMove timers to ensure each move complete
				field.closeVision(position);
				if(position.y < field.getSize().y && direction == 0) {
					field.openVision(new Vector2(position.x, position.y+1));
				}
					
				if(position.y > 0 && direction == 1) {
					field.openVision(new Vector2(position.x, position.y-1));
				}
					
				if(position.x > 0 && direction == 2) {
					field.openVision(new Vector2(position.x-1, position.y));
				}
					
				if(position.x < field.getSize().x && direction == 3) {
					field.openVision(new Vector2(position.x+1, position.y));
				}
			
				
				slowMoveTimer.scheduleTask(new Timer.Task() {
					
					@Override
					//filter the pace
					public void run() {
						if(position.y < GameInstance.getInstance().field.getSize().y && direction == 0) {
							position.y += 0.05;
						}
							
						if(position.y > 0 && direction == 1) {
							position.y -= 0.05;
						}
							
						if(position.x > 0 && direction == 2) {
							position.x -= 0.05;
						}
							
						if(position.x < GameInstance.getInstance().field.getSize().x && direction == 3) {
							position.x += 0.05;
						}
					}
				}
				, 0, 0.015f, 20);
						
				isMoving = false;
			}
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
