package lk.ijse.gdse.springboot.notetakerV2.service;

import lk.ijse.gdse.springboot.notetakerV2.customObj.UserResponse;
import lk.ijse.gdse.springboot.notetakerV2.dto.Impl.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDto> getAllUsers();
}
