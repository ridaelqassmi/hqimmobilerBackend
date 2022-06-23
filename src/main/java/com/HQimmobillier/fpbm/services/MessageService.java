package com.HQimmobillier.fpbm.services;


import com.HQimmobillier.fpbm.dto.MessageDto;
import com.HQimmobillier.fpbm.entity.Messages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<Messages> getMessageThatAreSendToMe();
    public List<Messages> getAllMessagesThatISend();
    public Messages createMessage(MessageDto msg);
}
