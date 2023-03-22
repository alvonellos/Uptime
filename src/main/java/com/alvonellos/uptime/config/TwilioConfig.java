package com.alvonellos.uptime.config;

;
import com.alvonellos.uptime.exceptions.UptimeAPIException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log
public class TwilioConfig implements InitializingBean  {
    @Value("${uptime.client.twilio.account-sid}")
    public String accountSid;

    @Value("${uptime.client.twilio.auth-token}")
    public String authToken;

    @Value("${uptime.client.twilio.messaging-service-sid}")
    public String messagingServiceSid;

    @Value("${uptime.client.twilio.phone-number}")
    public String phoneNumber;

    @Value("${uptime.client.twilio.notification-phone-number}")
    public String notificationPhoneNumber;

    @Value("${uptime.client.twilio.enable-notifications}")
    public Boolean enableNotifications;


    @Override
    public void afterPropertiesSet() throws Exception {
        if(accountSid == null || authToken == null) {
            throw new UptimeAPIException("Twilio accountSid and authToken must be set") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }

        log.config("Twilio account sid: " + accountSid);
        log.config("Twilio auth token: " + authToken);
        log.config("Twilio messaging service sid: " + messagingServiceSid);
        log.config("Twilio phone number: " + phoneNumber);
        log.config("Twilio notification phone number: " + notificationPhoneNumber);
        log.config("Twilio enable notifications: " + enableNotifications);
        log.info("Twilio config initialized");
    }
}
