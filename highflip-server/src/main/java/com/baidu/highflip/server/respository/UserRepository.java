package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
