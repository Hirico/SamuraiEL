package com.samurai.el.maingame;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;
import com.samurai.el.network.PlayerCommand;
import com.samurai.el.player.BlueAxe;
import com.samurai.el.player.BlueSpear;
import com.samurai.el.player.BlueSword;
import com.samurai.el.player.Player;
import com.samurai.el.player.RedAxe;
import com.samurai.el.player.RedSpear;
import com.samurai.el.player.RedSword;

public class RemotePlayerController {
	public Player[] controlledPlayers;
	private Timer upTimer;
	private Timer downTimer;
	private Timer leftTimer;
	private Timer rightTimer;
	
	public RemotePlayerController() {
		controlledPlayers = new Player[6];
		
	}
	
	public void setPlayer(Player player) {
		controlledPlayers[player.id] = player;
	}
	
	public void executeCommand(PlayerCommand command) {
		Player player = controlledPlayers[command.id];

		if(command.keycode == Input.Keys.W) {
			if(command.keyDown) {
				upTimer = player.moveBegin(0);
			} else {
				player.moveEnd(upTimer);
			}
		}
		if(command.keycode == Input.Keys.S) {
			if(command.keyDown) {
				downTimer = player.moveBegin(1);
			} else {
				player.moveEnd(downTimer);
			}
		}
		if(command.keycode == Input.Keys.A) {
			if(command.keyDown) {
				leftTimer = player.moveBegin(2);
			} else {
				player.moveEnd(leftTimer);
			}
		}
		if(command.keycode == Input.Keys.D) {
			if(command.keyDown) {
				rightTimer = player.moveBegin(3);
			} else {
				player.moveEnd(rightTimer);
			}
		}
		if(command.keycode == Input.Keys.J) {
			player.occupy();
		}
		if(command.keycode == Input.Keys.K) {
			if(!player.isHidden) {
				player.hide();
			} else {
				player.show();
			}
		}
		if(command.keycode == Input.Keys.Q) {
			player.turn(false);
		}
		if(command.keycode == Input.Keys.E) {
			player.turn(true);
		}
		if(command.keycode == Input.Keys.L) {
			if(player instanceof RedSpear || player instanceof BlueSpear) {
				player.transport();
			}
			else if(player instanceof RedSword || player instanceof BlueSword) {
				player.bound();
			}
			else if(player instanceof RedAxe || player instanceof BlueAxe) {
				player.shockwave();
			}
		}
	}
}
