package com.project.hrtsx.Service.Impl;

import com.project.hrtsx.Model.User;
import com.project.hrtsx.Repository.UserRepo;
import com.project.hrtsx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepository;


    @Override
    public String saveUser(User user) {
        try {
            user.setUserRole("STANDART_USER");
            userRepository.save(user);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String updateUser(User user) {
        try {
            userRepository.save(user);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> allUsers() {
        try {
            return (List<User>) userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findUserById(Long id) {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

