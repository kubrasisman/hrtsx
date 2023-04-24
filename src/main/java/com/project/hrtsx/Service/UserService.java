package com.project.hrtsx.Service;


import com.project.hrtsx.Model.User;

import java.util.List;

public interface UserService {
    String saveUser(User user);

    String updateUser(User user);

    List<User> allUsers();

    User findUserByEmail(String email);

    User findUserById(Long id);

}
