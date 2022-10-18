package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, String> {

    @Query("SELECT p FROM Platform p WHERE p.isLocal = TRUE")
    Platform findLocal();

    @Modifying
    @Query("DELETE FROM Platform p WHERE p.isLocal = TRUE")
    void deleteLocal();

    Iterable<Platform> findAllByCompanyAndProduct(String company, String product);
}
