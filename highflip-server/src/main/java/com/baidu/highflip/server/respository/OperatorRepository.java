package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, String> {
}
