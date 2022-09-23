package com.baidu.highflip.server.respository;

import com.baidu.highflip.server.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, String> {

}
