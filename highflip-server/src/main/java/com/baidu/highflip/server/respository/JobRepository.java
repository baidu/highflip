package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {

}
