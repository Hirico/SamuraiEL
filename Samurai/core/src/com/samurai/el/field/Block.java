package com.samurai.el.field;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public class Block extends Sprite{
	public boolean isVisible;
	public Player owner;
	public int id;
	public int side;
	public int playerOn;
	
	public Block() {
		super();
		playerOn = -1;
		id = -1;
		owner = null;
		side = -1;
		isVisible = false;
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
		
		//implements to set sprite
	}
	
	public void occupy(Player p) {
		occupy(p.id);
	}
	
	public void hide() {
		isVisible = false;
		if(!owner.isAllied) {
			owner.isVisible = false;
		}
	}
	
	public void show() {
		isVisible = true;
		owner.isVisible = true;
		//implements to set sprite
	}
}
