package com.samurai.el.field;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;
import com.samurai.el.resource.Resources;

public class Block extends Sprite{
	public boolean isVisible;
	public Player owner;
	public int id;
	public int side;
	public int playerIdOn;
	public int viewerNum;
	public boolean isHome;
	public Player playerOn;
	public Texture block6; // invisible block
	public Texture block7; // neutral block
	public int size;
	
	public Block(int size) {
		super();
		this.size = size;
		
		viewerNum = 0;
		playerIdOn = -1;
		id = -1;
		owner = null;
		side = -1;
		isVisible = false;
		block6 = Resources.getInstance().block6;
		block7 = Resources.getInstance().block7;
		this.set(new Sprite(block6));
		isHome = false;
	}
	
	public void playerArrive(Player player) {
		playerOn = player;
		playerIdOn = playerOn.id;
		if(isVisible) {
			playerOn.isVisible = true;
		}
	}
	
	public void playerArrive(int id) {
		GameInstance tempInstance = GameInstance.getInstance();
		switch(id) {
		case 0:
			playerArrive(tempInstance.redSpear);
			break;
		case 1:
			playerArrive(tempInstance.redSword);
			break;
		case 2:
			playerArrive(tempInstance.redAxe);
			break;
		case 3:
			playerArrive(tempInstance.blueSpear);
			break;
		case 4:
			playerArrive(tempInstance.blueSword);
			break;
		case 5:
			playerArrive(tempInstance.blueAxe);
			break;
		}
	}
	
	public void playerLeave() {
		if(playerOn != null) {
			playerOn.isVisible = false;
		}
		playerOn = null;		
		playerIdOn = -1;
		
	}
	
	public void occupy(int id) {
		this.id = id;
		GameInstance tempInstance = GameInstance.getInstance();
		switch(id) {
		case 0:
			owner = tempInstance.redSpear;
			side = 0;
			break;
		case 1:
			owner = tempInstance.redSword;
			side = 0;
			break;
		case 2:
			owner = tempInstance.redAxe;
			side = 0;
			break;
		case 3:
			owner = tempInstance.blueSpear;
			side = 1;
			break;
		case 4:
			owner = tempInstance.blueSword;
			side = 1;
			break;
		case 5:
			owner = tempInstance.blueAxe;
			side = 1;
			break;
		}
		
		//implements to set texture
		if(isVisible) {
			this.setTexture(owner.specBlockTexture);
		}
		
	}
	
	public void occupy(Player p) {
		occupy(p.id);
	}
	
	public void hide() {
		viewerNum -= 1;
		if(viewerNum == 0) {
			isVisible = false;
			if((playerOn != null) && (!playerOn.isAllied)) {
				playerOn.isVisible = false;
			}
			this.setTexture(block6);
		}
	}
	
	public void show() {		
		if(viewerNum == 0) {
			isVisible = true;
			if(playerOn != null) {
				playerOn.isVisible = true;
			}
			if(owner != null) {
				this.setTexture(owner.specBlockTexture);
			} else {
				this.setTexture(block7);
			}
		}
		viewerNum += 1;
	}
		
	
	@Override
	public void draw(Batch batch) {
		this.setSize(size, size);
		super.draw(batch);
	}
	
	public void dispose() {

	}
}
