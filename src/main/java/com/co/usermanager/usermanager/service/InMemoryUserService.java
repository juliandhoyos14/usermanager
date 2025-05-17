package com.co.usermanager.usermanager.service;

import com.co.usermanager.usermanager.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InMemoryUserService implements UserRepository {

  private final List<User> users = new ArrayList<>();

  @Override
  public List<User> getUsers(Integer minAge, Integer maxAge) {
    if (minAge == null && maxAge == null) {
      return users;
    } else {
      return users.stream()
          .filter(user -> user.getAge() >= minAge && user.getAge() <= maxAge)
          .toList();
    }
  }

  @Override
  public User getUserById(String id) {
    return users.stream()
        .filter(user -> user.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
  }

  @Override
  public User createUser(User user) {
    if (users.stream().anyMatch(existingUser -> existingUser.getId().equals(user.getId()))) {
      throw new IllegalArgumentException("User already exists with id: " + user.getId());
    }
    users.add(user);
    return user;
  }

  @Override
  public User updateUser(String id, User user) {
    User existingUser = users.stream()
        .filter(u -> u.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

    existingUser.setName(user.getName());
    existingUser.setAge(user.getAge());
    existingUser.setEmail(user.getEmail());
    return existingUser;
  }

  @Override
  public void deleteUser(String id) {
    User userToDelete = users.stream()
        .filter(user -> user.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

    users.remove(userToDelete);
  }
}
