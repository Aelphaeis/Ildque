package com.cruat.ildque.bot.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import com.cruat.ildque.bot.util.Strings;

public class StringsTest {
	private static final Logger logger = LogManager.getLogger();
	
	@Rule
	public TestName name = new TestName();

	@Before
	public void setup() {
		logger.info(name.getMethodName());
	}
	@Test
	public void translateCommandline_null_empty() {
		String [] result = Strings.translateCommandline(null);
		assertNotNull(result);
		assertEquals(0, result.length);
	}
	
	@Test
	public void translateCommandline_empty_empty() {
		String [] result = Strings.translateCommandline("");
		assertNotNull(result);
		assertEquals(0, result.length);
	}

	@Test
	public void translateCommandline_nameOnly_nameElement() {
		String [] result = Strings.translateCommandline("name");
		assertNotNull(result);
		assertEquals(1, result.length);
		assertEquals("name", result[0]);
	}
	
	@Test
	public void translateCommandline_twoTokensSingles_Tokenized() {
		String [] result = Strings.translateCommandline("name 'test quote'");
		assertNotNull(result);
		assertEquals(2, result.length);
		assertEquals("name", result[0]);
		assertEquals("test quote", result[1]);
	}
	
	@Test
	public void translateCommandline_twoTokensDoubles_Tokenized() {
		String [] result = Strings.translateCommandline("name \"test quote\"");
		assertNotNull(result);
		assertEquals(2, result.length);
		assertEquals("name", result[0]);
		assertEquals("test quote", result[1]);
	}
	
	@Test
	public void translateCommandline_spaceBeforeQuote_spaceIgnored() {
		String [] result = Strings.translateCommandline(" 'test'");
		assertNotNull(result);
		assertEquals(1, result.length);
		assertEquals("test", result[0]);
	}
	
	@Test
	public void translateCommandline_spaceAfterQuote_spaceIgnored() {
		String [] result = Strings.translateCommandline("'test' ");
		assertNotNull(result);
		assertEquals(1, result.length);
		assertEquals("test", result[0]);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void translateCommandline_singleQuote_exception() {
		Strings.translateCommandline("'");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void translateCommandline_doubleQuote_exception() {
		Strings.translateCommandline("\"");
	}

	
}
