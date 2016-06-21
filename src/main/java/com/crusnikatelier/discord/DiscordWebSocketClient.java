package com.crusnikatelier.discord;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import javax.net.ssl.SSLContext;

import org.java_websocket.WebSocket;
import org.java_websocket.client.DefaultSSLWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;

public class DiscordWebSocketClient extends WebSocketClient {

	public DiscordWebSocketClient(URI serverURI) throws KeyManagementException, NoSuchAlgorithmException {
		super(serverURI);
		
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, null, null);
		setWebSocketFactory(new DefaultSSLWebSocketClientFactory(context));
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		System.out.print("onOpen");

		System.out.print(handshakedata);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String message) {
		System.out.print(message);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.print(reason);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Exception ex) {
		ex.printStackTrace();
		// TODO Auto-generated method stub
	}

	
	@Override
	public void onWebsocketHandshakeSentAsClient( WebSocket conn, ClientHandshake request ){
		
		//System.out.println(new String(request.getContent()));
		Iterator<String> it =  request.iterateHttpFields();
		while(it.hasNext()){
			String field = it.next();
			System.out.println(field + " : " + request.getFieldValue(field));
		}
		
		System.out.println("onWebsocketHandshakeSentAsClient");
		
	}

}
