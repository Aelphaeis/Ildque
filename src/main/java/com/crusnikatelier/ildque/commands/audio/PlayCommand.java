package com.crusnikatelier.ildque.commands.audio;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;
import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoFileInfo;
import com.github.axet.vget.info.VideoInfo;
import com.github.axet.vget.info.VideoInfo.States;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.audio.AudioPlayer;

public class PlayCommand implements BotCommand{
	private static final Logger logger = LoggerFactory.getLogger(PlayCommand.class);
	Options opts;
	
	public PlayCommand(){
		opts = new Options();
		opts.addOption(new Option("u", "url", true, "link to play (Only supports youtube)" ));
	}
	
	@Override
	public String getName() {
		return "play";
	}

	@Override
	public Options getOptions() {
		return opts;
	}

	@Override
	public void execute(Event event, String[] argv) {
		MessageReceivedEvent evt = (MessageReceivedEvent)event;
		VideoInfo info = resolveVideoInfo(argv);
		new Thread(new  VideoPlayer(evt, info)).start();
	}
	
	public VideoInfo resolveVideoInfo(String[] args){
		try{
			// see https://github.com/axet/vget
			CommandLineParser parser = new DefaultParser();
			CommandLine line = parser.parse(getOptions(), args);
			
			if(line.hasOption('u')){
				String value = line.getOptionValue('u');
				if(value == null){
					String msg = "Invalid url";
					throw new IllegalStateException(msg);
				}
				
				
				VGetParser vgp = VGet.parser(new URL(value));
				VideoInfo info = vgp.info(new URL(value));
				return info;
			}
			else{
				throw new IllegalStateException("Unable to process command");
			}
		}
		catch(ParseException e){
			logger.error("Unable to parse url", e);
			throw new IllegalStateException("Unexpected error occurred");
		}
		catch(MalformedURLException e){
			throw new IllegalStateException("Invalid URL");
		}
	}
	
	public class VideoPlayer implements Runnable{
		VideoInfo i;
		MessageReceivedEvent evt;

		public VideoPlayer(MessageReceivedEvent e, VideoInfo vi) {
			i = vi;
			evt = e;
		}
		
		@Override
		public void run() {
			
			try {
				File tempDir = Files.createTempDirectory("ildque").toFile();
				logger.debug("temporary directory : " + tempDir.getAbsolutePath());
				VGet v = new VGet(i, tempDir);
				v.download();
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
			
			while(i.getState() != States.DONE){
				try {
					Thread.sleep(2000);
				} 
				catch (InterruptedException e) {
					return;
				}
			}
			
			try{
				IVoiceChannel c = moveBotToUser();
				AudioPlayer ap = new AudioPlayer(c.getGuild());
				for(VideoFileInfo vfi :  i.getInfo()){
					logger.debug("attempting to queue {}", vfi.targetFile.getAbsolutePath());
					ap.queue(vfi.targetFile);
				}
				//LF audio support!
			}
			catch(MissingPermissionsException e){ 
				String msg = "Unable to join voice channel : insufficent permissions";
				throw new IllegalStateException(msg, e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		IVoiceChannel moveBotToUser() throws MissingPermissionsException{
			IUser author = evt.getMessage().getAuthor();
			List<IVoiceChannel> authChannels = author.getConnectedVoiceChannels();
			List<IVoiceChannel> botChannels = evt.getClient().getConnectedVoiceChannels();

			if(authChannels.size() != 1){
				String msg = "Unable to find requester in voice channel";
				throw new IllegalStateException(msg);
			}
			
			if(botChannels.isEmpty()){
				//Join the requesters channel
				authChannels.get(0).join();
				return authChannels.get(0);
			}
			
			//If the bot isn't in any channels the author is in, join requesters channel
			List<IVoiceChannel> channels = botChannels.stream()
				.filter(p -> p.getID().equals(authChannels.get(0).getID()))
				.collect(Collectors.toList());
			if(channels.isEmpty()){
				authChannels.get(0).join();
				return authChannels.get(0);
			}
			return channels.get(0);
		}
	}
}
