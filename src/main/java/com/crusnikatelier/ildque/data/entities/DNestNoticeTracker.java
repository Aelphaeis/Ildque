package com.crusnikatelier.ildque.data.entities;

import java.util.Date;

public class DNestNoticeTracker {
	
	private long id;
	private long noticeNumber;
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
