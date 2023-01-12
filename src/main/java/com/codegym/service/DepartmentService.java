package com.codegym.service;

import com.codegym.model.Department;
import com.codegym.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements ICRUDService<Department, Long>, IDepartmentService {
    @Autowired
    private IDepartmentRepository iDepartmentRepository;


    @Override
    public Page<Department> findAll(Pageable page) {
        return iDepartmentRepository.findAll((Pageable) page);
    }

    @Override
    public Department findById(Long id) {
        return iDepartmentRepository.findById(id).get();
    }

    @Override
    public void save(Department aClass) {
        iDepartmentRepository.save(aClass);
    }

    @Override
    public void delete(Long id) {
        iDepartmentRepository.deleteById(id);
    }

    @Override
    public Page<Department> findByName(String name, Pageable pageable) {
        return iDepartmentRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public List<Department> findClass() {
        return (List<Department>) iDepartmentRepository.findAll();
    }
}
