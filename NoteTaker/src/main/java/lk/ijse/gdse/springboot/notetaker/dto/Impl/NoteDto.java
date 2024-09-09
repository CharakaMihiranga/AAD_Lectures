package lk.ijse.gdse.springboot.notetaker.dto.Impl;

import lk.ijse.gdse.springboot.notetaker.customObj.UserResponse;
import lk.ijse.gdse.springboot.notetaker.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDto implements SuperDto, UserResponse {
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createdDate;
    private String userId;
}
