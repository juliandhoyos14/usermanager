package com.co.usermanager.usermanager.controller;

import com.co.usermanager.usermanager.model.User;
import com.co.usermanager.usermanager.usecase.UserUseCase;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

  private final UserUseCase userUseCase;

  @GetMapping
  public ResponseEntity<?> getUsers(@RequestParam(required = false) Integer minAge,
                                    @RequestParam(required = false) Integer maxAge) {

    if (minAge != null && maxAge != null && minAge > maxAge) {
      return ResponseEntity
          .badRequest()
          .body(Map.of("error", "minAge cannot be greater than maxAge"));
    }
    List<User> users = userUseCase.getUsers(minAge, maxAge);
    return ResponseEntity
        .ok()
        .body(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable String id) {
    User user = userUseCase.getUserById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
    if (result.hasErrors()) {
      List<String> errors = result.getFieldErrors().stream()
          .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
          .toList();

      return ResponseEntity
          .badRequest()
          .body(Map.of("errors", errors));
    }

    User created = userUseCase.createUser(user);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody User user, BindingResult result) {
    if (result.hasErrors()) {
      List<String> errors = result.getFieldErrors().stream()
          .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
          .toList();
      return ResponseEntity
          .badRequest()
          .body(Map.of("errors", errors));
    }

    User updated = userUseCase.updateUser(id, user);
    return ResponseEntity
        .ok(updated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    userUseCase.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
