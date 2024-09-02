package lk.ijse.gdse.springboot.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.notetaker.dao.NoteDao;
import lk.ijse.gdse.springboot.notetaker.dto.NoteDto;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import lk.ijse.gdse.springboot.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;
    @Autowired
    private Mapping mapping;

    @Override
    public String saveNote(NoteDto noteDto) {
        noteDto.setNoteId(AppUtil.createNoteId());
        var noteEntity = mapping.convertToEntity(noteDto);
        noteDao.save(noteEntity);
        return "Note Saved successfully in Bo Layer";
    }

    @Override
    public void updateNote(String noteId, NoteDto noteDto) {

    }

    @Override
    public void deleteNote(String noteId) {

    }

    @Override
    public NoteDto getNote(String note) {

        return null;
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return null;
    }
}
