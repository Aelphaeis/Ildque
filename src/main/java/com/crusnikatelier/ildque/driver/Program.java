package com.crusnikatelier.ildque.driver;

import java.io.File;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.Bot;
import com.crusnikatelier.ildque.configuration.BotConfiguration;
import com.crusnikatelier.ildque.configuration.BotConfiguration.Settings;

public class Program {
	static {
		//Override the log4j2 settings
		StringBuilder builder = new StringBuilder(System.getProperty("user.dir"));
		builder.append("/log4j2.xml");
		File confOverride = new File(builder.toString());
		if(confOverride.exists() && confOverride.isFile()){
			Configurator.initialize(null, confOverride.getAbsolutePath());
		}
	}
	
	private static final Logger logger = LoggerFactory.getLogger(Program.class);
	public static void main(String[] args) throws Throwable {
		long start = System.currentTimeMillis();
		new Bot(args).run();
		long duration  = System.currentTimeMillis() - start;
		String msg = "Bot successfully started in {} millseconds with prefix '{}'";
		logger.info(msg, duration, BotConfiguration.value(BotConfiguration.Settings.PREFIX));
	}
	
	
	Options getOptions(){
		Settings[] settings = BotConfiguration.Settings.values();
		Options options = new Options();
		for(Settings setting : settings){
			String shortname = setting.getShortName();
			shortname = shortname.replace("/", ".");
			options.addOption(new Option(null, shortname, true, null));
		}
		return options;
	}
}
