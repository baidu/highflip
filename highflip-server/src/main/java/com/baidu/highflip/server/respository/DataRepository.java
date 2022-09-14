package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, String> {
}
