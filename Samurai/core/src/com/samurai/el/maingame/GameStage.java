package com.samurai.el.maingame;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.samurai.el.maingame.Timer;
import com.samurai.el.player.Player;

public class GameStage extends Stage{
	private Timer upTimer;
	private Timer downTimer;
	private Timer leftTimer;
	private Timer rightTimer;
	private GameInstance gameInstance;
	private GameScreen screen;
	
	public GameStage(GameInstance gameInstance, GameScreen screen) {
		super();
		this.screen = screen;
		gameInstance = GameInstance.getInstance();
	}
	
	public void stop() {
		Player human = gameInstance.human;
		if(upTimer != null) {
			human.moveEnd(upTimer);
		}
		if(downTimer != null) {
			human.moveEnd(downTimer);
		}
		if(leftTimer != null) {
			human.moveEnd(leftTimer);
		}
		if(rightTimer != null) {
			human.moveEnd(rightTimer);
		}
	}


	@Override
	public boolean keyDown(int keycode) {
		gameInstance = GameInstance.getInstance();
		Player human = gameInstance.human;
		
		if(!gameInstance.stoped) {
			if(keycode == Input.Keys.ESCAPE) {
				screen.pause();
				screen.game.setScreen(new PauseScreen(screen));
			}
			
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
			if(keycode == Input.Keys.Q) {
				human.turn(false);
			}
			if(keycode == Input.Keys.E) {
				human.turn(true);
			}
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
