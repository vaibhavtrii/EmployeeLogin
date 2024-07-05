package com.Employee.EmployeeLogin.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCo {
    long id;
    String name;
    String contact;
    String description;
    String email;
    String password;
    String roles;
    int salary;


}
