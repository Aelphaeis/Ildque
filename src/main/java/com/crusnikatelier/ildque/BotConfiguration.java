package com.crusnikatelier.ildque;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BotConfiguration {
	
	private String commandPrefix;
	
	public BotConfiguration() {
		//Default bot configuration
		setCommandPrefix("Ilqdue ");
	}

	@XmlElement
	public String getCommandPrefix() {
		return commandPrefix;
	}

	public void setCommandPrefix(String commandPrefix) {
		this.commandPrefix = commandPrefix;
	}
}
