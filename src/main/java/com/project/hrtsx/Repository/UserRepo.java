package com.project.hrtsx.Repository;

import com.project.hrtsx.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo  extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

}
