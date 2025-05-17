package com.co.usermanager.usermanager.service;

import com.co.usermanager.usermanager.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

  /**
   * Returns a list of users with ages between minAge and maxAge (inclusive), or all users if minAge and maxAge are null.
   * @param minAge The minimum age of the users to return, or null to not filter by minimum age.
   * @param maxAge The maximum age of the users to return, or null to not filter by maximum age.
   * @return A list of users with ages between minAge and maxAge (inclusive), or all users if minAge and maxAge are null.
   */
  List<User> getUsers(Integer minAge, Integer maxAge);

  /**
   * Returns a user with the given id.
   * @param id The name of the users to return.
   * @return A list of users with the given name.
   */
  User getUserById(String id);

  /**
   * Create a new user.
   * @param user The user to create.
   * @return The created user.
   */
  User createUser(User user);

  /**
   * Update a user.
   * @param id The id of the user to update.
   * @param user The user to update.
   * @return The updated user.
   */
  User updateUser(String id, User user);

  /**
   * Delete a user.
   * @param id The id of the user to delete.
   */
  void deleteUser(String id);
}
