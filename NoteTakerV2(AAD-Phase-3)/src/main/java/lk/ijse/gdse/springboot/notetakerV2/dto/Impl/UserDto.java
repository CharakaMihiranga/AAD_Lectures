package lk.ijse.gdse.springboot.notetakerV2.dto.Impl;

import lk.ijse.gdse.springboot.notetakerV2.customObj.UserResponse;
import lk.ijse.gdse.springboot.notetakerV2.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements SuperDto, UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePic;
    private List<NoteDto> notes;
}
