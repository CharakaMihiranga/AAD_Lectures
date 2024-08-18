package lk.ijse.gdse.springboot.notetaker.controller;

import lk.ijse.gdse.springboot.notetaker.bo.NoteBo;
import lk.ijse.gdse.springboot.notetaker.bo.NoteBoImpl;
import lk.ijse.gdse.springboot.notetaker.dto.NoteDto;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {
    @Autowired
    private final NoteBo noteBo;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody NoteDto note){ //http://localhost:8080/notetaker/api/v1/note
        //Todo: Handle with Bo
        var saveData = noteBo.saveNote(note);
        return ResponseEntity.ok(saveData);
    }   
    @GetMapping( value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<NoteDto> getAllNotes(){  //http://localhost:8080/notetaker/api/v1/note/allnotes
        List<NoteDto> notes = List.of(
                new NoteDto(
                        "N001",
                        "Spring Boot",
                        "Spring Boot is a powerful framework",
                        "High",
                        "2021-06-10"
                ),
                new NoteDto(
                        "N002",
                        "Spring Security",
                        "Spring Security is a powerful framework",
                        "High",
                        "2021-06-10"
                )
        );
        return notes;
    }
    @GetMapping(value = "/{noteId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDto getNote(@PathVariable("noteId") String noteId){ //http://localhost:8080/notetaker/api/v1/note/1
        System.out.println(noteId);
        return new NoteDto(
                "N001",
                "Spring Boot",
                "Spring Boot is a powerful framework",
                "High",
                "2021-06-10"
        );
    }
    @PatchMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateNote(@PathVariable("noteId") String noteId,@RequestBody NoteDto note){ //http://localhost:8080/notetaker/api/v1/note/1
        System.out.println(noteId);
        System.out.println(note+" Updated");
    }
    @DeleteMapping(value = "/{noteId}")
    public void deleteNote(@PathVariable("noteId") String noteId){ //http://localhost:8080/notetaker/api/v1/note/1
        System.out.println(noteId+" Deleted");
    }
}
