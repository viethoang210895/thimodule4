package com.codegym.service;

import com.codegym.model.Employee;
import com.codegym.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;


    @Override
    public Page<Employee> findAll(Pageable page) {
        return iEmployeeRepository.findAll((Pageable) page);
    }

    @Override
    public Employee findById(Long id) {
        return iEmployeeRepository.findById(id).get();
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        iEmployeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findByName(String name, Pageable pageable) {
        return iEmployeeRepository.findAllByNameContaining(name,pageable);
    }
    public List<Employee> findByDepart(){
        return (List<Employee>) iEmployeeRepository.findAllByDepartment();
    }

}
