package com.abinesh.spring_task.controller;

import com.abinesh.spring_task.model.Task;
import com.abinesh.spring_task.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final ITaskService taskService;
    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        return new ResponseEntity<>(taskService.getTasks(), HttpStatus.FOUND);
    }
    @PostMapping
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }
    @PutMapping("/update/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable Long id){
        return taskService.updateTask(task, id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @GetMapping("/task/findbystatus/{status}")
    public ResponseEntity<List<Task>> getTaskByStatus(@PathVariable String status){
        List<Task> temptask=new ArrayList<>();

        temptask=taskService.getTasksByStatus(status);

        if(temptask.size()>0){
            return new ResponseEntity<>(temptask,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(temptask,HttpStatus.NOT_FOUND);

    }

}
