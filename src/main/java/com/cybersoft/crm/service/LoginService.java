package com.cybersoft.crm.service;

import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.repository.UserRepository;

import java.util.List;

public class LoginService {

    UserRepository usersRepository = new UserRepository();

    public boolean checkLogin(String email, String password){
        List<UserModel> list = usersRepository.getUsersByEmailAndPassword(email,password);
        return list.size() > 0;
    }

}
