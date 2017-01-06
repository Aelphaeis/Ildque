package com.crusnikatelier.ildque.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="dnestnotice_tracker")
public class DNestNoticeTracker {
	@Id
	@Column(name="id")
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="sqlite", table="sqlite_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="sqliteTestTable")
	private long id;
	
	@Column(name="notice_number")
	private long noticeNumber;
	
	@Column(name="notification_date")
	private Date NotificationDate;
	
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
	
	public Date getNotificationDate() {
		return NotificationDate;
	}
	
	public void setNotificationDate(Date notificationDate) {
		NotificationDate = notificationDate;
	}

}
