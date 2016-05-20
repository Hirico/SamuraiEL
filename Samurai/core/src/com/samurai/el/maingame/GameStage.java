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
	public GameInstance gameInstance;
	private GameScreen screen;
	public boolean paused;
	
	public GameStage(GameInstance gameInstance, GameScreen screen) {
		super();
		this.screen = screen;
		this.gameInstance = gameInstance;
		paused = false;
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
			if(gameInstance.mode != -1) {
				if(keycode == Input.Keys.P) {
					if(!paused) {
						screen.pause();
						stop();
						paused = true;
					} else {
						screen.resume();
						paused = false;
					}
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
					if(!human.isHidden) {
						human.hide();
					} else {
						human.show();
					}
				}
				if(keycode == Input.Keys.Q) {
					human.turn(false);
				}
				if(keycode == Input.Keys.E) {
					human.turn(true);
				}
				if(keycode == Input.Keys.U) {
					screen.changeUILayer();
				}
			} else {
				//guideLevel control
				if(gameInstance.guideLevel == 0) {
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
					if(keycode == Input.Keys.Q) {
						human.turn(false);
					}
					if(keycode == Input.Keys.E) {
						human.turn(true);
					}
					if(keycode == Input.Keys.SPACE) {
						gameInstance.updateGuideLevel();
					}
				}
				else if(gameInstance.guideLevel == 1 || gameInstance.guideLevel == 2) {
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
					if(keycode == Input.Keys.Q) {
						human.turn(false);
					}
					if(keycode == Input.Keys.E) {
						human.turn(true);
					}
					if(keycode == Input.Keys.J) {
						human.occupy();
					}
					if(keycode == Input.Keys.SPACE) {
						gameInstance.updateGuideLevel();
					}
				}
				else if(gameInstance.guideLevel == 3) {
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
					if(keycode == Input.Keys.Q) {
						human.turn(false);
					}
					if(keycode == Input.Keys.E) {
						human.turn(true);
					}
					if(keycode == Input.Keys.J) {
						human.occupy();
					}					
					if(keycode == Input.Keys.K) {
						if(!human.isHidden) {
							human.hide();
						} else {
							human.show();
						}
					}if(keycode == Input.Keys.SPACE) {
						gameInstance.updateGuideLevel();
					}
				}
				else if(gameInstance.guideLevel == 4) {
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
					if(keycode == Input.Keys.Q) {
						human.turn(false);
					}
					if(keycode == Input.Keys.E) {
						human.turn(true);
					}
					if(keycode == Input.Keys.J) {
						human.occupy();
					}					
					if(keycode == Input.Keys.K) {
						if(!human.isHidden) {
							human.hide();
						} else {
							human.show();
						}
					}if(keycode == Input.Keys.SPACE) {
						gameInstance.stop();
						screen.exit();
					}
				}
				
				
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
		if(keycode == Input.Keys.ESCAPE) {
			screen.exit();
		}
		return false;
	}
}
