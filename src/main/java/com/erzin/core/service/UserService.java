package com.erzin.core.service;

import com.erzin.core.model.User;
import com.erzin.core.exception.NotPersistedException;

import java.util.List;

public interface UserService {

    /**
     * Persist a new User.
     *
     * @param instance
     */
    public void saveUser(User instance);

    /**
     * Returns the count of all users.
     *
     * @return
     */
    public Long getUserCount();

    /**
     * Deletes a user if user exits.
     *
     * @param userId
     */
    public void deleteUser(Long userId) throws NotPersistedException;


    /**
     * Updates a user if user already persisted.
     *
     * @param instance
     */
    public void updateUser(User instance) throws NotPersistedException;

    /**
     * Returns all the users in the db.
     *
     * @return
     */
    public List<User> getAllUsers();

    /**
     * Search users with given parameters.
     * Could be use for only search name or only searching lastname.
     * Parameter could be set to null to not include in the search criteria.
     * Match case is equal.
     * @param name
     * @param lastName
     * @return
     */


    /**
     * Finds and return  a user with the id.
     */

    public User findUser(Long id) throws NotPersistedException;

    /**
     * This method returns the current sequence in order to generate Id for new instances.
     * It performs an expensive operations it shouldn't use unless it is necessary.
     * @return
     */
    public Long getSequence() ;
}
