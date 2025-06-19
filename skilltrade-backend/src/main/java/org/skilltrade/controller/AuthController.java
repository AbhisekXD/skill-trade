package org.skilltrade.controller;

import org.skilltrade.dto.LoginDTO;
import org.skilltrade.dto.RegisterDTO;
import org.skilltrade.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return authService.login(dto);
    }
}
