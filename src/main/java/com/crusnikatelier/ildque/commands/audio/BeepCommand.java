package com.crusnikatelier.ildque.commands.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.cli.Options;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.audio.AudioPlayer;

public class BeepCommand implements BotCommand{
	
	public static final String BEEP_RESOURCE_LOCATION = "src/main/resources/beep.mp3";

	@Override
	public String getName() {
		return "beep";
	}

	@Override
	public Options getOptions() {
		Options options = new Options();
		options.addOption("", "Plays beep");
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		MessageReceivedEvent evt = (MessageReceivedEvent)event;
		AudioPlayer player = new AudioPlayer(evt.getMessage().getGuild());
		File beepFile = new File(BEEP_RESOURCE_LOCATION);
		try {
			player.queue(beepFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
