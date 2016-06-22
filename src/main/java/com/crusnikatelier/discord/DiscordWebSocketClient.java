package com.crusnikatelier.discord;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;

import org.java_websocket.client.DefaultSSLWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DiscordWebSocketClient extends WebSocketClient {
	Logger logger = LoggerFactory.getLogger(getClass());

	public DiscordWebSocketClient(URI serverURI) throws KeyManagementException, NoSuchAlgorithmException {
		super(serverURI);
		
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, null, null);
		setWebSocketFactory(new DefaultSSLWebSocketClientFactory(context));
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		logger.debug("Socket Opened");
	}

	@Override
	public void onMessage(String message) {
		logger.trace("Message Recieved : {}", message);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		logger.debug("Socket Closed : {}");
	}
	
	@Override
	public void send(String text) throws java.nio.channels.NotYetConnectedException {
		logger.debug("sending : " +  text);
		super.send(text);
	}

	@Override
	public void onError(Exception ex) {
		logger.error("Discord Websocket has encountered an error", ex);
	}
}
