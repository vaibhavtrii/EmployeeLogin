package com.Employee.EmployeeLogin.POJOs;


import com.Employee.EmployeeLogin.Constants.Roles;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Employee")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee implements UserDetails {


    @ManyToMany( cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name = "Employee_Role" ,
            joinColumns = @JoinColumn(name = "Employee_id"),
            inverseJoinColumns =@JoinColumn(name = "Roles_id"))
    List<GrantedAuthorityImpl> grantedAuthorities;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String contact;
    int salary;
    @NonNull
    String email;
    String password;
    @Enumerated
    Roles role;
    @Override
    public Collection<? extends GrantedAuthorityImpl> getAuthorities() {
        return grantedAuthorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    public Long getId() {
        return id;
    }
    public Employee() {

    }

}
