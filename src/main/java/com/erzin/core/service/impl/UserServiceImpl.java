package com.erzin.core.service.impl;

import com.erzin.core.dao.UserDao;
import com.erzin.core.model.User;
import com.erzin.core.service.UserService;
import com.erzin.core.exception.NotPersistedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    UserDao userDao;

    public void saveUser(User instance) {
        // Couldn't find a way to auto generete id for MongoDb with Spring Data .
        // So current users count + 1 will be assigned for id for the new instance before persistence.
        Long userCount = userDao.getCount(); // get the count
        userCount++; // increment by 1
        instance.setId(userCount); //  set new instance id with calculated value
        userDao.save(instance);
        logger.debug("new user added to the database with id " + instance.getId());
    }

    public Long getUserCount() {
        return userDao.getCount();
    }

    public void deleteUser(Long userId) throws NotPersistedException {
        User instance = userDao.find(userId);
        if (instance != null) {
            userDao.delete(instance);
        } else {
            logger.error("Delete error : Unpersisted user with user id " + userId);
            throw new NotPersistedException("User not persisted. Update user failed.");
        }

    }

    public void updateUser(User instance) throws NotPersistedException {
        // if user isn't persisted don't save it .
        if (userDao.isPersisted(instance.getId())) {
            userDao.save(instance);
            logger.debug("user updated with id" + instance.getId());
        } else {
            // This case shouldn't be occured something is really wrong.
            logger.error("Update error : Unpersisted user with user id " + instance.getId());
            throw new NotPersistedException("User not persisted. Update user failed.");
        }
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    public User findUser(Long id) throws NotPersistedException {
        User instance = userDao.find(id);
        if (instance == null) {
            logger.error("Get error : Unpersisted user with user id " + instance.getId());
            throw new NotPersistedException("User not persisted. Update user failed.");
        } else {
            return instance;
        }

    }
}

