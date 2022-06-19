package com.HQimmobillier.fpbm.repository;

import com.HQimmobillier.fpbm.entity.Post;
import com.HQimmobillier.fpbm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findAllPostsByUser(User user);
}
