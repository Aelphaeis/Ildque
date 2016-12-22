package test.com.crusnikatelier.ildque;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersCommandTest {
	
	
	private static final Logger logger = LoggerFactory.getLogger(UsersCommandTest.class);

	@Test
	public void test() {
		Options group = new Options();
		Option o1 = new Option("o", "offline", true, "Flag for offline users");
		group.addOption(o1);
		
		StringWriter sWriter = new StringWriter(5000);
		PrintWriter pWriter = new PrintWriter(sWriter);
		
		HelpFormatter formatter = new HelpFormatter();
		formatter.printUsage(pWriter, 100, "help", group);
		
		assertEquals("usage: help [-o <arg>]", sWriter.toString().trim());
		logger.info(sWriter.toString());
	}

}
