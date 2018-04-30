package com.cruat.ildque.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cruat.ildque.bot.Ildque;

/**
 * Application Lifecycle Listener implementation class BotInitListener
 *
 */
public class BotInitListener implements ServletContextListener {

	Ildque bot;

    public void contextInitialized(ServletContextEvent sce)  { 
    	bot = new Ildque();
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
		bot.close();
    }
}
