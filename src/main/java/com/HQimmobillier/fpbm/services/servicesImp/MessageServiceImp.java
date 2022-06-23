package com.HQimmobillier.fpbm.services.servicesImp;

import com.HQimmobillier.fpbm.dto.MessageDto;
import com.HQimmobillier.fpbm.entity.Messages;
import com.HQimmobillier.fpbm.entity.Post;
import com.HQimmobillier.fpbm.entity.User;
import com.HQimmobillier.fpbm.repository.MessageRepository;
import com.HQimmobillier.fpbm.repository.PostRepository;
import com.HQimmobillier.fpbm.repository.UserRepo;
import com.HQimmobillier.fpbm.services.AccountService;
import com.HQimmobillier.fpbm.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {
    private final MessageRepository messageRepository;
    private final PostRepository postRepository;
    private final UserRepo userRepo;
    private final AccountService accountService;
    public MessageServiceImp(MessageRepository messageRepository, PostRepository postRepository, UserRepo userRepo, AccountService accountService) {
        this.messageRepository = messageRepository;
        this.postRepository = postRepository;
        this.userRepo = userRepo;
        this.accountService = accountService;
    }

    @Override
    public List<Messages> getMessageThatAreSendToMe() {
        User user  = accountService.getAuthenticatedUser();
        return messageRepository.findAllByRecever(user);
    }

    public List<Messages> getAllMessagesThatISend(){
        User user = accountService.getAuthenticatedUser();
        return messageRepository.findAllBySender(user);
    }

    @Override
    public Messages createMessage(MessageDto msg) {
        Messages messages = new Messages();
        messages.setDate(new Date());
        Post post =postRepository.findById(msg.getPostId()).get();
        messages.setPost(post);
        User sender = accountService.getAuthenticatedUser();
        messages.setSender(sender);
        User recever = userRepo.findById(msg.getSendTo()) .get();
        messages.setRecever(recever);
        messages.setContent(msg.getMessageContent());

        return messageRepository.save(messages);
    }
}
