package com.baidu.highflip.server.respository;

import com.baidu.highflip.core.entity.runtime.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
