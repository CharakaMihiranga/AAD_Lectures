package lk.ijse.gdse.springboot.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.notetaker.dao.NoteDao;
import lk.ijse.gdse.springboot.notetaker.dto.Impl.NoteDto;
import lk.ijse.gdse.springboot.notetaker.entity.NoteEntity;
import lk.ijse.gdse.springboot.notetaker.exception.NoteNotFoundException;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import lk.ijse.gdse.springboot.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<NoteEntity> tmpNoteEntityById = noteDao.findById(noteId);
        if (!tmpNoteEntityById.isPresent()){
            throw new NoteNotFoundException("Note not found");
        }else {
            tmpNoteEntityById.get().setNoteDesc(noteDto.getNoteDesc());
            tmpNoteEntityById.get().setCreatedDate(noteDto.getCreatedDate());
            tmpNoteEntityById.get().setNoteTitle(noteDto.getNoteTitle());
            tmpNoteEntityById.get().setPriorityLevel(noteDto.getPriorityLevel());
        }
    }

    @Override
    public void deleteNote(String noteId) {
        Optional<NoteEntity> tmpNoteEntityById = noteDao.findById(noteId);
        if (!tmpNoteEntityById.isPresent()){
            throw new NoteNotFoundException("Note not found");
        }else {
            noteDao.deleteById(noteId);
        }
    }

    @Override
    public NoteDto getNote(String noteId) {
        return mapping.convertToDto(noteDao.getReferenceById(noteId));
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return mapping.convertToDtos(noteDao.findAll());
    }
}
