package com.crusnikatelier.ildque.data.daos;

import com.crusnikatelier.ildque.data.DataAccessObject;
import com.crusnikatelier.ildque.data.entities.DNestNoticeSubscriber;

public interface DNestNoticeSubscriberDAO extends DataAccessObject<DNestNoticeSubscriber>{
	DNestNoticeSubscriber findByUserDiscordId(String id);
}
