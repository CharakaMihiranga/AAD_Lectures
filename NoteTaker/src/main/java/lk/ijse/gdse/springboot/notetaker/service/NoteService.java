package lk.ijse.gdse.springboot.notetaker.service;

import lk.ijse.gdse.springboot.notetaker.dto.NoteDto;

import java.util.List;

public sealed interface NoteService permits NoteServiceImpl {
    String saveNote(NoteDto noteDto);
    boolean updateNote(String noteId, NoteDto noteDto);
    boolean deleteNote(String noteId);
    NoteDto getNote(String note);
    List<NoteDto> getAllNotes();
}
