package org.skilltrade.service;

import org.skilltrade.dto.LoginDTO;
import org.skilltrade.dto.RegisterDTO;
import org.skilltrade.entity.User;
import org.skilltrade.repository.UserRepository;
import org.skilltrade.security.JwtUtil;
import org.skilltrade.service.EmailDomainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailDomainService emailDomainService;

    public String register(RegisterDTO dto) {
        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEduEmail(emailDomainService.isEdu(dto.getEmail()));
        user.setKarmaPoints(0);
        user.setTrustScore(0.0);
        userRepo.save(user);

        return jwtUtil.generateToken(user.getEmail());
    }

    public String login(LoginDTO dto) {
        User user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
