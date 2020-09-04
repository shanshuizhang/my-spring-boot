package com.zss.test.user;

import com.zss.test.event.EventListener;
import com.zss.test.event.EventMulticaster;
import com.zss.test.event.SimpleEventMulticaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan
public class MainConfig0 {

	/**
     * 注册一个事件广播器
	 * @param listeners
     * @return
     */
	@Bean
	@Autowired(required = false)
	public EventMulticaster eventMulticaster(List<EventListener> listeners){
		EventMulticaster eventMulticaster = new SimpleEventMulticaster();
		if(listeners != null){
			listeners.stream().forEach(eventMulticaster::addEventListener);
		}
		return eventMulticaster;
	}

	/**
     * 注册用户服务
	 * @param eventMulticaster
     * @return
     */
	@Bean
	public UserRegisterService userRegisterService(EventMulticaster eventMulticaster){
		UserRegisterService registerService = new UserRegisterService();
		registerService.setEventMulticaster(eventMulticaster);
		return registerService;
	}
}
