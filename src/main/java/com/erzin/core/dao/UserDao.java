package com.erzin.core.dao;

import com.erzin.core.model.User;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface UserDao extends Repository<User, Integer> {

    public void save(User instance);

    public Long getCount();

    public void delete(User instance);

    public boolean isPersisted(Long instanceId);

    public List<User> findAll();

    public User find(Long id);

}
