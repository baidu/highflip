package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    List<Task> findAllByJobid(String jobid);
}
