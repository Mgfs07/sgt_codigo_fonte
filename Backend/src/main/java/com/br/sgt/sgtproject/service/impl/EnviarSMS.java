package com.br.sgt.sgtproject.service.impl;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class EnviarSMS {

    public void sendSMS() {
        Message.creator(new PhoneNumber("+5527999414249"), new PhoneNumber("+17078731237"), "\nO PA deu mole, se eu fosse ele nao teria dado esse mole \n#PAS").create();
    }
}
