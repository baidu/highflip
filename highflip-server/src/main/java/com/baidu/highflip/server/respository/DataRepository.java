package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, String> {
}
