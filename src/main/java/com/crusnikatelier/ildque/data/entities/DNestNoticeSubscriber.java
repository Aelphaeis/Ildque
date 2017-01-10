package com.crusnikatelier.ildque.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.crusnikatelier.ildque.data.EntityBase;

@Entity(name="dnestnotice_subscribers")
@XmlRootElement
public class DNestNoticeSubscriber extends EntityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="sqlite", table="sqlite_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="sqliteTestTable")
	private long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subscriber", referencedColumnName="id", unique=true, insertable=false, nullable=false, updatable=false)
	private User subscriber;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(User sub) {
		this.subscriber = sub;
	}

	/*
	public long getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(long subscriber) {
		this.subscriber = subscriber;
	}
	*/
}
