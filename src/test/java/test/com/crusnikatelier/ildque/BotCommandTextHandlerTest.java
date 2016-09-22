package test.com.crusnikatelier.ildque;

import static org.junit.Assert.*;

import org.junit.Test;




public class BotCommandTextHandlerTest {

	@Test
	public void test() throws ClassNotFoundException {
		Class.forName("com.crusnikatelier.ildque.commands" + "." + "package-info");
		Package pkg = Package.getPackage("com.crusnikatelier.ildque.commands");
		assertNotNull(pkg);
	}

}
