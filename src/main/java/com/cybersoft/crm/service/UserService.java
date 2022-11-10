package com.cybersoft.crm.service;

import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.repository.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public List<UserModel> getAllUsers() {
        return userRepository.getUsers();
    }

    public boolean deleteUserById(int id) {
        int result = userRepository.deleteUserById(id);
        return result > 0;
    }
}
