package test.com.crusnikatelier.ildque.configuration;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.configuration.BotConfiguration;

public class BotConfigurationTest {

	private static final Logger logger = LoggerFactory.getLogger(BotConfiguration.class);
	@Test
	public void prefixTest(){
		String prefix = String.valueOf(BotConfiguration.value(BotConfiguration.Settings.PREFIX));
		assertEquals("Ildque ", prefix);
		logger.debug("test complete");
	}
}
