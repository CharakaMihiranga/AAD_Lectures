package lk.ijse.gdse.springboot.notetaker.controller;

import lk.ijse.gdse.springboot.notetaker.exception.NoteNotFoundException;
import lk.ijse.gdse.springboot.notetaker.service.NoteService;
import lk.ijse.gdse.springboot.notetaker.dto.Impl.NoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
@Controller
public class NoteController {

    @Autowired
    private final NoteService noteService;
    @GetMapping("health")
    public String healthCheck() {
        return "Note taker is running";
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody NoteDto note){ //http://localhost:8080/notetaker/api/v1/note
        //Todo: Handle with Bo
        var saveData = noteService.saveNote(note);
        return ResponseEntity.ok(saveData);
    }
    @GetMapping( value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<NoteDto> getAllNotes(){  //http://localhost:8080/notetaker/api/v1/note/allnotes
        return noteService.getAllNotes();
    }
    @GetMapping(value = "/{noteId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDto getNote(@PathVariable("noteId") String noteId){ //http://localhost:8080/notetaker/api/v1/note/1
        return noteService.getNote(noteId);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{noteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateNote(@PathVariable("noteId") String noteId,@RequestBody NoteDto note){ //http://localhost:8080/notetaker/api/v1/note/1
        try{
            noteService.updateNote(noteId, note);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable("noteId") String noteId){ //http://localhost:8080/notetaker/api/v1/note/1
        try{
            noteService.deleteNote(noteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
