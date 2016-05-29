package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.maingame.Timer;
import com.samurai.el.resource.Resources;

public class EAmmo extends Sprite {
	public int side;
	public Vector2 position; // in block logic
	public Vector2 drawPosition; // from getX()/getY()
	public int direction;
	public Player owner;
	public float timeAccumulator;
	public boolean isValid;
	
	public EAmmo (int side, Vector2 position, int direction, Player p) {
		if(side == 0) {
			this.set(Resources.getInstance().eAmmo0);
		} else {
			this.set(Resources.getInstance().eAmmo1);
		}
		this.position = new Vector2();
		this.drawPosition = new Vector2();
		this.side = side;
		this.position.set(position);
		this.drawPosition.set(position);
		this.direction = direction;
		this.owner = p;
		if(direction == 0) {
			this.setRotation(270);
			this.drawPosition.x -= 4.6;
			this.drawPosition.y += 4;
		}
		if(direction == 1) {
			this.setRotation(90);
			this.drawPosition.x -= 4.6;
			this.drawPosition.y -= 4.8;
		}
		if(direction == 2) {
			this.setRotation(0);
			this.drawPosition.x -= 9;
			this.drawPosition.y -= 0.5;
		}
		if(direction == 3) {			
			this.setRotation(180);
			this.drawPosition.y -= 0.5;
		}
		timeAccumulator = 0f;
	}
	
	@Override
	public void draw(Batch batch) {
		checkOverBorder();
		if(isValid) {
			Field field = GameInstance.getInstance().field;
			this.setPosition(field.getBottomLeftCorner().x + drawPosition.x*field.blockSize, 
					field.getBottomLeftCorner().y + drawPosition.y*field.blockSize);
			timeAccumulator += Gdx.graphics.getDeltaTime();
			if(timeAccumulator >= 0.05f) {
				forward();
				field.executeEAmmo(this);
				timeAccumulator = 0;
			}
			super.draw(batch);
		}
	}
	
	public void launch() {
		isValid = true;
	}
	
	/**set to invalid when touch the map border */
	public void checkOverBorder() {
		Field field = GameInstance.getInstance().field;
		if(position.x <= 0 || 
				position.x >= field.blocks.length-1 ||
				position.y <= 0 || 
				position.y >= field.blocks[0].length-1) {
			isValid = false;
		}
	}
	
	/**move ahead one unit(true position), and use a timer to add drawPosition */
	public void forward() {
		Vector2 targetPosition = new Vector2();
		targetPosition.set(position);
		
		//store targetPosition if not out of field border
		if(position.y < GameInstance.getInstance().field.getSize().y && direction == 0) {
			targetPosition.set(position.x, position.y+1);
		}
			
		if(position.y > 0 && direction == 1) {
			targetPosition.set(position.x, position.y-1);
		}
			
		if(position.x > 0 && direction == 2) {
			targetPosition.set(position.x-1, position.y);
		}
			
		if(position.x < GameInstance.getInstance().field.getSize().x && direction == 3) {
			targetPosition.set(position.x+1, position.y);
		}
		
		final Timer slowMoveTimer = new Timer(); 
		final Vector2 currentPosition = new Vector2();
		currentPosition.set(position);
		slowMoveTimer.scheduleTask(new Timer.Task() {
			
			@Override					
			public void run() {
				if(GameInstance.getInstance() != null) {
					if(currentPosition.y < GameInstance.getInstance().field.getSize().y && direction == 0) {
						drawPosition.y += 0.02;
					}
						
					if(currentPosition.y > 0 && direction == 1) {
						drawPosition.y -= 0.02;
					}
						
					if(currentPosition.x > 0 && direction == 2) {
						drawPosition.x -= 0.02;
					}
						
					if(currentPosition.x < GameInstance.getInstance().field.getSize().x && direction == 3) {
						drawPosition.x += 0.02;
					}
				}
			}
		}
		, 0, 0.004f, 49);
		
		slowMoveTimer.scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				slowMoveTimer.clear();
				slowMoveTimer.stop();					
			}					
		} ,0.21f);
		
		position.set(targetPosition);
	}
	
}
