package com.zerobeta.contentpublication.respository;

import com.zerobeta.contentpublication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginName(String loginName);

    Boolean existsByLoginName(String loginName);
}
