package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.Messages;
import com.HQimmobillier.fpbm.entity.Post;
import com.HQimmobillier.fpbm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface MessageRepository extends JpaRepository<Messages,Long> {

    List<Messages> findAllByRecever(User user);
    List<Messages> findAllBySender(User user);

    List<Messages> findAllByPost(Post post);
}
