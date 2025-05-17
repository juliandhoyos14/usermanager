package com.co.usermanager.usermanager.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

  @NotNull(message = "Id is required")
  private String id;

  @NotNull(message = "Name is required")
  @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters")
  private String name;

  @NotNull(message = "Email is required")
  @Email(message = "The email is not valid")
  private String email;

  @NotNull(message = "Age is required")
  @Min(value = 0, message = "The age cannot be less than 0")
  @Max(value = 100, message = "The age cannot be greater than 100")
  private Integer age;
}
