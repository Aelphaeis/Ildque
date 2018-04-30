package com.cruat.ildque.init;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cruat.ildque.config.Settings;

/**
 * Application Lifecycle Listener implementation class SystemIntegrityListener
 *
 */
public class SystemIntegrityListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg)  { 
    	Settings.LOGIN_TOKEN.value();
    }
	
    
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg)  { 
    }


}
