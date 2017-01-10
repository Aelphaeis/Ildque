package test.com.crusnikatelier.ildque.data.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.crusnikatelier.ildque.data.entities.User;

public class UserTest {

	@Test
	public void toStringTest() {
		User user = new User();
		String result = user.toString();
		assertTrue(result.contains("xml"));
	}
}
