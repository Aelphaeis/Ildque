package com.crusnikatelier.ildque.driver;

import com.crusnikatelier.ildque.Bot;

public class Program {
	//private static final Logger logger = LoggerFactory.getLogger(Program.class);
	public static void main(String[] args) throws Throwable {
		Bot bot = new Bot(args);
		bot.run();
	}
}
