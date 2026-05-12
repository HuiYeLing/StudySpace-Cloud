package com.studyspace.auth.controller;

import com.studyspace.auth.service.AuthService;
import com.studyspace.common.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResult login(@RequestParam String username,
                           @RequestParam String password) {
        return authService.login(username, password);
    }

    @PostMapping("/register")
    public ApiResult register(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email) {
        return authService.register(username, password, email);
    }
}