package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE LOWER(u.username) LIKE LOWER(concat('%', :username, '%'))")
    List<Users> findByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.username=?1")
    Users findByUser(String username);

}
