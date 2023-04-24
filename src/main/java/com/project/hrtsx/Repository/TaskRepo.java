package com.project.hrtsx.Repository;

import com.project.hrtsx.Model.Task;
import com.project.hrtsx.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("SELECT u FROM Task u WHERE u.user = ?1")
    List<Task> findTaskByUser(User email);

}
