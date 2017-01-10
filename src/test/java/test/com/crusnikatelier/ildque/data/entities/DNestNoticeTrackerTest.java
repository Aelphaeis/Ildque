package test.com.crusnikatelier.ildque.data.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.crusnikatelier.ildque.data.entities.DNestNoticeTracker;

public class DNestNoticeTrackerTest {

	@Test
	public void toStringTest() {
		DNestNoticeTracker tracker = new DNestNoticeTracker();
		String result = tracker.toString();
		assertTrue(result.contains("xml"));
	}

}
