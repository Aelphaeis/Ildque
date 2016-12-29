package com.crusnikatelier.ildque.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.Bot;
import com.crusnikatelier.ildque.configuration.BotConfiguration;

public class Program {
	private static final Logger logger = LoggerFactory.getLogger(Program.class);
	public static void main(String[] args) throws Throwable {
		
		long start = System.currentTimeMillis();
		new Bot(args).run();
		long duration  = System.currentTimeMillis() - start;
		String msg = "Bot successfully started in {} millseconds with prefix '{}'";
		logger.info(msg, duration, BotConfiguration.value(BotConfiguration.Settings.PREFIX));
	}
}
