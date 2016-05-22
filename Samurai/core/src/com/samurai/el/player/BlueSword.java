package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.resource.Resources;

public class BlueSword extends Player {
	
	public BlueSword(Vector2 homePosition) {
		super(homePosition);
		side = 1;
		id = 4;
		specBlockTexture = Resources.getInstance().block1;
		attackSound = Resources.getInstance().attack1;
		this.set(Resources.getInstance().stand1_4);
		totalCooldownTime = 55;
		playerHint.set(Resources.getInstance().player1);
		GameInstance.getInstance().teamScores[1] += 1;
		attackEffect.set(Resources.getInstance().electric1);
	}
	
	@Override
	public void attackEffectBegin() {
		Field field = GameInstance.getInstance().field;
		attackEffectDelay = 0.08f;
		switch(direction) {
		case 0:
			attackEffect.setRotation(180);
			attackEffect.setPosition(field.getBottomLeftCorner().x + (position.x-2.22f)*field.blockSize, 
					 field.getBottomLeftCorner().y + (position.y-0.27f)*field.blockSize);
			break;
		case 1:
			attackEffect.setRotation(0);
			attackEffect.setPosition(field.getBottomLeftCorner().x + (position.x-0.5f)*field.blockSize, 
					 field.getBottomLeftCorner().y + (position.y-1.98f)*field.blockSize);
			break;
		case 2:
			attackEffect.setRotation(270);
			attackEffect.setPosition(field.getBottomLeftCorner().x + (position.x-2.2f)*field.blockSize, 
					 field.getBottomLeftCorner().y + (position.y-2f)*field.blockSize);
			break;
		case 3:
			attackEffect.setRotation(90);
			attackEffect.setPosition(field.getBottomLeftCorner().x + (position.x-0.5f)*field.blockSize, 
				 field.getBottomLeftCorner().y + (position.y-0.3f)*field.blockSize);
			break;
		}
	}
	
	@Override
	public void draw(Batch batch) {
		Field field = GameInstance.getInstance().field;
		if(cooldownTime > 0) {
			cooldownTime -= 60*Gdx.graphics.getDeltaTime();
			if(cooldownTime < 0) {
				cooldownTime = 0;
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
				this.set(resource.move0_4);
				break;
			case 1:
				this.set(resource.move1_4);
				break;
			case 2:
				this.set(resource.move2_4);
				break;
			case 3:
				this.set(resource.move3_4);
				break;
			}			 
		} else {
			switch(direction) {
			case 0:
				this.set(resource.stand0_4);
				break;
			case 1:
				this.set(resource.stand1_4);
				break;
			case 2:
				this.set(resource.stand2_4);
				break;
			case 3:
				this.set(resource.stand3_4);
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
