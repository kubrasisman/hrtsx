package com.project.hrtsx.Service;

import com.project.hrtsx.Model.Task;
import com.project.hrtsx.Model.User;

import java.util.List;


public interface TaskService {
    String updateTask(Task task);

    List<Task> findTaskByUsers(User user);

    String saveTask(Task task);

    Task findById(Long id);

    List<Task> findAll();

}
