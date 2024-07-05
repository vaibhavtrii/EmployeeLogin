package com.Employee.EmployeeLogin.Controller;

import com.Employee.EmployeeLogin.Constants.EmployeeState;
import com.Employee.EmployeeLogin.Constants.Roles;
import com.Employee.EmployeeLogin.DTO.SaveCo;
import com.Employee.EmployeeLogin.DTO.UpdateCo;
import com.Employee.EmployeeLogin.POJOs.Employee;
import com.Employee.EmployeeLogin.POJOs.GrantedAuthorityImpl;
import com.Employee.EmployeeLogin.REPO.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@RestController
@RequestMapping("/Employee")

public class EmployeeController {
    final EmployeeRepo employeeRepo;
    final UserDetailsService userDetailsService;
    final PasswordEncoder passwordEncoder;
    final ModelMapper modelMapper;

    @GetMapping("/")
    public Object getData() {
        return "ok Done!";
    }

    @PostMapping("/save")
    public Object saveEmployee(@RequestBody SaveCo saveCo){
        Optional<Employee>  employee1 = employeeRepo.findByEmail(saveCo.getEmail());
        if (employee1.isPresent())
            return "Employee exist";
        Employee employee2 = modelMapper.map(saveCo,Employee.class);
        GrantedAuthorityImpl grantedAuthority = new GrantedAuthorityImpl();
        grantedAuthority.setRoles(saveCo.getRoles());
        employee2.setGrantedAuthorities(Collections.singletonList(grantedAuthority));
        employee2.setPassword(passwordEncoder.encode(saveCo.getPassword()));
        employeeRepo.save(employee2);
        return "Employee Saved";
    }
    @GetMapping("/get/{id}")
    public Object getEmployeeById(@PathVariable Long id){
        Optional<Employee> employee = employeeRepo.findById(id);
        if(!employee.isPresent())
            return "Employee does not exist";
        return employee.get();
    }
    @GetMapping("/get/all")
    public Object getAllEmployee(){
        return employeeRepo.findAll();
    }
    @PutMapping("/update")
    public String updateEmployee(@RequestBody UpdateCo updateCo){
        Optional<Employee> updateCo1 = employeeRepo.findById(updateCo.getId());
        if (!updateCo1.isPresent())
            return "Employee does not exist";
        Employee employee = updateCo1.get();
        if (employee.getPassword()!= null)
            employee.setPassword(passwordEncoder.encode(updateCo.getPassword()));

        modelMapper.map(updateCo,Employee.class);
        employeeRepo.save(employee);
        return "Details Updated";

    }
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id){
        Optional<Employee> employee = employeeRepo.findById(id);
        if(!employee.isPresent())
            return "Employee doesn't exist";
        employeeRepo.deleteById(id);
        return "Deleted";
    }
    @GetMapping("/get/name")
    public Object getEmployeeByName(@RequestParam(name = "name", required = false) String name){
       List<Employee> employee = employeeRepo.findByName(name);
       if (employee.isEmpty())
           return "Doesn't exist";
        return "details are" + employee;
    }
    @GetMapping("/get/description")
    public Object getDetailsByDesription(@RequestParam(name = "name", required = false) String description){
        List<Employee> employee1 = employeeRepo.findByDescription(description);
        if(employee1.isEmpty())
            return  "EMPLOYEE by this description does not exist";
        return "details are: " + employee1;
    }
    @GetMapping("/get/totalExpenditure")
    public Object getTotalExpenditure(){
        long employee2 = employeeRepo.findTotalSalary();
        return "Total Expenditure " + employee2;

    }
    @GetMapping("/get/AvgSalaryByDescription")
    public Object AvgSalaryByDescription(@RequestParam(name="name", required = false) String description){
        Object employee = employeeRepo.findAvgSalaryByDesc(description);
        if(employee == null)
            return "Currently no employee exist in this Department";
        return "Average of Salary in "+description + "is: " +employee;
    }
    @GetMapping("/get/EmployeesByRole")
    public Object getEmployeeByRoles(@RequestParam(name = "name", required = false) String roles){
                List<Employee> employee =  employeeRepo.findEmployeeByRole(roles);
        if(employee.isEmpty())
           return "Employee by this role is not present";
        return employee;
    }
    @GetMapping("/Employee/EmployeeState")
    public Object EmployeeState(@RequestParam(name = "State",required = false) EmployeeState employeeState){
        return employeeState.ordinal();
        //return employeeState.name();
    }
    /*@GetMapping("/get/ByRole")
    public Object GetByRole(@PathVariable Roles roles){
        
    }*/
}
