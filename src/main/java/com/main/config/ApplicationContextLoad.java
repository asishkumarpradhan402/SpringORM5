package com.main.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextLoad {

	private static ApplicationContext applicationContext;

	private ApplicationContextLoad() throws IllegalAccessException {
		throw new IllegalAccessException("Unable to create explicit object");
	}

	public static ApplicationContext getXmlApplicationContext() {
		synchronized (ApplicationContextLoad.class) {
			if (applicationContext != null) {
				return applicationContext;
			}
		}
		applicationContext = new ClassPathXmlApplicationContext("config.xml");
		return applicationContext;
	}

	public static ApplicationContext getAnnotationConfigApplicationContext() {
		synchronized (ApplicationContextLoad.class) {
			if (applicationContext != null) {
				return applicationContext;
			}
		}
		applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
		return applicationContext;
	}
}
