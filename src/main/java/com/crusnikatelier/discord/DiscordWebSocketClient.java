package com.crusnikatelier.discord;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class DiscordWebSocketClient extends WebSocketClient {

	public DiscordWebSocketClient(URI serverURI) {
		super(serverURI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Exception ex) {
		// TODO Auto-generated method stub
		
	}

}
