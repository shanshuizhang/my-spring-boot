package com.zss.test.user;

import com.zss.test.event.AbstractEvent;

/**
 * 用户注册事件
 */
public class UserRegisterEvent extends AbstractEvent {

	private String userName;

	public UserRegisterEvent(Object source,String userName) {
		super(source);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
