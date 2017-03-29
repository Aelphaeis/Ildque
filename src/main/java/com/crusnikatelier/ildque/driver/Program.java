package com.crusnikatelier.ildque.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.Bot;
import com.crusnikatelier.ildque.configuration.BotConfiguration;
import com.crusnikatelier.ildque.configuration.BotConfiguration.Settings;

public class Program {
	private static final Logger logger = LoggerFactory.getLogger(Program.class);
	private static final Options opts = getOptions();
	
	public static void main(String[] args) throws Throwable {
		//Use this to configure context factory
		Hashtable<String, String> environment = new Hashtable<String, String>();
		
		//Needed to parse options
		CommandLineParser parser = new DefaultParser();
		CommandLine line = parser.parse(opts, args);
		
		Object loggingConfig = line.getParsedOptionValue("configuration.log4j2");
		if(loggingConfig != null){
			processLogging(loggingConfig.toString());
		}

		//Add settings to the environment
		for(Settings setting : BotConfiguration.Settings.values()){
			//Transform short name to a name usable in the argument parameters
			String shortname = setting.getShortName();
			shortname = shortname.replace("/", "."); 
			
			//Get the value of this specific option
			Object optValue = line.getParsedOptionValue(shortname);

			//If we have a value, add it to environment hash
			if(optValue != null){
				environment.put(shortname, optValue.toString());
			}
		}
		
		Bot bot = new Bot(environment);
		bot.login();
	}
	
	static Options getOptions(){
		Settings[] settings = BotConfiguration.Settings.values();
		Options options = new Options();
		for(Settings setting : settings){
			String shortname = setting.getShortName();
			shortname = shortname.replace("/", ".");
		
			Option opt = new Option(null, shortname, true, null);
			opt = customizeOption(setting, opt);
			options.addOption(opt);
		}
		return options;
	}

	static Option customizeOption(Settings setting, Option opt){
		switch(setting){
			case TOKEN:
				opt.setRequired(true);
				break;
			default:
				break;
		}
		return opt;
	}
	
	static void processLogging(String config){
		try {
			FileInputStream fis = new FileInputStream(new File(config));
			ConfigurationSource source = new ConfigurationSource(fis);
			Configurator.initialize(null, source);
			logger.trace("test");
			System.out.println("~");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
