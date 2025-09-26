package com.example.CouDDa.service;

import com.example.CouDDa.domain.User;
import com.example.CouDDa.dto.LoginDto;
import com.example.CouDDa.dto.UserDto;
import com.example.CouDDa.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //passwordEncoder 주입(비밀번호 암호화)
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원 가입
    public User registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    // 로그인
    public User loginUser(LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("해당하는 이메일의 사용자가 없습니다.");
        }
        User user = userOptional.get();
        // 비밀번호 암호화해서 맞는지 확인
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}