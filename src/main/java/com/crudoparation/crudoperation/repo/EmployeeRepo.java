package com.crudoparation.crudoperation.repo;

import com.crudoparation.crudoperation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>  {
}
