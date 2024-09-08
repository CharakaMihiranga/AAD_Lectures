package lk.ijse.gdse.springboot.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.notetaker.dao.UserDao;
import lk.ijse.gdse.springboot.notetaker.dto.UserDto;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import lk.ijse.gdse.springboot.notetaker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserDao userDao;
    @Autowired
    private final Mapping mapping;

    @Override
    public String saveUser(UserDto userDto) {
        userDto.setUserId(AppUtil.createUserId());
        userDao.save(mapping.convertToUserEntity(userDto));
        return "User saved successfully";
    }

    @Override
    public boolean updateUser(String userId, UserDto userDto) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    @Override
    public UserDto getSelectedUser(String userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }
}
