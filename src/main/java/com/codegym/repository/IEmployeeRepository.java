package com.codegym.repository;

import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
    Page<Employee>findAllByNameContaining(String name, Pageable pageable);

    @Query(
            value = "SELECT * FROM employee e ORDER BY e.age ASC ",
            nativeQuery = true)
    Collection<Employee> findAllByDepartment();

}
