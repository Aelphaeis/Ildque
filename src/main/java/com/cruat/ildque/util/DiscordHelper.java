package com.cruat.ildque.util;

import com.cruat.ildque.exceptions.IldqueRuntimeException;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class DiscordHelper {
	public static void sendMessage(IChannel channel, String msg){
		try {
			channel.sendMessage(msg);
		}
		catch (MissingPermissionsException e) {
			//you can't really do anything here.
		}
		catch (RateLimitException e) {
			try { 
				Thread.sleep(e.getRetryDelay());
				sendMessage(channel, msg);
			}
			catch(InterruptedException ie){
				//should never happen.
				Thread.currentThread().interrupt();
				return;
			}
		} 
		catch (DiscordException e) {
			String err = "Unable to send msg";
			throw new IldqueRuntimeException(err, e); 
		}
	}
	
	public static void sendMessage(MessageReceivedEvent evt, String msg){
		IChannel dest = evt.getMessage().getChannel();
		sendMessage(dest, msg);
	}
	
	public static void sendCodeMessage(MessageReceivedEvent evt, String msg) {
		sendMessage(evt, "```" + msg + "```");
	}
	
	private DiscordHelper() {
		
	}
}
