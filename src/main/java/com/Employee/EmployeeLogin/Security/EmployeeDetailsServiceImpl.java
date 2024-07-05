package com.Employee.EmployeeLogin.Security;

import com.Employee.EmployeeLogin.POJOs.Employee;
import com.Employee.EmployeeLogin.REPO.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    final EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return (UserDetails) employeeRepo.findByEmail(username).orElseThrow(() ->
            new UsernameNotFoundException("User Not Found"+username));
    }
}
