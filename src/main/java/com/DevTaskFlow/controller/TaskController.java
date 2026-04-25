package com.DevTaskFlow.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.DevTaskFlow.model.Comment;
import com.DevTaskFlow.model.Priority;
import com.DevTaskFlow.model.Task;
import com.DevTaskFlow.model.TaskStatus;
import com.DevTaskFlow.model.User;
import com.DevTaskFlow.service.TaskService;
import com.DevTaskFlow.service.UserService;
import java.util.List;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

     private final TaskService taskService;
    private final UserService userService;
    
    @GetMapping
    public String listTasks(Model model, Authentication authentication) {
        String userEmail = authentication.getName();
        User currentUser = userService.findUserByEmail(userEmail);
        
        List<Task> tasks;
        if ("ADMIN".equals(currentUser.getRole())) {
            tasks = taskService.findAllTasks();
        } else {
            tasks = taskService.findTasksByAssignee(currentUser);
        }
        
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("priorities", Priority.values());
        return "tasks/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model, Authentication authentication) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("priorities", Priority.values());
        return "tasks/form";
    }
    
    @PostMapping("/new")
    public String createTask(@ModelAttribute Task task,
                             @RequestParam Long assigneeId,
                             Authentication authentication) {
        User currentUser = userService.findUserByEmail(authentication.getName());
        User assignee = userService.findUserById(assigneeId);
        task.setAssignee(assignee);
        taskService.createTask(task, currentUser);
        return "redirect:/tasks";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, Authentication authentication) {
        Task task = taskService.findTaskById(id);
        if (task == null) return "redirect:/tasks";
        
        model.addAttribute("task", task);
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("statuses", TaskStatus.values());
        return "tasks/form";
    }
    
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id,
                             @ModelAttribute Task taskDetails,
                             @RequestParam Long assigneeId) {
        User assignee = userService.findUserById(assigneeId);
        taskDetails.setAssignee(assignee);
        taskService.updateTask(id, taskDetails);
        return "redirect:/tasks";
    }
    
    @PostMapping("/status/{id}")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam TaskStatus status,
                               RedirectAttributes redirectAttributes) {
        Task task = taskService.updateTaskStatus(id, status);
        if (task != null) {
            redirectAttributes.addFlashAttribute("success", "Estado actualizado a: " + status.getDisplayName());
        }
        return "redirect:/tasks";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
    
    @GetMapping("/{id}")
    public String taskDetail(@PathVariable Long id, Model model) {
        Task task = taskService.findTaskById(id);
        if (task == null) return "redirect:/tasks";
        
        model.addAttribute("task", task);
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("newComment", new Comment());
        return "tasks/detail";
    }

}
