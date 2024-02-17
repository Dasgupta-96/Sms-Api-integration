package com.twillioexample.Controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twillioexample.payload.SmsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;
    @PostMapping("/send-sms")
    // http://localhost:8080/send-sms

    public ResponseEntity<String> sendSms(@RequestBody SmsRequest smsRequest) {

        Twilio.init(accountSid, authToken);

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(smsRequest.getToPhoneNumber()),
                new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                smsRequest.getMessage()
        ).create();
return ResponseEntity.ok("Sms sent successfully. "+ message.getSid());
        }


    }

