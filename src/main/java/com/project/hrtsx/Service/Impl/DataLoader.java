package com.project.hrtsx.Service.Impl;

import com.project.hrtsx.Model.User;
import com.project.hrtsx.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
    public class DataLoader {

        private UserRepo userRepository;

        @Autowired
        public DataLoader(UserRepo userRepository) {
            this.userRepository = userRepository;
            LoadUsers();
        }

        private void LoadUsers() {
            userRepository.save(new User(1L, "admin", "nimda", "admin", "admin", "ADMIN", true, null));
        }
    }