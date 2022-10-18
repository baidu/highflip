package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {

    @Query("SELECT j.jobId FROM Job j")
    Iterator<String> findAllJobId();
}
