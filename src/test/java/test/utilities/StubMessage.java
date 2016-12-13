package test.utilities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class StubMessage implements IMessage{

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDiscordClient getClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMessage copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IChannel getChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUser getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime getTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IUser> getMentions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IRole> getRoleMentions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IChannel> getChannelMentions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attachment> getAttachments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEmbedded> getEmbedded() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reply(String content) throws MissingPermissionsException, RateLimitException, DiscordException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMessage edit(String content) throws MissingPermissionsException, RateLimitException, DiscordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean mentionsEveryone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete() throws MissingPermissionsException, RateLimitException, DiscordException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<LocalDateTime> getEditedTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPinned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IGuild getGuild() {
		// TODO Auto-generated method stub
		return null;
	}

}
