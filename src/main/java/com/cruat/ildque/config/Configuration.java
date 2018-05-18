package com.cruat.ildque.config;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.bot.utilities.Serializer;

@XmlRootElement
public class Configuration {

	private static final Logger logger = LogManager.getLogger();
	public static Configuration load() {
		ClassLoader l = Configuration.class.getClassLoader();
		try(Reader r = new InputStreamReader(l.getResourceAsStream(DEFAULT_LOCATION))) {
			Configuration conf = Serializer.deserialize(r, Configuration.class);
			logger.trace("Read configuration from classpath");
			return conf;
		} catch (Exception e) {
			//this really ought to be impossible
			String err = "Unable to load configuration";
			throw new IllegalStateException(err, e);
		}
	}
	
	private static final String DEFAULT_LOCATION = "configuration.xml";
	
	
	private String token;
	private String prefix;
	
	@XmlElement
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@XmlElement
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
