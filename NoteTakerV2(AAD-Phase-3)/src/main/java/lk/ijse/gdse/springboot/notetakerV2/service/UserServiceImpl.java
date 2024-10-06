package lk.ijse.gdse.springboot.notetakerV2.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.notetaker.customObj.UserErrorResponse;
import lk.ijse.gdse.springboot.notetaker.customObj.UserResponse;
import lk.ijse.gdse.springboot.notetaker.dao.UserDao;
import lk.ijse.gdse.springboot.notetaker.dto.Impl.UserDto;
import lk.ijse.gdse.springboot.notetaker.entity.UserEntity;
import lk.ijse.gdse.springboot.notetaker.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.notetaker.exception.UserNotFoundException;
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
    public void saveUser(UserDto userDto) {
        userDto.setUserId(AppUtil.createUserId());
        UserEntity savedUser =
                userDao.save(mapping.convertToUserEntity(userDto));
        if (savedUser == null && savedUser.getUserId() == null) {
            throw new DataPersistFailedException("Can't save the user");
        }
    }

    @Override
    public void updateUser(UserDto userDto) {
        Optional<UserEntity> tempUser = userDao.findById(userDto.getUserId());
        if(!tempUser.isPresent()){
            throw new UserNotFoundException("User not found");
        }else{
            tempUser.get().setFirstName(userDto.getFirstName());
            tempUser.get().setLastName(userDto.getLastName());
            tempUser.get().setEmail(userDto.getEmail());
            tempUser.get().setPassword(userDto.getPassword());
            tempUser.get().setProfilePic(userDto.getProfilePic());
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> selectedUserId = userDao.findById(userId);
        if(!selectedUserId.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public UserResponse getSelectedUser(String userId) {
        if(userDao.existsById(userId)){
            return mapping.convertToUserDTO(userDao.getUserEntityByUserId(userId));
        }else{
         return new UserErrorResponse(0,"User not found"); //This is a custom response
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return mapping.convertUsersToList(userDao.findAll());
    }
}
