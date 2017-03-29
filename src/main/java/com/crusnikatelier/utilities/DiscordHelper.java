package com.crusnikatelier.utilities;

import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public final class DiscordHelper {
	public static void sendMessage(IChannel channel, String msg){
		try {
			channel.sendMessage(msg);
		}
		catch (MissingPermissionsException e) {

		}
		catch (RateLimitException e) {
			try { 
				Thread.sleep(e.getRetryDelay());
				sendMessage(channel, msg);
			}
			catch(InterruptedException ie){
				return;
			}
		} 
		catch (DiscordException e) {
			String err = "Unable to send msg";
			throw new RuntimeException(err, e); 
		}
	}
	
	public static void sendMessage(MessageReceivedEvent evt, String msg){
		IChannel dest = evt.getMessage().getChannel();
		sendMessage(dest, msg);
	}
}
