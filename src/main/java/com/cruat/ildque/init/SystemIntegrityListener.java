package com.cruat.ildque.init;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.config.Configuration;

/**
 * Application Lifecycle Listener implementation class SystemIntegrityListener
 *
 */
public class SystemIntegrityListener implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger();

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg)  { 
		Configuration conf = Configuration.load();
    	logger.info(conf.getPrefix());
    }
	
    
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg)  { 
    }


}
