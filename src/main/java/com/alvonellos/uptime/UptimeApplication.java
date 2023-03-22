package com.alvonellos.uptime;

import com.alvonellos.uptime.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan("com.alvonellos.uptime")
public class UptimeApplication implements InitializingBean {

	@Autowired
	TwilioConfig twilioConfig;

	public static void main(String[] args) {
		SpringApplication.run(UptimeApplication.class, args);
	}

	@Override
    public void afterPropertiesSet() throws Exception {
		//todo: add logging and a text message to the console
    }

}
