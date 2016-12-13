package test.utilities;

import sx.blah.discord.handle.obj.IMessage;

public class Stubs {
	
	public static final String NOT_IMPLEMENTED = "Operation has not been implemented";
	
	Stubs(){ }

	public static IMessage StubMessage(){
		return new StubMessage();
	}
}
