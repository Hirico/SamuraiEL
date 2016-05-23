package com.samurai.el.maingame;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.samurai.el.maingame.Timer;
import com.samurai.el.player.BlueAxe;
import com.samurai.el.player.BlueSpear;
import com.samurai.el.player.BlueSword;
import com.samurai.el.player.Player;
import com.samurai.el.player.RedAxe;
import com.samurai.el.player.RedSpear;
import com.samurai.el.player.RedSword;
import com.samurai.el.resource.Resources;

public class GameStage extends Stage{
	private Timer upTimer;
	private Timer downTimer;
	private Timer leftTimer;
	private Timer rightTimer;
	public GameInstance gameInstance;
	private GameScreen screen;
	public boolean paused;
	public Button up;
	public Button down;
	public Button left;
	public Button right;
	public Button attack;

	public GameStage(GameInstance gameInstance, GameScreen screen) {
		super(new StretchViewport(1280,720));
		this.screen = screen;
		this.gameInstance = gameInstance;
		paused = false;

		if(Gdx.app.getType() == ApplicationType.Android) {
			Sprite moveButtonSprite = new Sprite();
			moveButtonSprite.set(Resources.getInstance().attackButton);
			Button.ButtonStyle moveButtonStyle = new Button.ButtonStyle();
			moveButtonStyle.up = new SpriteDrawable(moveButtonSprite);
			moveButtonStyle.down = new SpriteDrawable(moveButtonSprite);
			moveButtonStyle.over = new SpriteDrawable(moveButtonSprite);

			up = new Button(moveButtonStyle);
			down = new Button(moveButtonStyle);
			left = new Button(moveButtonStyle);
			right = new Button(moveButtonStyle);

			Sprite attackButtonSprite = new Sprite();
			attackButtonSprite.set(Resources.getInstance().attackButton);
			Button.ButtonStyle attackButtonStyle = new Button.ButtonStyle();
			attackButtonStyle.up = new SpriteDrawable(attackButtonSprite);
			attackButtonStyle.down = new SpriteDrawable(attackButtonSprite);
			attackButtonStyle.over = new SpriteDrawable(attackButtonSprite);

			attack = new Button(attackButtonStyle);

			this.addActor(up);
			this.addActor(down);
			this.addActor(left);
			this.addActor(right);
			this.addActor(attack);
			final Player human = gameInstance.human;
			up.setPosition(144, 410);
			up.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,int pointer, int button)
				{
					upTimer = human.moveBegin(0);
					return true;
				}
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button)
				{
					human.moveEnd(upTimer);
				}
			});

			down.setPosition(144, 156);
			down.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,int pointer, int button)
				{
					downTimer = human.moveBegin(1);
					return true;
				}
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button)
				{
					human.moveEnd(downTimer);
				}
			});

			left.setPosition(50, 288);
			left.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,int pointer, int button)
				{
					leftTimer = human.moveBegin(2);
					return true;
				}
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button)
				{
					human.moveEnd(leftTimer);
				}
			});

			right.setPosition(242, 288);
			right.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,int pointer, int button)
				{
					rightTimer = human.moveBegin(3);
					return true;
				}
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button)
				{
					human.moveEnd(rightTimer);
				}
			});

			attack.setPosition(1000, 288);
			attack.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,int pointer, int button)
				{
					human.occupy();
					return true;
				}
			});
		}
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
				if(keycode == Input.Keys.L) {
					if(human instanceof RedSpear || human instanceof BlueSpear) {
						human.transport();
					}
					else if(human instanceof RedSword || human instanceof BlueSword) {
						human.bound();
					}
					else if(human instanceof RedAxe || human instanceof BlueAxe) {
						human.shockwave();
					}
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
		if(keycode == Input.Keys.BACK) {
			screen.exit();
		}
		return false;
	}

}
