package com.crusnikatelier.ildque.driver;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.spi.InitialContextFactory;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.omg.CORBA.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.Bot;
import com.crusnikatelier.ildque.configuration.BotConfiguration;
import com.crusnikatelier.ildque.configuration.BotConfiguration.Settings;

public class Program {
	static {
		//Override the log4j2 settings
//		StringBuilder builder = new StringBuilder(System.getProperty("user.dir"));
//		builder.append("/log4j2.xml");
//		File confOverride = new File(builder.toString());
//		if(confOverride.exists() && confOverride.isFile()){
//			Configurator.initialize(null, confOverride.getAbsolutePath());
//		}
	}
	
	private static final Logger logger = LoggerFactory.getLogger(Program.class);
	private static final Options opts = getOptions();
	
	public static void main(String[] args) throws Throwable {
		try{
			//Use this to configure context factory
			Hashtable<String, String> environment = new Hashtable<String, String>();
			
			//Needed to parse options
			CommandLineParser parser = new DefaultParser();
			CommandLine line = parser.parse(opts, args);

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
			Context c = new InitialContext(environment);
			Bot bot = new Bot(environment);
			System.out.println(bot.getConfiguration().value(Settings.TOKEN));
		}
		catch(RuntimeException e){
			e.printStackTrace();
		}
		//new Bot().run();
//		long start = System.currentTimeMillis();
//		long duration  = System.currentTimeMillis() - start;
//		String msg = "Bot successfully started in {} millseconds with prefix '{}'";
//		logger.info(msg, duration, BotConfiguration.value(BotConfiguration.Settings.PREFIX));
	}
	
	
	
	static Options getOptions(){
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
