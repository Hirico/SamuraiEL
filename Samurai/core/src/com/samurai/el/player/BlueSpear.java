package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.resource.Resources;

public class BlueSpear extends Player {
	
	public BlueSpear(Vector2 homePosition) {
		super(homePosition);
		side = 1;
		id = 3;
		specBlockTexture = Resources.getInstance().block1;
		attackSound = Resources.getInstance().attack0;
		this.set(Resources.getInstance().stand1_3);
		initialTotalCooldownTime = 60;
		totalCooldownTime = initialTotalCooldownTime;
		playerHint.set(Resources.getInstance().player1);
		attackEffect.set(Resources.getInstance().laser1);
		GameInstance.getInstance().teamScores[1] += 1;
		LskillTotalCooldown = 4f;
		LskillCooldown = 0;
	}
	
	@Override
	public void attackEffectBegin() {
		Field field = GameInstance.getInstance().field;
		attackEffectDelay = 0.08f;
		switch(direction) {
		case 0:
			attackEffect.setRotation(90);
			attackEffect.setPosition(field.getBottomLeftCorner().x + (drawPosition.x-1.5f)*field.blockSize, 
					 field.getBottomLeftCorner().y + (drawPosition.y+2.75f)*field.blockSize);
			break;
		case 1:
			attackEffect.setRotation(270);
			attackEffect.setPosition(field.getBottomLeftCorner().x + (drawPosition.x-1.5f)*field.blockSize, 
					 field.getBottomLeftCorner().y + (drawPosition.y-1.98f)*field.blockSize);
			break;
		case 2:
			attackEffect.setRotation(180);
			attackEffect.setPosition(field.getBottomLeftCorner().x + (drawPosition.x-3.9f)*field.blockSize, 
					 field.getBottomLeftCorner().y + (drawPosition.y+0.385f)*field.blockSize);
			break;
		case 3:
			attackEffect.setRotation(0);
			attackEffect.setPosition(field.getBottomLeftCorner().x + (drawPosition.x+0.9f)*field.blockSize, 
				 field.getBottomLeftCorner().y + (drawPosition.y+0.385f)*field.blockSize);
			break;
		}
	}
	
	@Override
	public void draw(Batch batch) {
		// determine assets
		Field field = GameInstance.getInstance().field;
		if(cooldownTime > 0) {
			cooldownTime -= 60*Gdx.graphics.getDeltaTime();
			if(cooldownTime < 0) {
				cooldownTime = 0;
			}
		}
		if(stopTime > 0) {
			stopTime -= Gdx.graphics.getDeltaTime();
			if(stopTime < 0) {
				stopTime = 0;
			}
		}
		if(LskillCooldown > 0) {
			LskillCooldown -= Gdx.graphics.getDeltaTime();
			if(LskillCooldown < 0) {
				LskillCooldown = 0;
			}
		}
		
		if(attackEffectDelay > 0) {
			attackEffectDelay -= Gdx.graphics.getDeltaTime();
			attackEffect.draw(batch);
		}
				
		if(isRecovering) {
			recoverLeftTime -= 60*Gdx.graphics.getDeltaTime();
			if(recoverLeftTime <= 0) {
				isRecovering = false;
				field.blocks[(int) homePosition.x][(int) homePosition.y].recoverComplete();				
			}
		}	
		
		if(isMoving) {
			switch(direction) {
			case 0:
				this.set(resource.move0_3);
				break;
			case 1:
				this.set(resource.move1_3);
				break;
			case 2:
				this.set(resource.move2_3);
				break;
			case 3:
				this.set(resource.move3_3);
				break;
			}			 
		} else {
			switch(direction) {
			case 0:
				this.set(resource.stand0_3);
				break;
			case 1:
				this.set(resource.stand1_3);
				break;
			case 2:
				this.set(resource.stand2_3);
				break;
			case 3:
				this.set(resource.stand3_3);
				break;
			}
		}
			
		
		this.setPosition(field.getBottomLeftCorner().x + drawPosition.x*field.blockSize, 
				field.getBottomLeftCorner().y + drawPosition.y*field.blockSize);
		this.setSize(size, size);
		
		
		if(isHidden) {
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0.5f);
		}
		
		if(isAllied || (isVisible && !isHidden)) {
			if(isHuman) {
				playerHint.setPosition(this.getX(), this.getY());
				playerHint.draw(batch);
			}
			if(!isRecovering) {
				super.draw(batch);
			}
		}
	}
	
}
