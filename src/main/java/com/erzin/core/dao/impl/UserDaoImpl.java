package com.erzin.core.dao.impl;

import com.erzin.core.dao.UserDao;
import com.erzin.core.model.User;
import com.erzin.core.utility.UserDocumentColumnEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Database Access Object Implementation Class for User Entities.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    // connection between db
    private final MongoOperations operations;


    // Inject a MongoTemplate instance to create a link to MongoDb
    @Autowired
    public UserDaoImpl(MongoOperations operations) {
        this.operations = operations;
    }

    /**
     * Persist a new user.
     * If user is already persisted it just updates it.
     *
     * @param instance
     */
    public void save(User instance) {
        operations.save(instance);
    }

    /**
     * Returns the total count of users in the db.
     *
     * @return
     */
    public Long getCount() {
        /**
         *  We're interesting all the users so using following method
         *  {@link MongoOperations#count(Query, Class)}
         *  we don't need to give a query parameter.
         */
        return operations.count(null, User.class);
    }

    /**
     * Deletes the given user from Db.
     *
     * @param instance
     */
    public void delete(User instance) {
        operations.remove(instance);
    }

    /**
     * Finds the user if it persisted.
     * Otherwise it returns null.
     * Must perform null check when using.
     *
     * @param instanceId
     * @return
     */
    public boolean isPersisted(Long instanceId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(UserDocumentColumnEnum.ID.getColumnName()).is(instanceId));
        Object response = operations.findOne(query, User.class);
        if (response != null) {
            return true;
        }
        return false;
    }

    public List<User> findAll() {
        return operations.findAll(User.class);
    }




    public User find(Long id) {
        Query query = new Query();

        query.addCriteria(Criteria.where(UserDocumentColumnEnum.ID.getColumnName()).is(id));
        return operations.findOne(query, User.class);
    }


}
