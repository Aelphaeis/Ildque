package com.crusnikatelier.ildque.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.crusnikatelier.ildque.data.EntityBase;

@Entity
@Table(name="dnestnotice_subscribers")
public class DNestNoticeSubscriber extends EntityBase {

	@Id
	@Column(name="id")
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="sqlite", table="sqlite_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="sqliteTestTable")
	private long id;
	
	@Column(name="subscriber", nullable=false, unique=true)
	private long subscriber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(long subscriber) {
		this.subscriber = subscriber;
	}
	
	
}
