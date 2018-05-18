package com.cruat.ildque.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.bot.utilities.Serializer;

@XmlRootElement
public class Configuration {

	private static final Logger logger = LogManager.getLogger();
	public static void save(Configuration conf) {
		File file = new File(DEFAULT_LOCATION);
		try (Writer writer = new FileWriter(file)){
			Serializer.serialize(conf, writer);
			logger.trace("Wrote configuration to {}", file.getAbsolutePath());
		} catch (Exception e) {
			//this really ought to be impossible
			String err = "Unable to save configuration";
			throw new IllegalStateException(err, e);
		}
	}
	
	public static Configuration load() {
		File file = new File(DEFAULT_LOCATION);
		try (Reader reader = new FileReader(file)){
			Configuration conf = Serializer.deserialize(reader, Configuration.class);
			logger.trace("Read configuration from {}", file.getAbsolutePath());
			return conf;
		} catch (Exception e) {
			//this really ought to be impossible
			String err = "Unable to load configuration";
			throw new IllegalStateException(err, e);
		}
	}
	
	private static final String DEFAULT_LOCATION = "src/main/resource/configuration.xml";
	
	
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
