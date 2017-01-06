package test.com.crusnikatelier.ildque.configuration;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.configuration.Settings;

public class BotConfigurationTest {

	private static final Logger logger = LoggerFactory.getLogger(Settings.class);
	@Test
	public void prefixTest(){
		String prefix = String.valueOf(Settings.PREFIX.value());
		assertEquals("Ildque ", prefix);
		logger.debug("test complete");
	}
}
