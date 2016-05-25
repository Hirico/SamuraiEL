package com.samurai.el.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public class Network {
	public static final int port = 54555;
	
	// register objects that are going to be sent over network
	public static void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(GamesetInfo.class);
		kryo.register(PlayerCommand.class);
		kryo.register(RegisterName.class);
		kryo.register(UpdateNames.class);
		kryo.register(RegisterResponse.class);
		
	}
	
	static public class RegisterName {
		public String name;
	}

	static public class UpdateNames {
		public String[] names;
	}
	
	static public class RegisterResponse {
		public String linkMessage;
		public RegisterResponse() {
			linkMessage = "connected";
		}
	}
	
	
}