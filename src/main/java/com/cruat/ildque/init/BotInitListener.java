package com.cruat.ildque.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cruat.ildque.bot.Ildque;
import com.cruat.ildque.cfg.Configuration;

/**
 * Application Lifecycle Listener implementation class BotInitListener
 *
 */
public class BotInitListener implements ServletContextListener {

	Ildque bot;

    public void contextInitialized(ServletContextEvent sce)  { 
    	Configuration conf =  Configuration.load();
    	bot = new Ildque(conf);
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	if(bot != null) {
    		bot.close();
    	}
    }
}
