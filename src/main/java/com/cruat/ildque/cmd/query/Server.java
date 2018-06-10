package com.cruat.ildque.cmd.query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import sx.blah.discord.handle.obj.IGuild;

@Entity
public class Server implements Queryable {
	String id;
	String name;
	String icon;
	String iconUrl;
	
	public Server() {
		
	}
	
	public Server(IGuild guild) {
		this();
		setName(guild.getName());
		setIcon(guild.getIcon());
		setId(guild.getStringID());
		setIconUrl(guild.getIconURL());
	}
	
	@Id
	@Column
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Column
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
}
