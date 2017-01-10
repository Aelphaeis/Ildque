package com.crusnikatelier.ildque.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.crusnikatelier.ildque.data.EntityBase;

@Entity(name="dnestnotice_tracker")
@XmlRootElement
public class DNestNoticeTracker extends EntityBase implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="sqlite", table="sqlite_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="sqliteTestTable")
	private long id;
	
	@Column(name="notice_number")
	private long noticeNumber;
	
	@Column(name="notice_date")
	private Date noticeDate;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getNoticeNumber() {
		return noticeNumber;
	}
	
	public void setNoticeNumber(long noticeNumber) {
		this.noticeNumber = noticeNumber;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
}
