package com.nexgen.layoutmap.auth;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mongodb.DuplicateKeyException;
import com.nexgen.layoutmap.dto.AuthRequest;
import com.nexgen.layoutmap.dto.AuthResponse;
import com.nexgen.layoutmap.dto.RegisterRequest;
import com.nexgen.layoutmap.repository.UserRepository;
import com.nexgen.layoutmap.utility.JwtUtil;

@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtTokenProvider jwtTokenProvider;
    
    private final JwtUtil jwtUtil;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    public AuthResponse register(AuthRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of("ROLE_USER"));

        userRepository.save(user);
        String token = jwtTokenProvider.createToken(user.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
    	 User user = userRepository.findByUsername(request.getUsername())
    		        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

    		    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
    		        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    		    }

    		    String token = jwtTokenProvider.createToken(user.getUsername());
    		    return new AuthResponse(token);

    }

    public AuthResponse register(RegisterRequest request) {
        try {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRoles(Set.of("ROLE_USER"));

            userRepository.save(user);
            String token = jwtTokenProvider.createToken(user.getUsername());
            return new AuthResponse(token);

        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
    }

	
}
