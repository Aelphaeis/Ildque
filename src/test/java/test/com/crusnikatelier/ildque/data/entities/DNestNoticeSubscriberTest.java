package test.com.crusnikatelier.ildque.data.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.crusnikatelier.ildque.data.entities.DNestNoticeSubscriber;


public class DNestNoticeSubscriberTest {

	@Test
	public void toStringTest() {
		DNestNoticeSubscriber sub = new DNestNoticeSubscriber();
		String result = sub.toString();
		assertTrue(result.contains("xml"));
	}

}
