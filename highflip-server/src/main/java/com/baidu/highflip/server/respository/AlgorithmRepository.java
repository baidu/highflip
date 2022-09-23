package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Algorithm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgorithmRepository extends JpaRepository<Algorithm, String> {
}
