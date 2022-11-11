package com.cybersoft.crm.service;

import com.cybersoft.crm.entity.UserEntity;
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

    public boolean insertUser(UserEntity user) {
        int result = userRepository.insertUser(user);
        return result > 0;
    }

    public boolean updateUser(UserEntity user) {
        int result = userRepository.updateUser(user);
        return result > 0;
    }

    public UserEntity findUserById(int id) {
        return userRepository.findUserById(id);
    }

}
