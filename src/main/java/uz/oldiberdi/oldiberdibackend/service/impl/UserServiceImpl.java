package uz.oldiberdi.oldiberdibackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.oldiberdi.oldiberdibackend.dto.LoginRequest;
import uz.oldiberdi.oldiberdibackend.dto.UserDto;
import uz.oldiberdi.oldiberdibackend.entity.User;
import uz.oldiberdi.oldiberdibackend.repository.UserRepository;
import uz.oldiberdi.oldiberdibackend.service.UserService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(UserDto userDto) {
        if (userRepository.findByPhoneNumber(userDto.getPhoneNumber()).isPresent()) {
            throw new IllegalArgumentException("Phone number already registered.");
        }

        User user = User.builder()
                .name(userDto.getName())
                .phoneNumber(userDto.getPhoneNumber())
                .passwordHash(passwordEncoder.encode(userDto.getPassword()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new NoSuchElementException("User not found."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials.");
        }

        return user;
    }
}
