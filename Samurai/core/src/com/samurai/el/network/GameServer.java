package com.samurai.el.network;

import java.io.IOException;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.samurai.el.network.Network.RegisterName;

public class GameServer {
	public Server server;
	
	public GameServer () throws IOException {
		server = new Server() {
			protected Connection newConnection() {
				return new GameConnection();
			}
		};
		Network.register(server);
		
		server.addListener(new Listener() {
			public void received(Connection c, Object object) {
				GameConnection connection = (GameConnection) c;
				
				if(object instanceof RegisterName) {
					connection.sendTCP(new Network.RegisterResponse());
				}
				
				if(object instanceof GamesetInfo) {
					
				}
				
				if(object instanceof PlayerCommand) {
					
				}
			}
			
			public void disconnected(Connection c) {
				
			}
		});
		
		server.bind(Network.port);
		server.start();
	}
	
	
	
	
	static class GameConnection extends Connection {
		public String name;
	}
	
	public static void main() throws IOException {
		Log.set(Log.LEVEL_DEBUG);
		new GameServer();
	}
}
