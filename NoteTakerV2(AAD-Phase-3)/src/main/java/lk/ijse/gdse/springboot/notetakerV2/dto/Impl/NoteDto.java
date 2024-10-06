package lk.ijse.gdse.springboot.notetakerV2.dto.Impl;

import lk.ijse.gdse.springboot.notetakerV2.customObj.NoteResponse;
import lk.ijse.gdse.springboot.notetakerV2.customObj.UserResponse;
import lk.ijse.gdse.springboot.notetakerV2.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDto implements SuperDto, NoteResponse, UserResponse {
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createdDate;
    private String userId;
}
