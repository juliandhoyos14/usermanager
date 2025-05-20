package com.co.usermanager.usermanager.service;

import com.co.usermanager.usermanager.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, String> {

  List<User> findByAgeBetween(Integer minAge, Integer maxAge);
}
