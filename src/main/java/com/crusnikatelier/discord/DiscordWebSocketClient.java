package com.crusnikatelier.discord;

import java.net.URI;
import java.nio.channels.NotYetConnectedException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import javax.net.ssl.SSLContext;
import javax.xml.bind.JAXBException;

import org.java_websocket.WebSocket;
import org.java_websocket.client.DefaultSSLWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;

import com.crusnikatelier.discord.pojos.Operation;
import com.crusnikatelier.utilities.MarshalHelper;

public class DiscordWebSocketClient extends WebSocketClient {

	public DiscordWebSocketClient(URI serverURI) throws KeyManagementException, NoSuchAlgorithmException {
		super(serverURI);
		
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, null, null);
		setWebSocketFactory(new DefaultSSLWebSocketClientFactory(context));
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		System.out.println("onOpen");
	}

	@Override
	public void onMessage(String message) {
		System.out.println("onMessage");
		System.out.println(message);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println(reason);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Exception ex) {
		ex.printStackTrace();
		// TODO Auto-generated method stub
	}
}
