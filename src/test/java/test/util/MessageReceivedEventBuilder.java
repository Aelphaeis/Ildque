package test.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

public class MessageReceivedEventBuilder {
	
	final MessageReceivedEvent event;
	
	
	public MessageReceivedEventBuilder() {
		event = mock(MessageReceivedEvent.class);
		when(event.getMessage()).thenReturn(mock(IMessage.class));
		when(event.getMessage().getChannel()).thenReturn(mock(IChannel.class));
	}
	
	public MessageReceivedEventBuilder content(String content) {
		when(event.getMessage().getContent()).thenReturn(content);
		return this;
	} 
	
	public MessageReceivedEvent build() {
		return event;
	}
}
