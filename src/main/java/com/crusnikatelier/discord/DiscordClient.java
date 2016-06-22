package com.crusnikatelier.discord;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.discord.pojos.GatewayResponse;
import com.crusnikatelier.ildque.driver.Program;
import com.crusnikatelier.utilities.MarshalHelper;

public class DiscordClient {
	public final static String BASE_URL = "https://discordapp.com/api";
	private DiscordWebSocketClient discordWsClient;
	
	public DiscordClient() throws IOException, JAXBException, InterruptedException, KeyManagementException, NoSuchAlgorithmException{
		GatewayResponse gr = getGatewayResponse();
		URI websocketUri = URI.create(gr.getUrl());
		discordWsClient = new DiscordWebSocketClient(websocketUri);
	}
	
	public void Run() throws InterruptedException{
		discordWsClient.connectBlocking();
	}
	
	public GatewayResponse getGatewayResponse() throws IOException, JAXBException {
		URL url = new URL(BASE_URL + GatewayResponse.REQUEST_PATH);
		URLConnection connection =  url.openConnection();
		
		connection.addRequestProperty("method", "GET");
		connection.addRequestProperty("User-Agent", "Ildque/0.0.1");
		
		String response = "";
		try(Scanner scanner = new Scanner(connection.getInputStream())){
			scanner.useDelimiter("\\A");
			response = scanner.hasNext()? scanner.next() : "";
		}
		
		return MarshalHelper.unmarshalJson(response, GatewayResponse.class);
	}
	
	public static URL getDiscordBaseURL() throws MalformedURLException{
		try{
			return new URL(BASE_URL + "/gateway");
		}
		catch(MalformedURLException e){
			throw e;
		}
	}
}
