package com.crusnikatelier.ildque.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.crusnikatelier.ildque.data.EntityBase;

@Entity
@Table(name="users")
@XmlRootElement
public class User extends EntityBase implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="sqlite", table="sqlite_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="sqliteTestTable")
	private long id;
	
	@Column(name="discord_id", length=40, nullable = false, unique = true)
	private String discordId;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDiscordId() {
		return discordId;
	}
	
	public void setDiscordId(String discordId) {
		this.discordId = discordId;
	}
}
