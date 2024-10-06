package lk.ijse.gdse.springboot.notetakerV2.controller;

import lk.ijse.gdse.springboot.notetaker.customObj.NoteResponse;
import lk.ijse.gdse.springboot.notetaker.dto.Impl.NoteDto;
import lk.ijse.gdse.springboot.notetaker.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.notetaker.exception.NoteNotFoundException;
import lk.ijse.gdse.springboot.notetaker.service.NoteService;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNote(@RequestBody NoteDto note){ //http://localhost:8080/notetaker/api/v1/note
        //Todo: Handle with Bo
        if (note == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try{
                noteService.saveNote(note);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                //internal server error putting to the last catch block bcz
                // it is the most generic exception
            }
        }
    }
    @GetMapping( value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<NoteDto> getAllNotes(){  //http://localhost:8080/notetaker/api/v1/note/allnotes
        return noteService.getAllNotes();
    }
    @GetMapping(value = "/{noteId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteResponse getNote(@PathVariable("noteId") String noteId){ //http://localhost:8080/notetaker/api/v1/note/1
        return noteService.getNote(noteId);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{noteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateNote(@PathVariable("noteId") String noteId,@RequestBody NoteDto note){ //http://localhost:8080/notetaker/api/v1/note/1
        try{
            if (note == null && (noteId == null || noteId.isEmpty())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
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
            if (noteId == null || noteId.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            noteService.deleteNote(noteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

// Http verbs => GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD
// Http noun => /api/v1/note