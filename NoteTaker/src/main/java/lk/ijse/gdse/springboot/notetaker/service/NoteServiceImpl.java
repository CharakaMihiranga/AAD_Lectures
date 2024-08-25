package lk.ijse.gdse.springboot.notetaker.service;

import lk.ijse.gdse.springboot.notetaker.dto.NoteDto;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class NoteServiceImpl implements NoteService {
    List<NoteDto> saveNoteTmp = new ArrayList<>();
    public NoteServiceImpl(){
        saveNoteTmp.add(new NoteDto(
                "N001",
                "Spring Boot",
                "Spring Boot is a powerful framework",
                "High",
                "2021-06-10"
        ));
        saveNoteTmp.add(new NoteDto(
                "N002",
                "Spring Boot",
                "Spring Boot is a powerful framework",
                "High",
                "2021-06-10"
        ));
        saveNoteTmp.add(new NoteDto(
                "N003",
                "Spring Boot",
                "Spring Boot is a powerful framework",
                "High",
                "2021-06-10"
        ));
        saveNoteTmp.add(new NoteDto(
                "N004",
                "Spring Boot",
                "Spring Boot is a powerful framework",
                "High",
                "2021-06-10"
        ));
        System.out.println("saveNoteTmp: "+saveNoteTmp);
    }
    @Override
    public String saveNote(NoteDto noteDto) {
        noteDto.setNoteId(AppUtil.createNoteId());
        saveNoteTmp.add(noteDto);
        return "Note Saved successfully in Bo Layer";
    }

    @Override
    public boolean updateNote(String noteId, NoteDto noteDto) {
        for (NoteDto note : saveNoteTmp) {
            if (noteId.equals(note.getNoteId())) {
                note.setNoteTitle(noteDto.getNoteTitle());
                note.setNoteDesc(noteDto.getNoteDesc());
                note.setPriorityLevel(noteDto.getPriorityLevel());
                note.setCreatedDate(noteDto.getCreatedDate());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteNote(String noteId) {
        for (NoteDto noteDto : saveNoteTmp){
            if (noteDto.getNoteId().equals(noteId)){
                saveNoteTmp.remove(noteDto);
                return true;
            }
        }
        return false;
    }

    @Override
    public NoteDto getNote(String note) {
        for (NoteDto noteDto : saveNoteTmp) {
            if (noteDto.getNoteId().equals(note)) {
                return noteDto;
            }
        }
        return null;
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return saveNoteTmp;
    }
}
