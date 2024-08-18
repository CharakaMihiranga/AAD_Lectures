package lk.ijse.gdse.springboot.notetaker.bo;

import lk.ijse.gdse.springboot.notetaker.dto.NoteDto;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class NoteBoImpl implements NoteBo {
    @Override
    public String saveNote(NoteDto noteDto) {
        noteDto.setNoteId(AppUtil.createNoteId());
        System.out.println(noteDto);
        return "Note Saved successfully in Bo Layer";
    }

    @Override
    public boolean updateNote(String noteId, NoteDto noteDto) {
        return false;
    }

    @Override
    public boolean deleteNote(String noteId) {
        return false;
    }

    @Override
    public NoteDto getNote(String note) {
        return null;
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return List.of();
    }
}
