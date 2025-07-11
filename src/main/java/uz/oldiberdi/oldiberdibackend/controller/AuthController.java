package uz.oldiberdi.oldiberdibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.oldiberdi.oldiberdibackend.dto.LoginRequest;
import uz.oldiberdi.oldiberdibackend.dto.UserDto;
import uz.oldiberdi.oldiberdibackend.entity.User;
import uz.oldiberdi.oldiberdibackend.security.JWTUtils;
import uz.oldiberdi.oldiberdibackend.service.UserService;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JWTUtils jWTUtils;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.login(request);
        String token = jWTUtils.generateToken(user);

        return ResponseEntity.ok(Map.of(
                "user", user,
                "token", token
        ));
    }
}
