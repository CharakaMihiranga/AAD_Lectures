package lk.ijse.gdse.springboot.notetakerV2.dao;

import lk.ijse.gdse.springboot.notetakerV2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
