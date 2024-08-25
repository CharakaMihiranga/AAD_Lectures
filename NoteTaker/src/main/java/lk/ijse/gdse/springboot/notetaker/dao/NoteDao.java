package lk.ijse.gdse.springboot.notetaker.dao;

import lk.ijse.gdse.springboot.notetaker.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends JpaRepository<NoteEntity, String> {

}
