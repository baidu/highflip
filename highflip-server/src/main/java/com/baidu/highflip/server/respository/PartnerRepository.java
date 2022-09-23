package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, String> {
}
