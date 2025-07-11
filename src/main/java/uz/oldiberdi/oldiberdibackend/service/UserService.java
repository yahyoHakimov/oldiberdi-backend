package uz.oldiberdi.oldiberdibackend.service;

import uz.oldiberdi.oldiberdibackend.dto.LoginRequest;
import uz.oldiberdi.oldiberdibackend.dto.UserDto;
import uz.oldiberdi.oldiberdibackend.entity.User;

public interface UserService {
    User register(UserDto userDto);
    User login(LoginRequest request);
}
