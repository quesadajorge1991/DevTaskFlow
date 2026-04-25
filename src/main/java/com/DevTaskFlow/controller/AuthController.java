package com.DevTaskFlow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.DevTaskFlow.dto.UserRegistrationDto;
import com.DevTaskFlow.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDto userDto,
                               BindingResult result, Model model) {
        if (userService.emailExists(userDto.getEmail())) {
            result.rejectValue("email", "error.user", "El email ya está registrado");
        }
        
        if (result.hasErrors()) {
            return "register";
        }
        
        userService.registerUser(userDto);
        return "redirect:/login?registered";
    }

}
