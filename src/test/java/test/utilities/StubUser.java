package test.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.Presences;
import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class StubUser implements IUser {
	
	String id;
	String name;
	Status status;
	Presences presences;
	String discriminator;
	
	List<IRole> roles;
	
	public StubUser() {
		roles = new ArrayList<IRole>();
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public IDiscordClient getClient() {
		throw new UnsupportedOperationException(Stubs.NOT_IMPLEMENTED);
	}

	@Override
	public IUser copy() {
		StubUser user = new StubUser();
		user.name = getName();
		user.id = getID();
		user.status = getStatus();
		user.presences = getPresence();
		user.discriminator = getDiscriminator();
		
		user.roles.addAll(roles);
		return user;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public String getAvatar() {
		throw new UnsupportedOperationException(Stubs.NOT_IMPLEMENTED);
	}

	@Override
	public String getAvatarURL() {
		throw new UnsupportedOperationException(Stubs.NOT_IMPLEMENTED);
	}

	@Override
	public Presences getPresence() {
		return presences;
	}

	@Override
	public String getDisplayName(IGuild guild) {
		throw new UnsupportedOperationException(Stubs.NOT_IMPLEMENTED);
	}

	@Override
	public String mention() {
		return "@" + getName() + "#" + discriminator;
	}

	@Override
	public String mention(boolean mentionWithNickname) {
		throw new UnsupportedOperationException(Stubs.NOT_IMPLEMENTED);
	}

	@Override
	public String getDiscriminator() {
		return discriminator;
	}

	@Override
	public List<IRole> getRolesForGuild(IGuild guild) {
		return roles;
	}

	@Override
	public Optional<String> getNicknameForGuild(IGuild guild) {
		return  Optional.empty();
	}

	@Override
	public boolean isBot() {
		return false;
	}

	@Override
	public void moveToVoiceChannel(IVoiceChannel newChannel)
			throws DiscordException, RateLimitException, MissingPermissionsException {
		throw new UnsupportedOperationException(Stubs.NOT_IMPLEMENTED);
	}

	@Override
	public List<IVoiceChannel> getConnectedVoiceChannels() {
		//Assume the user isn't even in voice chat
		return new ArrayList<IVoiceChannel>();
	}

	@Override
	public IPrivateChannel getOrCreatePMChannel() throws RateLimitException, DiscordException {
		throw new UnsupportedOperationException(Stubs.NOT_IMPLEMENTED);
	}

	@Override
	public boolean isDeaf(IGuild guild) {
		//assume the user isn't even in the guild voice chat
		return false;
	}

	@Override
	public boolean isMuted(IGuild guild) {
		//assume the user isn't even in the guild voice chat
		return false;
	}

	@Override
	public boolean isDeafLocally() {
		return false;
	}

	@Override
	public boolean isMutedLocally() {
		return false;
	}

	@Override
	public void addRole(IRole role) throws MissingPermissionsException, RateLimitException, DiscordException {
		boolean hasRole = false;
		for(IRole r : roles){
			hasRole = hasRole & r.getID().equals(role.getID());
		}
		if(!hasRole)
			roles.add(role);
	}

	@Override
	public void removeRole(IRole role) throws MissingPermissionsException, RateLimitException, DiscordException {
		Iterator<IRole> it = roles.iterator();
		while(it.hasNext()){
			IRole r = it.next();
			if(r.getID().equals(role)){
				it.remove();
			}
		}
	}
}
