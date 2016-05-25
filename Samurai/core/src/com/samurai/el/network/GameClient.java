package com.samurai.el.network;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.samurai.el.network.Network.RegisterName;

public class GameClient {
	public Client client;
	public String name;
	
	public GameClient() {
		client = new Client();
		client.start();
		
		Network.register(client);
		
		client.addListener(new Listener() {
			public void connected(Connection connection) {
				RegisterName registerName = new RegisterName();
				registerName.name = name;
				client.sendTCP(registerName);
			}
			
			public void received(Connection connection, Object object) {
				if(object instanceof GamesetInfo) {
					
				}
				
				if(object instanceof PlayerCommand) {
					
				}
			}
			
			public void disconnected(Connection connection) {
				
			}
		});
		
		// Request the host from the user.
		String input = (String)JOptionPane.showInputDialog(null, "Host:", "Connect to chat server", JOptionPane.QUESTION_MESSAGE,
					null, null, "localhost");
		if (input == null || input.trim().length() == 0) {
			System.exit(1);
		}
		final String host = input.trim();
		
		try {
			client.connect(5000, host, Network.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	


}
