package com.alvonellos.uptime;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UptimeApplication implements InitializingBean {

	public static void main(String[] args) {
		SpringApplication.run(UptimeApplication.class, args);
	}

	@Override
    public void afterPropertiesSet() throws Exception {
		//todo: add logging and a text message to the console
    }

}
