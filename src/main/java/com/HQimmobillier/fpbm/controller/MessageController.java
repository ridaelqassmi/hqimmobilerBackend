package com.HQimmobillier.fpbm.controller;

import com.HQimmobillier.fpbm.dto.MessageDto;
import com.HQimmobillier.fpbm.entity.Messages;
import com.HQimmobillier.fpbm.services.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping("api/message/receved")
    public List<Messages> getMessageThatAreSendToMe(){
        return messageService.getMessageThatAreSendToMe();
    }

    @GetMapping("api/message/send")
    public List<Messages> getMessageThatIsend(){
        return messageService.getAllMessagesThatISend();
    }

    @PostMapping("api/message/create")
    public Messages CreateMessage(@RequestBody MessageDto messageDto){
        System.out.println(messageDto.toString());
        return messageService.createMessage(messageDto);
    }


}
