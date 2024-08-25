package lk.ijse.gdse.springboot.notetaker.service;

import lk.ijse.gdse.springboot.notetaker.dto.NoteDto;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
    public void updateNote(String noteId, NoteDto noteDto) {
       ListIterator<NoteDto> updatedList = saveNoteTmp.listIterator();
         while (updatedList.hasNext()){
              NoteDto note = updatedList.next();
              if (note.getNoteId().equals(noteId)){
                noteDto.setNoteId(noteId);
                updatedList.set(noteDto);
                break;
              }
         }
    }

    @Override
    public void deleteNote(String noteId) {
        ListIterator<NoteDto> deleteList = saveNoteTmp.listIterator();
        while (deleteList.hasNext()){
            NoteDto note = deleteList.next();
            if (note.getNoteId().equals(noteId)){
                deleteList.remove();
                break;
            }
        }
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
