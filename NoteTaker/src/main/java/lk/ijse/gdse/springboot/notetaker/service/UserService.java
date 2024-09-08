package lk.ijse.gdse.springboot.notetaker.service;

import lk.ijse.gdse.springboot.notetaker.customObj.UserResponse;
import lk.ijse.gdse.springboot.notetaker.dto.Impl.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDto> getAllUsers();
}
