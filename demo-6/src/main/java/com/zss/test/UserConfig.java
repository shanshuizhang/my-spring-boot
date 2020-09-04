package com.zss.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class UserConfig {

	public static void main(String[] args) {
		/*ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/bean.xml");
		User user = (User)ctx.getBean("user");
		System.out.println(user);
		System.out.println("中国");*/

		Integer ii = null;
		BigDecimal bb = new BigDecimal(ii);
		System.out.println(bb);
	}

	public static class User{
		private String userName;

		private Integer age;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "User{" +
					"userName='" + userName + '\'' +
					", age=" + age +
					'}';
		}
	}
}
