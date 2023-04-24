package com.project.hrtsx.Service.Impl;

import com.project.hrtsx.Model.Task;
import com.project.hrtsx.Model.TaskStatusEnum;
import com.project.hrtsx.Model.User;
import com.project.hrtsx.Repository.TaskRepo;
import com.project.hrtsx.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepo taskRepository;

    @Override
    public String updateTask(Task task) {
        try {
            taskRepository.save(task);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Task> findTaskByUsers(User user) {
        try {
            return taskRepository.findTaskByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String saveTask(Task task) {
        try {
            task.setTaskStatus(TaskStatusEnum.START);
            taskRepository.save(task);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Task findById(Long id) {
        try {
            return taskRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Task> findAll() {
        try {
            return taskRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
