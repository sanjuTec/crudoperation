package com.crudoparation.crudoperation.repo;

import com.crudoparation.crudoperation.dto.UserDTO;
import com.crudoparation.crudoperation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user WHERE id=?1", nativeQuery = true)
    User getUserByuserId(int userId);
    @Query(value = "SELECT * FROM user WHERE id=?1 AND email=?2", nativeQuery = true)
    User getUserByuserIdNemail(int userId,String email);

}
