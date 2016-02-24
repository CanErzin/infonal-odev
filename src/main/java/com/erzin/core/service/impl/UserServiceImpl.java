package com.erzin.core.service.impl;

import com.erzin.core.dao.UserDao;
import com.erzin.core.model.User;
import com.erzin.core.service.UserService;
import com.erzin.core.exception.NotPersistedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    UserDao userDao;

    public void saveUser(User instance) {
        //First get the current sequence
        Long currentSequence = getSequence();
        // new instance id will be 1 greater than the sequence.
        instance.setId(++currentSequence); //  set new instance id with calculated value
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

    public Long getSequence() {
        Long currentSequence;
        // fetch user list
        List<User> userList = userDao.findAll();
        // If list is empty sequence is empty return 0
        if (userList.isEmpty()) {
            currentSequence = 0L;
        } else {
        // First Sort the list in desc order.
            Collections.sort(userList, new Comparator<User>() {

                public int compare(User o1, User o2) {
                    return o2.getId().compareTo(o1.getId());
                }
            });
        // get the first instance with max id.
            currentSequence = userList.get(0).getId();
        }

        return currentSequence;
    }

}

