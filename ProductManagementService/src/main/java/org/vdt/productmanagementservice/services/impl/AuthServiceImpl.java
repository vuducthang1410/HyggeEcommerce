package org.vdt.productmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vdt.productmanagementservice.dtos.request.AccountRegisterRqDto;
import org.vdt.productmanagementservice.entities.User;
import org.vdt.productmanagementservice.repositories.AuthRepository;
import org.vdt.productmanagementservice.services.IAuthService;
import org.vdt.productmanagementservice.services.IJwtService;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Map<String, Object> login(AccountRegisterRqDto accountRegisterRqDto) {
        Map<String, Object> response = new HashMap<>();
        User user = authRepository.findByUsername(accountRegisterRqDto.getUsername());
        if (passwordEncoder.matches(accountRegisterRqDto.getPassword(), user.getPassword())) {
            response.put("username", user.getUsername());
            response.put("userId", user.getId());
            String accessToken = jwtService.generateRefreshToken(user.getId());
            response.put("accessToken", accessToken);
            redisTemplate.opsForValue().set(user.getId(), accessToken, Duration.ofMinutes(30));
        }
        return response;
    }

    @Override
    public User register(AccountRegisterRqDto accountRegisterRqDto) {
        User user = new User();
        user.setUsername(accountRegisterRqDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountRegisterRqDto.getPassword()));
        return authRepository.save(user);
    }

    @Override
    public Map<String, Object> updateInfo(String userId) {
        return Map.of();
    }

    @Override
    public String logout(String userId) {
        return "";
    }
}
