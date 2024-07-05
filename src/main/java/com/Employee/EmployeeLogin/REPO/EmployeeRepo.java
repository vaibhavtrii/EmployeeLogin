package com.Employee.EmployeeLogin.REPO;


import com.Employee.EmployeeLogin.POJOs.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    @Query(value = "select SUM(e.salary) from Employee e")
    Long findTotalSalary();
    List<Employee> findByName(String name);

    List<Employee> findByDescription(String description);

    @Query(value = "select AVG(e.salary) from Employee e where e.description = ?1")
    Object findAvgSalaryByDesc(String description);

    @Query(value = "select e from Employee e Join FETCH e.grantedAuthorities g where g.roles = ?1")
    List<Employee> findEmployeeByRole(String roles);
}