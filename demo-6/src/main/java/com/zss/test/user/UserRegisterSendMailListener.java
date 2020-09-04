package com.zss.test.user;

import com.zss.test.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 用户注册，发送邮件监听器
 */
@Component
public class UserRegisterSendMailListener implements EventListener<UserRegisterEvent> {

	@Override
	public void onEvent(UserRegisterEvent event) {
		System.out.println(String.format("监听到用户注册事件，给用户%s发送邮件成功。事件源名称：%s",event.getUserName(),event.getSource()));
	}
}
