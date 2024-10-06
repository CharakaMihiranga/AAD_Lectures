package lk.ijse.gdse.springboot.notetakerV2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "note")
public class NoteEntity implements SuperEntity {
    @Id
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createdDate;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity user;
}
