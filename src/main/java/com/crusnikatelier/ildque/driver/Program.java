package com.crusnikatelier.ildque.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class Program {
	
	public static void main(String[] args) throws Throwable {
		if(args.length > 0){
			IDiscordClient client = new ClientBuilder().withToken(args[0]).login();
		}
		
	}
}
