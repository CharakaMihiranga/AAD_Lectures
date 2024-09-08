package lk.ijse.gdse.springboot.notetaker.dao;

import lk.ijse.gdse.springboot.notetaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
}
