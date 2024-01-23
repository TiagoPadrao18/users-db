package com.usersDb.usersDb.repository;

import com.usersDb.usersDb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName (String name);
    boolean existsByEmail (String email);

}
