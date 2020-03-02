package com.employee.employee.repository;

import com.employee.employee.entity.EmployeeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeDAO, Integer> {


}
