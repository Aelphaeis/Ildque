package test.com.crusnikatelier.utilities;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.crusnikatelier.discord.pojos.GatewayResponse;
import com.crusnikatelier.utilities.MarshalHelper;

public class MarshalHelperTest {

	@Test
	public void GatewayResponseUnmarshallTest() throws JAXBException {
		String json = "{\"url\" : \"wss://gateway.discord.gg/\"}";
		GatewayResponse resp = (GatewayResponse) MarshalHelper.unmarshalJson(json, GatewayResponse.class);
		
		assertNotNull(resp);
		assertEquals("wss://gateway.discord.gg", resp.getUrl());
	}

}
