package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Config, String> {

}
