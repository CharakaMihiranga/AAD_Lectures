package lk.ijse.gdse.springboot.notetakerV2.util;

import lk.ijse.gdse.springboot.notetaker.dto.Impl.NoteDto;
import lk.ijse.gdse.springboot.notetaker.dto.Impl.UserDto;
import lk.ijse.gdse.springboot.notetaker.entity.NoteEntity;
import lk.ijse.gdse.springboot.notetaker.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    //Matters of noteEntity and NoteDto
    public NoteDto convertToDto(NoteEntity noteEntity){
        return modelMapper.map(noteEntity, NoteDto.class);
    }
    public NoteEntity convertToEntity(NoteDto noteDto){
        return modelMapper.map(noteDto, NoteEntity.class);
    }
    public List<NoteDto> convertToDtos(List<NoteEntity> notes){
        return modelMapper.map(notes,new TypeToken<List<NoteDto>>(){}.getType() );
    }
    //User matters mapping
    public UserEntity convertToUserEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDto convertToUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }
    public List<UserDto> convertUsersToList(List<UserEntity> userEntities) {
        return modelMapper.map(userEntities, new TypeToken<List<UserDto>>(){}.getType());
    }
}
