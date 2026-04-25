package com.DevTaskFlow.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.DevTaskFlow.dto.UserRegistrationDto;
import com.DevTaskFlow.service.UserService;

import jakarta.validation.Valid;


import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

     private final UserService userService;
    
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "users/form";
    }
    
    @PostMapping("/new")
    public String createUser(@Valid @ModelAttribute("user") UserRegistrationDto userDto,
                             BindingResult result) {
        if (userService.emailExists(userDto.getEmail())) {
            result.rejectValue("email", "error.user", "El email ya está registrado");
        }
        
        if (result.hasErrors()) {
            return "users/form";
        }
        
        userService.registerUser(userDto);
        return "redirect:/users";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        // Redirigido a DashboardController, este método es redundante
        return "redirect:/dashboard";
    }

}
