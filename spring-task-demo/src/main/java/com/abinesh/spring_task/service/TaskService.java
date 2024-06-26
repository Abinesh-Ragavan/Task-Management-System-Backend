package com.abinesh.spring_task.service;

import com.abinesh.spring_task.Exception.TaskAlreadyExistsException;
import com.abinesh.spring_task.Exception.TaskNotFoundException;
import com.abinesh.spring_task.model.Task;
import com.abinesh.spring_task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService{
    private final TaskRepository taskRepository;



    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task addTask(Task task) {
        if (TaskAlreadyExists(task.getEmail())){
            throw  new TaskAlreadyExistsException(task.getEmail()+ " already exists!");
        }
        return taskRepository.save(task);
    }


    @Override
    public Task updateTask(Task task, Long id) {
        return taskRepository.findById(id).map(st -> {
            st.setFirstName(task.getFirstName());
            st.setLastName(task.getLastName());
            st.setEmail(task.getEmail());
            st.setDepartment(task.getDepartment());
            st.setDescription(task.getDescription());
            st.setDuedate(task.getDuedate());
            st.setStatus(task.getStatus());
            return taskRepository.save(st);
        }).orElseThrow(() -> new TaskNotFoundException("Sorry, this Task could not be found"));
    }



    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Sorry, no task found with the Id :" +id));
    }

    @Override
    public void deleteTask(Long id) {

        if (!taskRepository.existsById(id)){
            throw new TaskNotFoundException("Sorry, task not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        List<Task> tasks = new ArrayList<>();
        List<Task> taskList = getTasks();



        for (Task task : taskList) {
            if (task.getStatus().toLowerCase().equals(status.toLowerCase())){
                tasks.add(task);

            }
        }
        return tasks;
    }

    private boolean TaskAlreadyExists(String email) {
        return taskRepository.findByEmail(email).isPresent();
    }

}