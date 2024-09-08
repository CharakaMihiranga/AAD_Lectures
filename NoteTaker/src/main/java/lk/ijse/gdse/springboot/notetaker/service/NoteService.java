package lk.ijse.gdse.springboot.notetaker.service;

import lk.ijse.gdse.springboot.notetaker.dto.Impl.NoteDto;

import java.util.List;

public interface NoteService {
    String saveNote(NoteDto noteDto);
    void updateNote(String noteId, NoteDto noteDto);
    void deleteNote(String noteId);
    NoteDto getNote(String note);
    List<NoteDto> getAllNotes();
}
