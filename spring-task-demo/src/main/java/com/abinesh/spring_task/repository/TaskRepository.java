package com.abinesh.spring_task.repository;

import com.abinesh.spring_task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository  extends JpaRepository<Task, Long> {
    Optional<Task> findByEmail(String email);
}
