package com.DevTaskFlow.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DevTaskFlow.model.Task;
import com.DevTaskFlow.model.User;
import com.DevTaskFlow.service.CommentService;
import com.DevTaskFlow.service.TaskService;
import com.DevTaskFlow.service.UserService;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
private final CommentService commentService;
    private final TaskService taskService;
    private final UserService userService;
    
    @PostMapping("/add/{taskId}")
    public String addComment(@PathVariable Long taskId,
                             @RequestParam String content,
                             Authentication authentication) {
        Task task = taskService.findTaskById(taskId);
        User author = userService.findUserByEmail(authentication.getName());
        
        if (task != null && author != null && content != null && !content.trim().isEmpty()) {
            commentService.addComment(content, task, author);
        }
        
        return "redirect:/tasks/" + taskId;
    }
}
