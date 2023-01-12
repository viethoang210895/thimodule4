package com.codegym.formatter;

import com.codegym.model.Department;
import com.codegym.service.ICRUDService;
import org.springframework.format.Formatter;

import java.util.Locale;
import java.util.Optional;

public class DepartmentFormatter implements Formatter<Department> {
    private final ICRUDService<Department, Long> iCrudService;

    public DepartmentFormatter(ICRUDService<Department, Long> iCrudService) {
        this.iCrudService = iCrudService;
    }
    @Override
    public Department parse(String text, Locale locale) {
        Optional<Department> department = Optional.ofNullable(iCrudService.findById(Long.parseLong(text)));
        return department.orElseGet(Department::new);
    }


    @Override
    public String print(Department object, Locale locale) {
        return null;
    }
}
