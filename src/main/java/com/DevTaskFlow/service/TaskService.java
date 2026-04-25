package com.DevTaskFlow.service;




import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.DevTaskFlow.model.Task;
import com.DevTaskFlow.model.TaskStatus;
import com.DevTaskFlow.model.User;
import com.DevTaskFlow.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

     private final TaskRepository taskRepository;
    
    public Task createTask(Task task, User createdBy) {
        task.setCreatedBy(createdBy);
        task.setStatus(TaskStatus.TODO);
        return taskRepository.save(task);
    }
    
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setPriority(taskDetails.getPriority());
            task.setDueDate(taskDetails.getDueDate());
            task.setAssignee(taskDetails.getAssignee());
            return taskRepository.save(task);
        }
        return null;
    }
    
    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setStatus(status);
            return taskRepository.save(task);
        }
        return null;
    }
    
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
    
    public List<Task> findTasksByAssignee(User user) {
        return taskRepository.findByAssignee(user);
    }
    
    public List<Task> findTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
    
    public List<Task> findTasksByAssigneeAndStatus(User user, TaskStatus status) {
        return taskRepository.findByAssigneeAndStatus(user, status);
    }

}
