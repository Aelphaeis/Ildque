package test.com.crusnikatelier.utilities;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.discord.pojos.GatewayResponse;
import com.crusnikatelier.discord.pojos.Operation;
import com.crusnikatelier.utilities.MarshalHelper;

public class MarshalHelperTest {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void GatewayResponseUnmarshallTest() throws JAXBException {
		logger.debug("Starting GatewayResponseUnmarshallTest")
		;
		String json = "{\"url\" : \"wss://gateway.discord.gg/\"}";
		GatewayResponse resp = (GatewayResponse) MarshalHelper.unmarshalJson(json, GatewayResponse.class);
		
		assertNotNull(resp);
		assertEquals("wss://gateway.discord.gg/", resp.getUrl());
	}
	
	@Test
	public void OperationMarshallerTest() throws JAXBException{
		Operation op = new Operation();
		op.setOp(2);
		
		String json = MarshalHelper.marshallJson(op);
		logger.trace(json);
		
		op = MarshalHelper.unmarshalJson(json, Operation.class);
		
		assertNotNull(op);
		assertEquals(2, op.getOp());
		
	}
	
}
