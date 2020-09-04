package com.zss.test.user;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(MainConfig0.class);
		context.refresh();

		UserRegisterService registerService = context.getBean(UserRegisterService.class);
		registerService.register("霍元甲");
	}
}
