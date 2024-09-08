package lk.ijse.gdse.springboot.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.notetaker.dao.UserDao;
import lk.ijse.gdse.springboot.notetaker.dto.UserDto;
import lk.ijse.gdse.springboot.notetaker.entity.NoteEntity;
import lk.ijse.gdse.springboot.notetaker.entity.UserEntity;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import lk.ijse.gdse.springboot.notetaker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public boolean updateUser(UserDto userDto) {
        Optional<UserEntity> tempUser = userDao.findById(userDto.getUserId());
        if(!tempUser.isPresent()){
            return false;
        }else{
            tempUser.get().setFirstName(userDto.getFirstName());
            tempUser.get().setLastName(userDto.getLastName());
            tempUser.get().setEmail(userDto.getEmail());
            tempUser.get().setPassword(userDto.getPassword());
            tempUser.get().setProfilePic(userDto.getProfilePic());
            return true;
        }
    }

    @Override
    public boolean deleteUser(String userId) {
        if(userDao.existsById(userId)){
            userDao.deleteById(userId);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public UserDto getSelectedUser(String userId) {
        return mapping.convertToUserDTO(userDao.getUserEntityByUserId(userId));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return mapping.convertUsersToList(userDao.findAll());
    }
}
