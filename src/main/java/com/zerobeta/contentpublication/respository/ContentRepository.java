package com.zerobeta.contentpublication.respository;

import com.zerobeta.contentpublication.entity.Content;
import com.zerobeta.contentpublication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Integer> {

    List<Content> findByUser(User user);

    List<Content> findByIsPublishedTrue();
}
