package com.crusnikatelier.ildque.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.discord.DiscordClient;

public class Program {
	
	public static void main(String[] args) throws Throwable {
		Logger logger = LoggerFactory.getLogger(Program.class);
		logger.info("Initializing Ilqdue");
		DiscordClient dc = new DiscordClient();
		logger.info("Starting Ilqdue");
		
		logger.info("Ilqdue started");
		
	}
}
