package lk.ijse.gdse.springboot.notetaker.service;

import lk.ijse.gdse.springboot.notetaker.customObj.NoteResponse;
import lk.ijse.gdse.springboot.notetaker.dto.Impl.NoteDto;

import java.util.List;

public interface NoteService {
    void saveNote(NoteDto noteDto);
    void updateNote(String noteId, NoteDto noteDto);
    void deleteNote(String noteId);
    NoteResponse getNote(String note);
    List<NoteDto> getAllNotes();
}
