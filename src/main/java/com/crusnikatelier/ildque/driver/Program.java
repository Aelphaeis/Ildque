package com.crusnikatelier.ildque.driver;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.crusnikatelier.discord.pojos.GatewayResponse;
import com.crusnikatelier.utilities.MarshalHelper;

public class Program {
	
	public final static String BASE_URL = "wss://gateway.discord.gg/";
	public final static String DISCORD_URL = "https://discordapp.com/api";
			
	public static void main(String[] args) throws Throwable {
	//	DiscordClient dc = new DiscordClient();
		//System.out.println(dc.getGatewayResponse().getUrl());
		GatewayResponse gr = new GatewayResponse();
		gr.setUrl(BASE_URL);
		
	}
	
	
	
	public static void wscTest() throws InterruptedException, URISyntaxException{

		String url = BASE_URL;
		
		WebSocketClient wsc = new WebSocketClient(new URI(url)){

			@Override
			public void onOpen(ServerHandshake handshakedata) {
				System.out.println(handshakedata);
			}

			@Override
			public void onMessage(String message) {
				System.out.println(message);
				
			}

			@Override
			public void onClose(int code, String reason, boolean remote) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onError(Exception ex) {
				ex.printStackTrace();
			}
		};
		
		System.out.println("Attempting Connect");
		wsc.connectBlocking();
		wsc.send("");
		
	}

}
