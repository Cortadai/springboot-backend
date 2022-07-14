package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Employee;

//@Repository
// There is no need to add @Repository annotation because we are extending
// the interface from JpaRepositry, which does incorporate the @Repository annotation
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
