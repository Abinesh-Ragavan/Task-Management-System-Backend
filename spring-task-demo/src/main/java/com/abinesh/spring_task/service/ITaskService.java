package com.abinesh.spring_task.service;

import com.abinesh.spring_task.model.Task;

import java.util.List;

public interface ITaskService {
    Task addTask(Task task);
    List<Task> getTasks();
    Task updateTask(Task task, Long id);
    Task getTaskById(Long id);
    void deleteTask(Long id);
    List<Task>getTasksByStatus(String status);
}
