package lk.ijse.gdse.springboot.notetakerV2.dao;

import lk.ijse.gdse.springboot.notetaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
    @Query()
   UserEntity getUserEntityByUserId(String userId);
}
