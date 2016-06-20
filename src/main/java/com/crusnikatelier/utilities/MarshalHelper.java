package com.crusnikatelier.utilities;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import com.crusnikatelier.discord.pojos.*;

import org.eclipse.persistence.jaxb.JAXBContextProperties;

public class MarshalHelper {
	public static final String MEDIA_TYPE = "application/json";
	
	public static Object unmarshalJson(String json, Class<?> clazz) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		unmarshaller.setProperty(JAXBContextProperties.MEDIA_TYPE, MEDIA_TYPE);
		unmarshaller.setProperty(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
		
		StringReader reader = new StringReader(json);
		Object result = unmarshaller.unmarshal(reader);
		return result;
	}
	
	public static String marshallJson(Object obj) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		
		marshaller.setProperty(JAXBContextProperties.MEDIA_TYPE, MEDIA_TYPE);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		Writer writer = new StringWriter();
		marshaller.marshal(obj, writer);
		return writer.toString();
		
	}
}
