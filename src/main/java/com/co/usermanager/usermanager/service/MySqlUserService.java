package com.co.usermanager.usermanager.service;

import com.co.usermanager.usermanager.model.User;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@AllArgsConstructor
public class MySqlUserService implements UserRepository {

  private final SpringDataUserRepository repo;

  @Override
  public List<User> getUsers(Integer minAge, Integer maxAge) {
    if (minAge == null && maxAge == null) {
      return repo.findAll();
    }
    return repo.findByAgeBetween(minAge, maxAge);
  }

  @Override
  public User getUserById(String id) {
    return repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
  }

  @Override
  public User createUser(User user) {
    if (repo.existsById(user.getId())) {
      throw new IllegalArgumentException(
          "A user with the same ID already exists.");
    }
    return repo.save(user);
  }

  @Override
  public User updateUser(String id, User user) {
    return repo.findById(id)
        .map(existing -> {
          existing.setName(user.getName());
          existing.setEmail(user.getEmail());
          existing.setAge(user.getAge());
          return repo.save(existing);
        })
        .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
  }

  @Override
  public void deleteUser(String id) {
    if (!repo.existsById(id)) {
      throw new IllegalArgumentException("Usuario con ID " + id + " no encontrado.");
    }
    repo.deleteById(id);
  }
}
