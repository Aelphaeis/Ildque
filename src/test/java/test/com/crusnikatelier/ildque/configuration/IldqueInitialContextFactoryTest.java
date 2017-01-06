package test.com.crusnikatelier.ildque.configuration;

import static org.junit.Assert.*;

import org.junit.Test;

import com.crusnikatelier.ildque.configuration.IldqueInitialContextFactory;

public class IldqueInitialContextFactoryTest {

	@Test
	public void test() {
		
		IldqueInitialContextFactory idcf = new IldqueInitialContextFactory();
		System.out.println(idcf.getSqlite3DbConnectionString());
	}

}
