package com.zss.test.user;

import com.zss.test.event.EventMulticaster;

public class UserRegisterService  {

	private EventMulticaster eventMulticaster;

	public void register(String userName){
		System.out.println(userName + "注册用户");

		//广播事件
		eventMulticaster.multicaster(new UserRegisterEvent(this,userName));
	}

	public EventMulticaster getEventMulticaster() {
		return eventMulticaster;
	}

	public void setEventMulticaster(EventMulticaster eventMulticaster) {
		this.eventMulticaster = eventMulticaster;
	}
}
