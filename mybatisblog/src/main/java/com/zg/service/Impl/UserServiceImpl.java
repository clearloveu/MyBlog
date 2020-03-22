package com.zg.service.Impl;

import com.zg.dao.UserDao;
import com.zg.pojo.User;
import com.zg.service.UserService;
import com.zg.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
