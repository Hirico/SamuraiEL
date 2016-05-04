package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
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
	public Vector2 drawPosition;
	public int direction;
	public int side;
	public int id;
	public int score;
	public double cooldownTime;
	public boolean isAllied;
	public boolean isVisible;
	public boolean isHidden;
	public boolean isRecovering;
	public double recoverLeftTime;
	public boolean isMoving;
	
	public Player(Vector2 homePosition) {
		
		isVisible = false;
		isHidden = false;
		isRecovering = false;
		isMoving = false;
		recoverLeftTime = 0;
		isAllied = false;
		this.homePosition = new Vector2();
		this.homePosition.set(homePosition);
		position = new Vector2();
		position.set(homePosition);
		drawPosition = new Vector2();
		drawPosition.set(position);
		cooldownTime = 0;
		score = 0;
		
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
		this.setPosition(field.getBottomLeftCorner().x + drawPosition.x*field.blockSize, 
				field.getBottomLeftCorner().y + drawPosition.y*field.blockSize);
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
		field.executeOccupation(this, position, direction);
		cooldownTime = 30;
	}
	
	public void attacked() {
		if(isAllied) {
			Field field = GameInstance.getInstance().field;
			field.closeVision(position);			
			field.openVision(homePosition);
		}
		position.set(homePosition);	
		drawPosition.set(homePosition);
		isRecovering = true;
		recoverLeftTime = 180;
	}
	
	
	public void hide() {
		Field field = GameInstance.getInstance().field;
		if(field.isOwnSide(this, position)) {
			isHidden = true;
			
			//setTheHideSprite 
			
		}
	}
	
	public void show() {
		Field field = GameInstance.getInstance().field;
		if(field.blocks[(int) position.x][(int) position.y].playerOn == -1) {
			isHidden = false;
			
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
			
			//store targetPosition if not out of field border
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
			
			//execute move if the move follows regulation
			if(((!isHidden && field.blocks[(int) targetPosition.x][(int) targetPosition.y].playerOn == -1) 
					|| (isHidden && field.isOwnSide(this, targetPosition))) 
					&& !field.isOthersHome(this,targetPosition)) {
				isMoving = true;
				
				if(!isHidden) {
					field.blocks[(int) position.x][(int) position.y].playerOn = -1;
					field.blocks[(int) targetPosition.x][(int) targetPosition.y].playerOn = this.id;
				}
				position.set(targetPosition);
				
				//set new vision
				if(!isAllied) {
					if(field.blocks[(int) targetPosition.x][(int) targetPosition.y].isVisible) {
						this.isVisible = true;
					} else { 
						this.isVisible = false;
					}
				} else {
					field.closeVision(position);
					if(position.y < field.getSize().y && direction == 0) {
						field.openVision(targetPosition);
					}
							
					if(position.y > 0 && direction == 1) {
						field.openVision(targetPosition);
					}
							
					if(position.x > 0 && direction == 2) {
						field.openVision(targetPosition);
					}
							
					if(position.x < field.getSize().x && direction == 3) {
						field.openVision(targetPosition);
					}
				}			
				
				
				// isolate the move and slowMove timers to ensure each move complete
				// the below is not actual move
				// it's to slow the pace (only slow down the visual action, actual position already set)
				Timer slowMoveTimer = new Timer(); 				
				slowMoveTimer.scheduleTask(new Timer.Task() {
					
					@Override					
					public void run() {
						if(position.y < GameInstance.getInstance().field.getSize().y && direction == 0) {
							drawPosition.y += 0.05;
						}
							
						if(position.y > 0 && direction == 1) {
							drawPosition.y -= 0.05;
						}
							
						if(position.x > 0 && direction == 2) {
							drawPosition.x -= 0.05;
						}
							
						if(position.x < GameInstance.getInstance().field.getSize().x && direction == 3) {
							drawPosition.x += 0.05;
						}
					}
				}
				, 0, 0.015f, 19);
				
				//notify that the visual move action ended
				Timer moveActionEndTimer = new Timer();
				moveActionEndTimer.scheduleTask(new Timer.Task() {
					
					@Override
					public void run() {
						isMoving = false;
					}
				}, 0.3f, 0, 0);
										
			}
		}
			
	}
	
	public void turn(int direction) {
		this.direction = direction;	
	}


	public void moveEnd(Timer timer) {
		timer.clear();
	}
	
	public void setHomePosition(int x, int y) {
		homePosition.set(x, y);
	}
	
	public Vector2 getPosition() {
		return new Vector2(position.x,position.y);
	}
	
	public Vector2 getHomePosition() {
		return new Vector2(homePosition.x,homePosition.y);
	}


	public void getKillBonus() {
		score += 5;
	}


	public void loseABlock() {
		score -= 1;		
	}


	public void getABlock() {
		score += 1;
		
	}
	
}
