package lk.ijse.gdse.springboot.notetaker.service;

import lk.ijse.gdse.springboot.notetaker.dao.UserDao;
import lk.ijse.gdse.springboot.notetaker.dto.UserDto;

import java.util.List;

public interface UserService {
    String saveUser(UserDto userDto);
    boolean updateUser(UserDto userDto);
    boolean deleteUser(String userId);
    UserDto getSelectedUser(String userId);
    List<UserDto> getAllUsers();
}
