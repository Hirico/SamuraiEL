package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.samurai.el.maingame.Timer;
import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.resource.Resources;

public abstract class Player extends Sprite implements Disposable{
	public boolean isHuman;
	public Vector2 position;
	public Vector2 homePosition;
	public Vector2 drawPosition;
	public int direction;
	public int side;
	public int id;
	public int score;
	public int killNum;
	public int killedNum;
	public double cooldownTime;
	public boolean isAllied;
	public boolean isVisible;
	public boolean isHidden;
	public boolean isRecovering;
	public double recoverLeftTime;
	public boolean isMoving;
	public Texture specBlockTexture;
	public boolean isStuck;
	public int movestate;
	public Resources resource;
	public int size;
	
	public Player(Vector2 homePosition) {
		
		isStuck = false;
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
		score = 1;
		killNum = 0;
		killedNum = 0;
		movestate = 0;
		direction = 1;
		size = GameInstance.getInstance().field.blockSize;
		
		resource = Resources.getInstance();
		
		this.set(resource.stand1);
	}
	
	
	@Override
	public void draw(Batch batch) {
		// determine assets
		
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
		
		if(isMoving) {
			if(movestate == 0) {
				switch(direction) {
				case 0:
					this.set(resource.move0_0);
					break;
				case 1:
					this.set(resource.move1_0);
					break;
				case 2:
					this.set(resource.move2_0);
					break;
				case 3:
					this.set(resource.move3_0);
					break;
				}
			} else {
				switch(direction) {
				case 0:
					this.set(resource.move0_1);
					break;
				case 1:
					this.set(resource.move1_1);
					break;
				case 2:
					this.set(resource.move2_1);
					break;
				case 3:
					this.set(resource.move3_1);
					break;
				}
			}
		} else {
			switch(direction) {
			case 0:
				this.set(resource.stand0);
				break;
			case 1:
				this.set(resource.stand1);
				break;
			case 2:
				this.set(resource.stand2);
				break;
			case 3:
				this.set(resource.stand3);
				break;
			}
		}
		
		
		if(isHidden) {
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0.5f);
		}
		
		this.setPosition(field.getBottomLeftCorner().x + drawPosition.x*field.blockSize, 
				field.getBottomLeftCorner().y + drawPosition.y*field.blockSize);
		this.setSize(size, size);
		
		if(isAllied || (isVisible && !isHidden)) {
			super.draw(batch);
		}
	}
	
	public boolean occupiable() {
		if(cooldownTime == 0 && !isRecovering && !isHidden) {
			return true;
		} else {
			return false;
		}
	}
	
	public void occupy() {
		if(occupiable()) {
			Field field = GameInstance.getInstance().field;
			field.executeOccupation(this, position, direction);
			cooldownTime = 60; // this divides 60 is the real time in seconds
		}
	}
	
	/**use for AI before occupy() to avoid making friendly fire */
	public boolean fireSafe() {
		return GameInstance.getInstance().field.fireSafeCheck(this, position, direction);
	}
	
	
	public void attacked() {
		isHidden = false;
		GameInstance.getInstance().field.blocks[(int) position.x][(int) position.y].playerLeave();
		GameInstance.getInstance().field.blocks[(int) homePosition.x][(int) homePosition.y].playerArrive(this);
		isRecovering = true;
		isMoving = false;
		if(isAllied) {
			Field field = GameInstance.getInstance().field;
			field.closeVision(position, this);			
			field.openVision(homePosition);
		}
		position.set(homePosition);	
		drawPosition.set(homePosition);		
		recoverLeftTime = 90;
		killedNum += 1;
	}
	
	
	public void hide() {
		Field field = GameInstance.getInstance().field;
		if(field.isOwnSide(this, position)) {
			isHidden = true;
			field.blocks[(int) position.x][(int) position.y].playerLeave();
			//setTheHideSprite 
			
		}
	}
	
	public void show() {
		Field field = GameInstance.getInstance().field;
		if(field.blocks[(int) position.x][(int) position.y].playerIdOn == -1) {
			isHidden = false;
			field.blocks[(int) position.x][(int) position.y].playerArrive(this);
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
		}
	}


	public Timer moveBegin(final int direction) {
		float delay = 0;
		if(direction != this.direction) {
			this.direction = direction;
			//delay = 0.2f;
		}
		
		Timer moveTimer = new Timer();
		moveTimer.scheduleTask(new Timer.Task() {
			
			@Override
			public void run() {				
				if(GameInstance.getInstance() == null) {
					this.cancel();					
				} else {
					move(direction);
				}
			}
		}
		, delay, 0.2f);//run,turnDelay,interval/moveSpeed(seconds)
		
		return moveTimer;
		
	}
	

	public void move(final int direction) {
		if(!isRecovering) {
			Field field = GameInstance.getInstance().field;
			this.direction = direction;
			Vector2 targetPosition = new Vector2();
			targetPosition.set(position);
			
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
			
			//use for AI
			if(field.isOthersHome(this,targetPosition)) {
				isStuck = true;
			}
			
			//execute move if the move follows regulation 
			if(((!isHidden && field.blocks[(int) targetPosition.x][(int) targetPosition.y].playerIdOn == -1) 
					|| (isHidden && field.isOwnSide(this, targetPosition) && !targetPosition.equals(position))) 
					&& !field.isOthersHome(this,targetPosition)) {
				isMoving = true;
				
				if(!isHidden) {
					field.blocks[(int) position.x][(int) position.y].playerLeave();
					field.blocks[(int) targetPosition.x][(int) targetPosition.y].playerArrive(this);
				}
				
				
				//set new vision
				if(!isAllied) {
					if(field.blocks[(int) targetPosition.x][(int) targetPosition.y].isVisible) {
						this.isVisible = true;
					} else { 
						this.isVisible = false;
					}
				} else {
					field.closeVision(position, this);
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
				// it's to slow the pace (only slow down the visual action, actual position set elsewhere)
				final Timer slowMoveTimer = new Timer(); 
				final Vector2 currentPosition = new Vector2();
				currentPosition.set(position);
				slowMoveTimer.scheduleTask(new Timer.Task() {
					
					@Override					
					public void run() {
						if(GameInstance.getInstance() != null && !isRecovering) {
							if(currentPosition.y < GameInstance.getInstance().field.getSize().y && direction == 0) {
								drawPosition.y += 0.05;
							}
								
							if(currentPosition.y > 0 && direction == 1) {
								drawPosition.y -= 0.05;
							}
								
							if(currentPosition.x > 0 && direction == 2) {
								drawPosition.x -= 0.05;
							}
								
							if(currentPosition.x < GameInstance.getInstance().field.getSize().x && direction == 3) {
								drawPosition.x += 0.05;
							}
						}
					}
				}
				, 0, 0.01f, 19);
				
				slowMoveTimer.scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						slowMoveTimer.clear();
						slowMoveTimer.stop();					
					}					
				} ,0.21f);
				
				if(movestate == 0) {
					movestate = 1;
				} else {
					movestate = 0;
				}
				position.set(targetPosition);
			}
		}
			
	}
	
	/** simply turn direction once */
	public void turn(boolean clockwise) {
		if(clockwise) {
			switch(direction) {
			case 0:
				direction = 3;
				break;
			case 1:
				direction = 2;
				break;
			case 2:
				direction = 0;
				break;
			case 3:
				direction = 1;
				break;
			}
		} else {
			switch(direction) {
			case 0:
				direction = 2;
				break;
			case 1:
				direction = 3;
				break;
			case 2:
				direction = 1;
				break;
			case 3:
				direction = 0;
				break;
			}
		}
	}


	public void moveEnd(Timer timer) {
		if(timer != null) {
			timer.clear();
			timer.stop();
			timer = null;
			isMoving = false;
		}
	}
	
	public void moveEnd() {
		// use for AI non-continuous move
		isMoving = false;
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
		killNum += 1;
		score += 1;
	}


	public void loseABlock() {
		score -= 1;		
	}


	public void getABlock() {
		score += 1;
		
	}
	
	public void dispose() {
		specBlockTexture.dispose();
	}
	
}
