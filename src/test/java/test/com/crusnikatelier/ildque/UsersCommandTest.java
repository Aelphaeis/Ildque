package test.com.crusnikatelier.ildque;

import static org.junit.Assert.*;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Test;

@SuppressWarnings("unused")
public class UsersCommandTest {

	@Test
	public void test() {
		Options group = new Options();
		Option o1 = new Option("o", "offline", true, "Flag for offline users");
		group.addOption(o1);
		
		
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("help", group);	
	}

}
