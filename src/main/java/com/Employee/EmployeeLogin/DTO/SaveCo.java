package com.Employee.EmployeeLogin.DTO;

import com.Employee.EmployeeLogin.Constants.Roles;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveCo {
    String name;
    String contact;
    String description;
    @NonNull
    String email;
    String password;
    String roles;
    int salary;

    public SaveCo() {
    }


}
