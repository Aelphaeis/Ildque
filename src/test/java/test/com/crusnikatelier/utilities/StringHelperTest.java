package test.com.crusnikatelier.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.crusnikatelier.utilities.StringHelper;

public class StringHelperTest {

	@Test
	public void singleCmdArgument() {
		String cmd = "logout";
		String[] args = StringHelper.translateCommandline(cmd);
		
		assertEquals(1, args.length);
		assertEquals(cmd, args[0]);
	}

}
