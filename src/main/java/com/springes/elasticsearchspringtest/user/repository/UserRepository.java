package com.springes.elasticsearchspringtest.user.repository;

import com.springes.elasticsearchspringtest.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
