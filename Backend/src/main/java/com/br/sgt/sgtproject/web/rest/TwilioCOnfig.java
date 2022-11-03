package com.br.sgt.sgtproject.web.rest;

import com.twilio.Twilio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Getter
@Setter
public class TwilioCOnfig {

    @Value("${TWILIO_ACCOUNT_SID}")
    private  String ACCOUNT_SID;
    @Value("${TWILIO_AUTH_TOKEN}")
    private  String AUTH_TOKEN;

    @PostConstruct
    public void init(){
        Twilio.init(getACCOUNT_SID(), getAUTH_TOKEN());
    }
}
