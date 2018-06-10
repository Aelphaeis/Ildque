package test.util;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

public class MessageReceivedEventBuilder {
	private static final Logger logger = LogManager.getLogger();
	public static final Answer<IMessage> SEND_MSG_LOGGER;
	static {
		SEND_MSG_LOGGER = new Answer<IMessage>() {
			@Override
			public IMessage answer(InvocationOnMock invocation) throws Throwable {
				logger.info(invocation.getArgumentAt(0, String.class));
				return null;
			}
		};
	}

	final MessageReceivedEvent event;
	final IChannel channel;

	public MessageReceivedEventBuilder() {
		channel = mock(IChannel.class);
		event = mock(MessageReceivedEvent.class);
		when(event.getMessage()).thenReturn(mock(IMessage.class));
		when(event.getMessage().getChannel()).thenReturn(channel);
	}

	public MessageReceivedEventBuilder content(String content) {
		when(event.getMessage().getContent()).thenReturn(content);
		return this;
	}

	public MessageReceivedEventBuilder onSendMessage(Answer<IMessage> ans) {
		doAnswer(ans).when(channel).sendMessage(anyString());
		return this;
	}

	public MessageReceivedEvent build() {
		return event;
	}
}
