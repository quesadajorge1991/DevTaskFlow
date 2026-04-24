package com.DevTaskFlow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.DevTaskFlow.model.Task;
import com.DevTaskFlow.model.TaskStatus;
import com.DevTaskFlow.model.User;
import com.DevTaskFlow.service.TaskService;
import com.DevTaskFlow.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String userEmail = authentication.getName();
        User currentUser = userService.findUserByEmail(userEmail);

        List<Task> tasks;
        if ("ADMIN".equals(currentUser.getRole())) {
            tasks = taskService.findAllTasks();
        } else {
            tasks = taskService.findTasksByAssignee(currentUser);
        }

        boolean noTask = tasks.stream().filter(t -> t.getAssignee().getEmail() == userEmail).count() == 0;

        long todoCount = tasks.stream().filter(t -> t.getStatus() == TaskStatus.TODO).count();
        long inProgressCount = tasks.stream().filter(t -> t.getStatus() == TaskStatus.IN_PROGRESS).count();
        long doneCount = tasks.stream().filter(t -> t.getStatus() == TaskStatus.DONE).count();

        model.addAttribute("todoCount", todoCount);
        model.addAttribute("inProgressCount", inProgressCount);
        model.addAttribute("doneCount", doneCount);
        model.addAttribute("user", currentUser);
        model.addAttribute("totalTasks", tasks.size());
        model.addAttribute("tienetarea", noTask);

        return "dashboard";
    }

}
