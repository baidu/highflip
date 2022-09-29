package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, String> {

    Iterable<Platform> findAllByCompanyAndProduct(String company, String product);
}
