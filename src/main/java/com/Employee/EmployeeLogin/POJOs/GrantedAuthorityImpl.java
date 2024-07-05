package com.Employee.EmployeeLogin.POJOs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity(name = "GrantedAuthority")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrantedAuthorityImpl implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String roles;


    @Override
    public String getAuthority() {
        return roles;
    }
}
