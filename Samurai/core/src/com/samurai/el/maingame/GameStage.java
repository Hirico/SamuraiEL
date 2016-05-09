package com.samurai.el.maingame;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.samurai.el.player.Player;

public class GameStage extends Stage{
	private Timer upTimer;
	private Timer downTimer;
	private Timer leftTimer;
	private Timer rightTimer;
	private GameInstance gameInstance;
	
	public GameStage(GameInstance gameInstance) {
		super();
		gameInstance = GameInstance.getInstance();
	}


	@Override
	public boolean keyDown(int keycode) {
		gameInstance = GameInstance.getInstance();
		Player human = gameInstance.human;
		if(keycode == Input.Keys.W) {
			upTimer = human.moveBegin(0);
		}
		if(keycode == Input.Keys.S) {
			downTimer = human.moveBegin(1);
		}
		if(keycode == Input.Keys.A) {
			leftTimer = human.moveBegin(2);
		}
		if(keycode == Input.Keys.D) {
			rightTimer = human.moveBegin(3);
		}
		if(keycode == Input.Keys.J) {
			human.occupy();
		}
		if(keycode == Input.Keys.K) {
			human.hide();
		}
		if(keycode == Input.Keys.L) {
			human.show();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Player human = gameInstance.human;
		if(keycode == Input.Keys.W) {
			human.moveEnd(upTimer);
		}
		if(keycode == Input.Keys.S) {
			human.moveEnd(downTimer);
		}
		if(keycode == Input.Keys.A) {
			human.moveEnd(leftTimer);
		}
		if(keycode == Input.Keys.D) {
			human.moveEnd(rightTimer);
		}
		return false;
	}
}
