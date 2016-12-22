package test.com.crusnikatelier.ildque.commands;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.crusnikatelier.ildque.BotCommandTextHandler;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RateLimitException;

public class TerminateCommandTest {

	@Test
	public void test() throws RateLimitException, DiscordException {
		
		//Set up test
		final BooleanHolder isLoggedOut = new BooleanHolder();
		String content = "Ildque terminate";
		MessageReceivedEvent mre = stubMessageReceivedEvent(content);
		
		IDiscordClient client = Mockito.mock(IDiscordClient.class);
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				isLoggedOut.bool = true;
				return null;
			}
		}).when(client).logout();
		
		Mockito.when(mre.getClient()).thenReturn(client);
		
		//Actually run the test
		BotCommandTextHandler handler = new BotCommandTextHandler(null);
		handler.handle(mre);
		
		//Check the result
		assertEquals(true, isLoggedOut.bool);
	}
	
	private MessageReceivedEvent stubMessageReceivedEvent(String content){
		
		IUser author = Mockito.mock(IUser.class);
		Mockito.when(author.getID()).thenReturn("1234567890");
		
		IMessage msg = Mockito.mock(IMessage.class);
		Mockito.when(msg.getContent()).thenReturn(content); 
		Mockito.when(msg.getAuthor()).thenReturn(author);
		
		MessageReceivedEvent mre = Mockito.mock(MessageReceivedEvent.class);
		Mockito.when(mre.getMessage()).thenReturn(msg);
		
		return mre;
	}
	
	public class BooleanHolder {
		boolean bool;
	}
	

}
